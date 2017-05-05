package com.hangdude.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;
import com.hangdude.model.constants.ErrorMessageConstants;
import com.hangdude.service.MainService;

/**
 * A Class that implements the {@link MainService}, and implements the main operations for managing elements
 * 
 * @author ahamouda
 *
 */
public abstract class AbsMainService<T, E> implements MainService<T, E> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbsMainService.class);

	/** A variable that keeps track of all elements **/
	protected Map<E, T> elements;

	public AbsMainService() {
		elements = new ConcurrentHashMap<>();
	}

	/**
	 * @see MainService#getAll()
	 */
	@Override
	public List<T> getAll() {
		// Prevent list from being updated!
		return ImmutableList.copyOf(elements.values());
	}

	/**
	 * @see MainService#getElement(Object)
	 */
	@Override
	public T getElement(E key) {
		if (key == null) {
			LOGGER.error(ErrorMessageConstants.FAIL_GET);
			return null;
		}
		return elements.get(key);
	}

	/**
	 * @see MainService#addElement(Object, Object)
	 */
	@Override
	public boolean addElement(E key, T element) {
		if (key == null || element == null) {
			LOGGER.error(ErrorMessageConstants.FAIL_ADD, key, element);
			return false;
		}

		if (elements.containsKey(key)) {
			LOGGER.error(ErrorMessageConstants.KEY_EXISTS, key);
			return false;
		}

		elements.put(key, element);
		return true;
	}

	/**
	 * @see MainService#addUpdateElement(Object, Object)
	 */
	@Override
	public boolean addUpdateElement(E key, T element) {
		if (key == null || element == null) {
			LOGGER.error(ErrorMessageConstants.FAIL_ADD_UPDATE);
			return false;
		}

		elements.put(key, element);
		return true;
	}

	/**
	 * @see MainService#removeElement(Object)
	 */
	@Override
	public T removeElement(E key) {
		if (key == null) {
			LOGGER.error(ErrorMessageConstants.FAIL_REMOVE);
			return null;
		}

		if (!elements.containsKey(key)) {
			LOGGER.error(ErrorMessageConstants.KEY_NON_EXISTENT);
			return null;
		}

		return elements.remove(key);
	}

	/**
	 * @see MainService#removeAll()
	 */
	@Override
	public void removeAll() {
		elements.clear();
	}

}
