package com.hangdude.model;

import java.io.Serializable;

/**
 * A POJO class that represents a game word
 * 
 * @author ahamouda
 */
public class GameWord implements Serializable {

	private static final long serialVersionUID = 1L;

	private String word;
	private Difficulty difficulty;
	private Category category;

	private GameWord(Builder builder) {
		this.word = builder.word;
		this.difficulty = builder.difficulty;
		this.category = builder.category;
	}

	public String getWord() {
		return word;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public Category getCategory() {
		return category;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String word;
		private Difficulty difficulty;
		private Category category;

		public Builder word(String word) {
			this.word = word;
			return this;
		}

		public Builder difficulty(Difficulty difficulty) {
			this.difficulty = difficulty;
			return this;
		}

		public Builder category(Category category) {
			this.category = category;
			return this;
		}

		public GameWord build() {
			return new GameWord(this);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((difficulty == null) ? 0 : difficulty.hashCode());
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		GameWord other = (GameWord) obj;
		if (category != other.category) return false;
		if (difficulty != other.difficulty) return false;
		if (word == null) {
			if (other.word != null) return false;
		} else if (!word.equals(other.word)) return false;
		return true;
	}

}
