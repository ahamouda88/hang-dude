package com.hangdude.api.request;

import java.io.Serializable;

import com.hangdude.model.Category;
import com.hangdude.model.Difficulty;

/**
 * A POJO class that represents a request for adding a board
 * 
 * @author ahamouda
 *
 */
public class BoardRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Category category;
	private Difficulty difficulty;
	private String username;

	public BoardRequest() {
	}

	public BoardRequest(Category category, Difficulty difficulty, String username) {
		this.category = category;
		this.difficulty = difficulty;
		this.username = username;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((difficulty == null) ? 0 : difficulty.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		BoardRequest other = (BoardRequest) obj;
		if (category != other.category) return false;
		if (difficulty != other.difficulty) return false;
		if (username == null) {
			if (other.username != null) return false;
		} else if (!username.equals(other.username)) return false;
		return true;
	}

}
