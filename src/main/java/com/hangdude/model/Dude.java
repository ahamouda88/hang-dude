package com.hangdude.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A POJO class that represents a User
 * 
 * @author ahamouda
 */
public class Dude implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String username;
	private Set<GameWord> completedWords;

	private Dude(Builder builder) {
		this.id = builder.id;
		this.username = builder.username;
		this.completedWords = builder.completedWords == null ? new HashSet<>() : builder.completedWords;
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public Set<GameWord> getCompletedWords() {
		return completedWords;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String id;
		private String username;
		private Set<GameWord> completedWords;

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public Builder completedWords(Set<GameWord> completedWords) {
			this.completedWords = completedWords;
			return this;
		}

		public Dude build() {
			return new Dude(this);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((completedWords == null) ? 0 : completedWords.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Dude other = (Dude) obj;
		if (completedWords == null) {
			if (other.completedWords != null) return false;
		} else if (!completedWords.equals(other.completedWords)) return false;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		if (username == null) {
			if (other.username != null) return false;
		} else if (!username.equals(other.username)) return false;
		return true;
	}

}
