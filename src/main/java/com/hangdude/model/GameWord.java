package com.hangdude.model;

import static org.apache.commons.lang.StringUtils.isEmpty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

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
	/** A map to manage the positions of each character in the word **/
	private Map<Character, List<Integer>> charPositions;

	private GameWord(Builder builder) {
		this.word = builder.word;
		this.difficulty = builder.difficulty;
		this.category = builder.category;
		this.charPositions = allocateCharacters();
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

	public Map<Character, List<Integer>> getCharPositions() {
		return ImmutableMap.copyOf(charPositions);
	}

	public static Builder builder() {
		return new Builder();
	}

	/** Form the map of characters, and their positions **/
	private Map<Character, List<Integer>> allocateCharacters() {
		Map<Character, List<Integer>> chars = new HashMap<>();
		if (isEmpty(word)) return chars;

		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			List<Integer> list = chars.get(c);

			if (list == null) list = new ArrayList<>();

			list.add(i);
			chars.put(c, list);
		}
		return ImmutableMap.copyOf(chars);
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
		result = prime * result + ((charPositions == null) ? 0 : charPositions.hashCode());
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
		if (charPositions == null) {
			if (other.charPositions != null) return false;
		} else if (!charPositions.equals(other.charPositions)) return false;
		if (difficulty != other.difficulty) return false;
		if (word == null) {
			if (other.word != null) return false;
		} else if (!word.equals(other.word)) return false;
		return true;
	}

	@Override
	public String toString() {
		return "[word=" + word + ", difficulty=" + difficulty + ", category=" + category + ", charPositions="
				+ charPositions + "]";
	}

}
