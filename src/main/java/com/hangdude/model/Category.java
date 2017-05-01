package com.hangdude.model;

/**
 * An enum that represents the category of the game
 * 
 * @author ahamouda
 */
public enum Category {

	SPORT("Sport"),
	ANIMAL("Animal"),
	COUNTRY("Country"),
	CITY("City"),
	FOOD("Food");

	private final String title;

	private Category(String title) {
		this.title = title;
	}

	public String title() {
		return title;
	}
}
