package com.hangdude.model;

/**
 * An enum that represents the difficulty of the game
 * 
 * @author ahamouda
 */
public enum Difficulty {

	EASY("Easy"),
	MEDIUM("Medium"),
	HARD("Hard");

	private final String title;

	private Difficulty(String title) {
		this.title = title;
	}

	public String title() {
		return title;
	}
}
