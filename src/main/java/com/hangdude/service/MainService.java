package com.hangdude.service;

import java.util.List;

/**
 * An interface that manages the basic/main operations on elements
 * 
 * @author ahamouda
 *
 * @param <T>
 *            the data type of the element
 * @param <E>
 *            the data type of the element's key
 */
public interface MainService<T, E> {

	/**
	 * A method that returns all elements
	 * 
	 * @return a list of elements
	 */
	public List<T> getAll();

	/**
	 * A method that returns the element associated with the given key
	 * 
	 * @param key
	 *            the key for the element
	 * @return an element if the given key is valid, otherwise it will return <b>null</b>
	 */
	public T getElement(E key);

	/**
	 * A method that adds an element
	 * 
	 * @param key
	 *            the key of the element
	 * @param element
	 *            the element that need to be added
	 * @return true if element is added successfully, false otherwise
	 */
	public boolean addElement(E key, T element);

	/**
	 * A method that adds a new element if not exists, or update existing element
	 * 
	 * @param key
	 *            a key associated with the game board
	 * @param element
	 *            the element need to be updated
	 * @return true if element is added/updated successfully, false otherwise
	 */
	public boolean addUpdateElement(E key, T element);

	/**
	 * A method that removes an element using the given key associated with it
	 * 
	 * @param key
	 *            a key associated with the game board
	 * @return the deleted element if exists, otherwise it will return <b>null</b>
	 */
	public T removeElement(E key);

	/**
	 * A method to remove all elements
	 */
	public void removeAll();
}
