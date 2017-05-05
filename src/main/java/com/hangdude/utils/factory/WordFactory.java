package com.hangdude.utils.factory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableSet;
import com.hangdude.model.Category;
import com.hangdude.model.Difficulty;
import com.hangdude.model.GameWord;
import com.hangdude.model.constants.ErrorMessageConstants;

/**
 * A class that creates {@link GameWord} objects
 * 
 * @author ahamouda
 *
 */
public final class WordFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(WordFactory.class);

	/** Main variable that acts as a storage for words with different categories and difficulties **/
	private static final Map<Category, Map<Difficulty, Set<GameWord>>> WORDS;

	/**
	 * Initializing words with different categories and difficulties Note: this could be in a database
	 */
	static {
		WORDS = new HashMap<>();

		//@formatter:off
		// Category: Sport
		GameWord sport1 = GameWord.builder().category(Category.SPORT).difficulty(Difficulty.EASY).word("SOCCER").build();
		GameWord sport2 = GameWord.builder().category(Category.SPORT).difficulty(Difficulty.MEDIUM).word("TABLE TENNIS").build();
		GameWord sport3 = GameWord.builder().category(Category.SPORT).difficulty(Difficulty.HARD).word("FIGURE SKATING").build();
		Map<Difficulty, Set<GameWord>> sportMap = new HashMap<>();
		sportMap.put(Difficulty.EASY,  ImmutableSet.of(sport1));
		sportMap.put(Difficulty.MEDIUM,  ImmutableSet.of(sport2));
		sportMap.put(Difficulty.HARD,  ImmutableSet.of(sport3));
		
		// Category: Country
		GameWord country1 = GameWord.builder().category(Category.COUNTRY).difficulty(Difficulty.EASY).word("GERMANY").build();
		GameWord country2 = GameWord.builder().category(Category.COUNTRY).difficulty(Difficulty.MEDIUM).word("EGYPT").build();
		GameWord country3 = GameWord.builder().category(Category.COUNTRY).difficulty(Difficulty.HARD).word("MOZAMBIQUE").build();
		Map<Difficulty, Set<GameWord>> countryMap = new HashMap<>();
		countryMap.put(Difficulty.EASY,  ImmutableSet.of(country1));
		countryMap.put(Difficulty.MEDIUM,  ImmutableSet.of(country2));
		countryMap.put(Difficulty.HARD,  ImmutableSet.of(country3));
		
		// Category: Food
		GameWord food1 = GameWord.builder().category(Category.FOOD).difficulty(Difficulty.EASY).word("BEEF").build();
		GameWord food2 = GameWord.builder().category(Category.FOOD).difficulty(Difficulty.MEDIUM).word("OMELET").build();
		GameWord food3 = GameWord.builder().category(Category.FOOD).difficulty(Difficulty.HARD).word("BRUSSELS SPROUTS").build();
		Map<Difficulty, Set<GameWord>> foodMap = new HashMap<>();
		foodMap.put(Difficulty.EASY,  ImmutableSet.of(food1));
		foodMap.put(Difficulty.MEDIUM,  ImmutableSet.of(food2));
		foodMap.put(Difficulty.HARD,  ImmutableSet.of(food3));
		
		// Category: Animal
		GameWord animal1 = GameWord.builder().category(Category.ANIMAL).difficulty(Difficulty.EASY).word("CAT").build();
		GameWord animal2 = GameWord.builder().category(Category.ANIMAL).difficulty(Difficulty.MEDIUM).word("CHIMIPANZEE").build();
		GameWord animal3 = GameWord.builder().category(Category.ANIMAL).difficulty(Difficulty.HARD).word("GOLDEN LION TAMARIN").build();
		Map<Difficulty, Set<GameWord>> animalMap = new HashMap<>();
		animalMap.put(Difficulty.EASY,  ImmutableSet.of(animal1));
		animalMap.put(Difficulty.MEDIUM,  ImmutableSet.of(animal2));
		animalMap.put(Difficulty.HARD,  ImmutableSet.of(animal3));
		
		
		// Category: City
		GameWord city1 = GameWord.builder().category(Category.CITY).difficulty(Difficulty.EASY).word("LONDON").build();
		GameWord city2 = GameWord.builder().category(Category.CITY).difficulty(Difficulty.MEDIUM).word("ALEXANDRIA").build();
		GameWord city3 = GameWord.builder().category(Category.CITY).difficulty(Difficulty.HARD).word("PARIS").build();
		Map<Difficulty, Set<GameWord>> cityMap = new HashMap<>();
		cityMap.put(Difficulty.EASY,  ImmutableSet.of(city1));
		cityMap.put(Difficulty.MEDIUM,  ImmutableSet.of(city2));
		cityMap.put(Difficulty.HARD,  ImmutableSet.of(city3));
		//@formatter:on

		WORDS.put(Category.SPORT, sportMap);
		WORDS.put(Category.COUNTRY, countryMap);
		WORDS.put(Category.FOOD, foodMap);
		WORDS.put(Category.ANIMAL, animalMap);
		WORDS.put(Category.CITY, cityMap);

	}

	/** Private constructor **/
	private WordFactory() {
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
	public static GameWord getWord(Category category, Difficulty difficulty) {
		return getWord(category, difficulty, null);
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
	 * @return a {@link GameWord} that matches the given parameters, or <b>null</b> if word type doesn't exist
	 */
	public static GameWord getWord(Category category, Difficulty difficulty, Set<GameWord> excludedWords) {
		if (category == null || difficulty == null) {
			LOGGER.error(ErrorMessageConstants.NO_WORD_ERROR);
			return null;
		}

		// Get words based on category first
		Map<Difficulty, Set<GameWord>> categoryWords = WORDS.get(category);
		if (categoryWords == null) {
			LOGGER.error(ErrorMessageConstants.NO_WORD_CAT_ERROR);
			return null;
		}

		// Get words based on difficulty
		Set<GameWord> difficultyWords = categoryWords.get(difficulty);
		if (difficultyWords == null) {
			LOGGER.error(ErrorMessageConstants.NO_WORD_DIF_ERROR);
			return null;
		}

		difficultyWords = new HashSet<>(difficultyWords);

		// Excluded words
		if (excludedWords != null && !excludedWords.isEmpty()) {
			difficultyWords.removeAll(excludedWords);
		}

		// Return the next word if exists!
		return difficultyWords.iterator().hasNext() ? difficultyWords.iterator().next() : null;
	}

	/**
	 * A method that creates a {@link GameWord} object that matches the given criteria
	 * 
	 * @param word
	 *            a string that represents the word
	 * @param category
	 *            the {@link Category} of the problem
	 * @param difficulty
	 *            the {@link Difficulty} of the problem
	 * @return a {@link GameWord} that matches the given parameters, or <b>null</b> if word type doesn't exist
	 */
	public static GameWord createWord(String word, Category category, Difficulty difficulty) {
		if (word == null || category == null || difficulty == null) {
			LOGGER.error(ErrorMessageConstants.CREATE_WORD_ERROR);
			throw null;
		}

		return GameWord.builder().category(category).difficulty(difficulty).word(word).build();
	}

}
