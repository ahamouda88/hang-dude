package com.hangdude.service;

import java.util.Set;

import com.hangdude.model.Category;
import com.hangdude.model.Difficulty;

/**
 * An interface that manages the operations on the word
 * 
 * @author ahamouda
 * 
 * @param <T>
 *            the data type of the word
 */
public interface WordService<T> {

	/**
	 * A method that returns a word given the category and difficulty parameters
	 * 
	 * @param category
	 *            the {@link Category} of the word needed
	 * @param difficulty
	 *            the {@link Difficulty} of the word needed
	 * @return a word if given parameters are valid, otherwise it will return <b>null</b>
	 */
	public T getWord(Category category, Difficulty difficulty);

	/**
	 * A method that returns a word given the category, difficulty and excluded words parameters
	 * 
	 * @param category
	 *            the {@link Category} of the word needed
	 * @param difficulty
	 *            the {@link Difficulty} of the word needed
	 * @param excludedWords
	 *            words that should be excluded from the selection
	 * @return a word if given parameters are valid, otherwise it will return <b>null</b>
	 */
	public T getWord(Category category, Difficulty difficulty, Set<T> excludedWords);

	/**
	 * A method that adds a character to the current word state if valid
	 * 
	 * @param character
	 *            the character that need to be added
	 * @param currentWordState
	 *            a string that represents the current state of the word
	 * @param word
	 *            an instance of the word
	 * @return a string with the added character if valid, otherwise will return <b>null</b>
	 */
	public String addCharacter(Character character, String currentWordState, T word);
}
