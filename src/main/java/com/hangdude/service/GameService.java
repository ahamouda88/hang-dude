package com.hangdude.service;

import java.util.Set;

import com.hangdude.model.Category;
import com.hangdude.model.Difficulty;
import com.hangdude.model.Dude;
import com.hangdude.model.GameWord;

/**
 * An interface that manages the operations on the game word
 * 
 * @author ahamouda
 * 
 * @param <T>
 *            the data type of the game board
 */
public interface GameService<T> {

	/**
	 * A method that returns a game word given the category and difficulty parameters
	 * 
	 * @param category
	 *            the {@link Category} of the word needed
	 * @param difficulty
	 *            the {@link Difficulty} of the word needed
	 * @return a game word if given parameters are valid, otherwise it will return <b>null</b>
	 */
	public GameWord getGameWord(Category category, Difficulty difficulty);

	/**
	 * A method that returns a game word given the category, difficulty and excluded words parameters
	 * 
	 * @param category
	 *            the {@link Category} of the word needed
	 * @param difficulty
	 *            the {@link Difficulty} of the word needed
	 * @param excludedWords
	 *            words that should be excluded from the selection
	 * @return a game word if given parameters are valid, otherwise it will return <b>null</b>
	 */
	public GameWord getGameWord(Category category, Difficulty difficulty, Set<GameWord> excludedWords);

	/**
	 * A method that adds a character to the current word state if valid
	 * 
	 * @param character
	 *            the character that need to be added
	 * @param board
	 *            a {@link GameWord} representing the current word
	 * @return an updated board based on the addition of the new character
	 */
	public T addCharacter(Character character, T board);

	/**
	 * A method that adds a word to the set of completed words for a user
	 * 
	 * @param gameWord
	 *            the {@link GameWord} that need to be added
	 * @param dude
	 *            the {@link Dude} that completed the word
	 * 
	 * @return true if word is added successfully, otherwise it will return false
	 */
	public boolean addCompletedWord(GameWord gameWord, Dude dude);
}
