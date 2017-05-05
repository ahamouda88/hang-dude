package com.hangdude.service.impl;

import static org.springframework.util.StringUtils.isEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangdude.exception.WordNonExistentException;
import com.hangdude.model.Dude;
import com.hangdude.model.GameWord;
import com.hangdude.model.HangdudeBoard;
import com.hangdude.model.api.BoardRequest;
import com.hangdude.model.constants.ErrorMessageConstants;
import com.hangdude.service.BoardService;
import com.hangdude.service.WordService;

/**
 * A class that implements the {@link BoardService} interface and extends main operations from {@link AbsMainService}
 * 
 * @author ahamouda
 *
 */
@Service
public class HangdudeBoardService extends AbsMainService<HangdudeBoard, String>
		implements BoardService<HangdudeBoard, String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(HangdudeBoardService.class);

	@Autowired
	private WordService<GameWord> wordService;

	/**
	 * @see BoardService#addCharacter(Character, Object)
	 */
	@Override
	public HangdudeBoard addCharacter(Character character, String key) {
		if (character == null) {
			LOGGER.error(ErrorMessageConstants.ADD_CHARACTER_ERROR);
			return null;
		}

		HangdudeBoard board = this.getElement(key);
		// Board with the given key doesn't exist
		if (board == null) {
			LOGGER.error(ErrorMessageConstants.ADD_CHARACTER_KEY_NON_EXISTENT);
			return null;
		}
		String newWord = wordService.addCharacter(character, board.getWordState(), board.getCurrentWord());

		// Set attempted character as true
		int charPos = character - 'A';
		board.getClickedChars()[charPos] = true;

		// If new word is null means the character doesn't exist in the word
		if (newWord == null) {
			board.setNumOfAttempts(board.getNumOfAttempts() + 1);
			// If maximum number of attempts is reached then show the whole word
			if (board.isFailed()) {
				board.setWordState(board.getCurrentWord().getWord());
			}
			elements.put(key, board);
			return board;
		}

		/*
		 * If original word is equal to the new word, that means the user has completed the game, and should add the
		 * current word to completed words
		 */
		board.setWordState(newWord);
		if (board.getCurrentWord().getWord().equals(newWord)) {
			board.getDude().getCompletedWords().add(board.getCurrentWord());
		}
		elements.put(key, board);
		return board;
	}

	/**
	 * @see BoardService#addUpdateBoard(Object, BoardRequest)
	 */
	@Override
	public HangdudeBoard addUpdateBoard(String key, BoardRequest request) {
		// Validate parameters first
		if (key == null || request == null || request.getCategory() == null || request.getDifficulty() == null) {
			LOGGER.error(ErrorMessageConstants.FAIL_ADD_UPDATE);
			return null;
		}
		/*
		 * Check if board associated with the given key already exists, and if board exists then update the current
		 * board associated with the given key
		 */
		Dude dude;
		HangdudeBoard board = this.getElement(key);
		if (board != null && board.getDude() != null) {
			dude = board.getDude();
		} else {
			String username = isEmpty(request.getUsername()) ? "Anonymous" : request.getUsername();
			dude = Dude.builder().id(key).username(username).build();
		}

		GameWord gameWord = wordService.getWord(request.getCategory(), request.getDifficulty(),
				dude.getCompletedWords());

		if (gameWord == null) {
			throw new WordNonExistentException(ErrorMessageConstants.WORD_NON_EXISTENT);
		}
		board = new HangdudeBoard(dude, gameWord);
		return this.addUpdateElement(key, board) ? board : null;
	}

}
