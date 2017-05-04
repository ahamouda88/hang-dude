package com.hangdude.model.constants;

/**
 * An interface that contains url path constants
 */
public class PathConstants {

	// Common constants
	public final static String API = "/api";
	public final static String CATEGORIES = "/categories";

	// Game board constants
	public final static String BOARDS_PATH = API + "/boards"; // endpoint: /api/boards
	public final static String CURRENT_BOARD_PATH = BOARDS_PATH + "/current"; // endpoint: /api/boards/current
	public final static String CHARACTER_PATH = CURRENT_BOARD_PATH + "/{char}"; // endpoint: /api/boards/current/{char}

}
