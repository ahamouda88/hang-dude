package com.hangdude.service.impl;

import static org.apache.commons.lang.StringUtils.isEmpty;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hangdude.model.Category;
import com.hangdude.model.Difficulty;
import com.hangdude.model.GameWord;
import com.hangdude.model.constants.ErrorMessageConstants;
import com.hangdude.service.WordService;
import com.hangdude.utils.factory.WordFactory;

/**
 * An implementation of {@link WordService} interface, and manages {@link GameWord} elements
 * 
 * @author ahamouda
 *
 */
@Service
public class GameWordService implements WordService<GameWord> {

	private static final Logger LOGGER = LoggerFactory.getLogger(GameWordService.class);

	/**
	 * @see WordService#getWord(Category, Difficulty)
	 */
	@Override
	public GameWord getWord(Category category, Difficulty difficulty) {
		return WordFactory.getWord(category, difficulty);
	}

	/**
	 * @see WordService#getWord(Category, Difficulty, Set)
	 */
	@Override
	public GameWord getWord(Category category, Difficulty difficulty, Set<GameWord> excludedWords) {
		return WordFactory.getWord(category, difficulty, excludedWords);
	}

	/**
	 * @see WordService#addCharacter(Character, String, Object)
	 */
	@Override
	public String addCharacter(Character character, String currentWordState, GameWord gameWord) {
		if (character == null || isEmpty(currentWordState) || gameWord == null || gameWord.getWord() == null) {
			LOGGER.error(ErrorMessageConstants.ADD_CHARACTER_ERROR);
			return null;
		}
		Map<Character, List<Integer>> charMap = gameWord.getCharPositions();
		List<Integer> positions = charMap.get(character);

		// Return null if character doesn't exist in the word
		if (positions == null) return null;

		char[] chars = currentWordState.toCharArray();

		// Fill the characters array with the new character
		positions.forEach(pos -> chars[pos] = character);

		return new String(chars);
	}

}
