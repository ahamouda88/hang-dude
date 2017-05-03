package com.hangdude.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;
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
		return ImmutableList.copyOf(elements.values());
	}

	/**
	 * @see MainService#getElement(Object)
	 */
	@Override
	public T getElement(E key) {
		if (key == null) {
			LOGGER.error("Failed to return an element, due to invalid parameter. Key: {}.", key);
			return null;
		}
		return elements.get(key);
	}

	/**
	 * @see MainService#addElement(Object, Object)
	 */
	@Override
	public boolean addElement(E key, T element) {
		return updateOrAddElement(key, element, false);
	}

	/**
	 * @see MainService#updateElement(Object, Object)
	 */
	@Override
	public boolean updateElement(E key, T element) {
		return updateOrAddElement(key, element, true);
	}

	/**
	 * @see MainService#removeElement(Object)
	 */
	@Override
	public T removeElement(E key) {
		if (key == null) {
			LOGGER.error("Failed to remove an element, due to invalid parameter. key: {}.", key);
			return null;
		}

		if (!elements.containsKey(key)) {
			LOGGER.error("Failed to remove an element. Element with the given key '{}', doesn't exist.", key);
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

	private boolean updateOrAddElement(E key, T element, boolean isUpdate) {
		if (key == null || element == null) {
			LOGGER.error("Failed to add an element, due to invalid parameters. Key: {}, and Element: {}.", key,
					element);
			return false;
		}

		if (isUpdate) {
			if (!elements.containsKey(key)) {
				LOGGER.error("Failed to update an element. Board with the given key '{}' doesn't exist.", key);
				return false;
			}
		} else {
			if (elements.containsKey(key)) {
				LOGGER.error("Failed to add an element. Element with the given key '{}' already exists.", key);
				return false;
			}
		}
		elements.put(key, element);

		return true;
	}

}
