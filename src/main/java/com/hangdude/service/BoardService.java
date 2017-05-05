package com.hangdude.service;

import com.hangdude.model.api.BoardRequest;

/**
 * An interface that manages the operations on the game boards, and that extends {@link MainService}
 * 
 * @author ahamouda
 *
 * @param <T>
 *            the data type of the game board
 * @param <E>
 *            the data type of the board's key
 */
public interface BoardService<T, E> extends MainService<T, E> {

	/**
	 * A method that adds a character to the current word state if valid
	 * 
	 * @param character
	 *            the character that need to be added
	 * @param key
	 *            the key of the game board
	 * @return an updated board based on the addition of the new character, or <b>null</b> if character wasn't added
	 *         successfully
	 */
	public T addCharacter(Character character, E key);

	/**
	 * A method that adds a new board if not exists, or update existing element
	 * 
	 * @param key
	 *            the key of the game board
	 * @param request
	 *            a {@link BoardRequest} object
	 * @return an updated or a new board, or <b>null</b> if board wasn't successfully updated or added
	 */
	public T addUpdateBoard(E key, BoardRequest request);
}
