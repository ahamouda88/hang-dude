package com.hangdude.model.constants;

/**
 * An interface that contains all error messages
 */
public interface ErrorMessageConstants {

	// Board and Character Error Messages
	public static final String CREATE_HANGBOARD_ERROR = "Failed to create a Hangdude board, due to invalid input.";
	public static final String ADD_CHARACTER_ERROR = "Failed to add character, due to invalid parameter.";
	public static final String ADD_CHARACTER_KEY_NON_EXISTENT = "Failed to add character. Board with the given key doesn't exist.";

	// Word Error Messages
	public static final String CREATE_WORD_ERROR = "Failed to create word due to invalid input.";
	public static final String NO_WORD_ERROR = "Failed to find word due to invalid parameters.";
	public static final String NO_WORD_CAT_ERROR = "No words for the given category.";
	public static final String NO_WORD_DIF_ERROR = "No words for the given difficulty.";
	public static final String WORD_NON_EXISTENT = "Word with the given criteria doesn't exist.";

	// Element CRUD Operations Error Messages
	public static final String FAIL_REMOVE = "Failed to remove an element, due to invalid key.";
	public static final String FAIL_ADD = "Failed to add an element, due to invalid key.";
	public static final String FAIL_ADD_UPDATE = "Failed to add/update an element, due to invalid parameter(s).";
	public static final String KEY_NON_EXISTENT = "Element with the given key doesn't exist.";
	public static final String KEY_EXISTS = "Element with the given key does exist.";
}
