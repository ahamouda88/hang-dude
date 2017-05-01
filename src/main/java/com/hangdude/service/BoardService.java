package com.hangdude.service;

import java.util.List;

import com.hangdude.model.Dude;
import com.hangdude.model.GameWord;

/**
 * An interface that manages the operations on the game boards
 * 
 * @author ahamouda
 *
 * @param <T>
 *            the data type of the game board
 */
public interface BoardService<T, E> {

	/**
	 * A method that returns all game boards currently being played
	 * 
	 * @return a list of game boards
	 */
	public List<T> getAllBoards();

	/**
	 * A method that returns the game board associated with the given key
	 * 
	 * @param key
	 *            the key for the wanted game board
	 * @return a game board if the given key is valid, otherwise it will return <b>null</b>
	 */
	public T getBoard(E key);

	/**
	 * A method that creates a game board using the dude and game word parameters
	 * 
	 * @param key
	 *            the key of the new game board
	 * @param dude
	 *            a {@link Dude} object
	 * @param gameWord
	 *            a {@link GameWord} object
	 * @return a new created game board if created successfully, otherwise it will return <b>null</b>
	 */
	public T createBoard(E key, Dude dude, GameWord gameWord);

	/**
	 * A method that updates an existing game board using the given key associated with the board
	 * 
	 * @param key
	 *            a key associated with the game board
	 * @param board
	 *            an updated game board
	 * @return true if board is updated successfully, false otherwise
	 */
	public boolean updateBoard(E key, T board);

	/**
	 * A method that removes an existing game board using the given key associated with the board
	 * 
	 * @param key
	 *            a key associated with the game board
	 * @return the deleted board if exists, otherwise it will return <b>null</b>
	 */
	public T removeBoard(E key);

	/**
	 * A method to remove all existing boards
	 */
	public void removeAllBoards();

}
