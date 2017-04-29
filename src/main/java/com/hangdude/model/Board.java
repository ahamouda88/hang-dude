package com.hangdude.model;

import java.util.HashSet;
import java.util.Set;

/**
 * A POJO class that represents a game board which consists mainly of a {@link Dude}, {@link GameWord}, and a set of
 * completed game words
 * 
 * @author ahamouda
 */
public class Board {

	private Dude dude;
	private GameWord currentWord;
	private Set<GameWord> completedWords;

	public Board() {
	}

	public Board(Dude dude, GameWord currentWord) {
		this.dude = dude;
		this.currentWord = currentWord;
		this.completedWords = new HashSet<>();
	}

	public Dude getDude() {
		return dude;
	}

	public void setDude(Dude dude) {
		this.dude = dude;
	}

	public GameWord getCurrentWord() {
		return currentWord;
	}

	public void setCurrentWord(GameWord currentWord) {
		this.currentWord = currentWord;
	}

	public Set<GameWord> getCompletedWords() {
		return completedWords;
	}

	public void setCompletedWords(Set<GameWord> completedWords) {
		this.completedWords = completedWords;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((completedWords == null) ? 0 : completedWords.hashCode());
		result = prime * result + ((currentWord == null) ? 0 : currentWord.hashCode());
		result = prime * result + ((dude == null) ? 0 : dude.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Board other = (Board) obj;
		if (completedWords == null) {
			if (other.completedWords != null) return false;
		} else if (!completedWords.equals(other.completedWords)) return false;
		if (currentWord == null) {
			if (other.currentWord != null) return false;
		} else if (!currentWord.equals(other.currentWord)) return false;
		if (dude == null) {
			if (other.dude != null) return false;
		} else if (!dude.equals(other.dude)) return false;
		return true;
	}

}
