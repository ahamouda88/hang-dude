package com.hangdude.service.impl;

import static org.springframework.util.StringUtils.isEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangdude.api.request.BoardRequest;
import com.hangdude.model.Dude;
import com.hangdude.model.GameWord;
import com.hangdude.model.HangdudeBoard;
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
		if (key == null || character == null) {
			LOGGER.error("Failed to add character, due to invalid parameters. Key: {}, and character: {}.", key,
					character);
			return null;
		}

		if (!elements.containsKey(key)) {
			LOGGER.error("Failed to add character. Board with the given key '{}' doesn't exist.", key);
			return null;
		}

		HangdudeBoard board = elements.get(key);
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

		/*
		 * Check if board associated with the given key already exists, and if board exists then update the current
		 * board associated with the given key
		 */
		boolean check = true;
		GameWord gameWord;
		HangdudeBoard board = this.getElement(key);
		if (board != null && board.getDude() != null) {
			gameWord = wordService.getWord(request.getCategory(), request.getDifficulty(),
					board.getDude().getCompletedWords());

			// If word exists update the current, otherwise keep the most recent board
			if (gameWord != null) {
				board = new HangdudeBoard(board.getDude(), gameWord);
				check = this.updateElement(key, board);
			}
		} else {
			// Create board objects
			String username = isEmpty(request.getUsername()) ? "Anonymous" : request.getUsername();
			Dude dude = Dude.builder().id(key).username(username).build();
			gameWord = wordService.getWord(request.getCategory(), request.getDifficulty());

			board = new HangdudeBoard(dude, gameWord);
			check = this.addElement(key, board);
		}

		return check ? board : null;
	}

}
