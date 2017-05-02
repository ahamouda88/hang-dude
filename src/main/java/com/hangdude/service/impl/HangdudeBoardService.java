package com.hangdude.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hangdude.model.GameWord;
import com.hangdude.model.board.HangdudeBoard;
import com.hangdude.service.BoardService;
import com.hangdude.service.WordService;

/**
 * A class that implements the {@link BoardService} interface and extends main operations from {@link AbsMainService}
 * 
 * @author ahamouda
 *
 */
public class HangdudeBoardService extends AbsMainService<HangdudeBoard, String>
		implements BoardService<HangdudeBoard, String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(HangdudeBoardService.class);

	private final WordService<GameWord> wordService;

	public HangdudeBoardService(WordService<GameWord> wordService) {
		this.wordService = wordService;
	}

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

		// If new word is null means the character doesn't exist in the word
		if (newWord == null) {
			board.setNumOfAttempts(board.getNumOfAttempts() + 1);
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

}
