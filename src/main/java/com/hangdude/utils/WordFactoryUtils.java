package com.hangdude.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import challenges.hangdude.model.Category;
import challenges.hangdude.model.Difficulty;
import challenges.hangdude.model.GameWord;

/**
 * A class that creates {@link GameWord} objects
 * 
 * @author ahamouda
 *
 */
public final class WordFactoryUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(WordFactoryUtils.class);

	/** Main variable that acts as a storage for words with different categories and difficulties **/
	private static final Map<Category, Map<Difficulty, Set<GameWord>>> WORDS;

	/**
	 * Initializing words with different categories and difficulties
	 */
	static {
		WORDS = new HashMap<>();

		// EASY Words
		// Category: Sport
		GameWord gameWord = GameWord.builder().category(Category.SPORT).difficulty(Difficulty.EASY).word("").build();

	}

	/**
	 * A method that returns a word that matches the given parameters
	 * 
	 * @param category
	 *            the {@link Category} of the problem
	 * @param difficulty
	 *            the {@link Difficulty} of the problem
	 * @return a {@link GameWord} that matches the given parameters, or <b>null</b> if category or difficulty parameter
	 *         is null
	 */
	public static GameWord createWord(Category category, Difficulty difficulty) {
		return createWord(category, difficulty, null);
	}

	/**
	 * A method that returns a word that matches the given criteria
	 * 
	 * @param category
	 *            the {@link Category} of the problem
	 * @param difficulty
	 *            the {@link Difficulty} of the problem
	 * @param excludedWords
	 *            words that should be excluded from the selection
	 * @return a {@link GameWord} that matches the given parameters, or <b>null</b> if category or difficulty parameter
	 *         is null
	 */
	public static GameWord createWord(Category category, Difficulty difficulty, Set<GameWord> excludedWords) {
		if (category == null || difficulty == null) {
			LOGGER.error("Category and Difficulty shouldnot be null!");
			return null;
		}

		// Get words based on category first
		Map<Difficulty, Set<GameWord>> categoryWords = WORDS.get(category);
		if (categoryWords == null) {
			LOGGER.error("No words for the following category: " + category.name());
			return null;
		}

		// Get words based on difficulty
		Set<GameWord> difficultyWords = categoryWords.get(difficulty);
		if (difficultyWords == null) {
			LOGGER.error("No words for the following difficulty: " + difficulty.name());
			return null;
		}

		// Excluded words
		if (excludedWords != null && !excludedWords.isEmpty()) {
			difficultyWords.removeAll(excludedWords);
		}

		// Return the next word
		return difficultyWords.iterator().next();
	}

}
