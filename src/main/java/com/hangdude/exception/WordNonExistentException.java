package com.hangdude.exception;

/**
 * A class that extends {@link RuntimeException}, and it should be thrown when word doesn't exist
 * 
 * @author ahamouda
 *
 */
public class WordNonExistentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public WordNonExistentException(String message) {
		super(message);
	}
}
