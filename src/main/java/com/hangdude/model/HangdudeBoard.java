package com.hangdude.model;

import java.io.Serializable;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A POJO class that represents a game board which consists mainly of a {@link Dude}, {@link GameWord}, and manage the
 * state of the current word
 * 
 * @author ahamouda
 */
public class HangdudeBoard implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(HangdudeBoard.class);

	public static final char EMPTY_CHARACTER = '_';

	private Dude dude;
	private GameWord currentWord;
	private String wordState;
	private int numOfAttempts;
	/* A variable to keep track of clicked and attempted chars */
	private boolean[] clickedChars;

	public HangdudeBoard() {
	}

	public HangdudeBoard(Dude dude, GameWord currentWord) {
		if (dude == null || currentWord == null || currentWord.getWord() == null) {
			LOGGER.error("Failed to create a Hangdude board, due to invalid parameters. Dude: {}, and GameWord: {}.",
					dude, currentWord);
		} else {
			this.dude = dude;
			this.currentWord = currentWord;
			this.wordState = fillEmptyCharacters();
			this.clickedChars = new boolean[26];
		}
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

	public int getNumOfAttempts() {
		return numOfAttempts;
	}

	public void setNumOfAttempts(int numOfAttempts) {
		this.numOfAttempts = numOfAttempts;
	}

	public String getWordState() {
		return wordState;
	}

	public void setWordState(String wordState) {
		this.wordState = wordState;
	}

	public boolean[] getClickedChars() {
		return clickedChars;
	}

	public void setClickedChars(boolean[] clickedChars) {
		this.clickedChars = clickedChars;
	}

	private String fillEmptyCharacters() {
		char[] chars = new char[currentWord.getWord().length()];
		Arrays.fill(chars, EMPTY_CHARACTER);

		return new String(chars);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(clickedChars);
		result = prime * result + ((currentWord == null) ? 0 : currentWord.hashCode());
		result = prime * result + ((dude == null) ? 0 : dude.hashCode());
		result = prime * result + numOfAttempts;
		result = prime * result + ((wordState == null) ? 0 : wordState.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		HangdudeBoard other = (HangdudeBoard) obj;
		if (!Arrays.equals(clickedChars, other.clickedChars)) return false;
		if (currentWord == null) {
			if (other.currentWord != null) return false;
		} else if (!currentWord.equals(other.currentWord)) return false;
		if (dude == null) {
			if (other.dude != null) return false;
		} else if (!dude.equals(other.dude)) return false;
		if (numOfAttempts != other.numOfAttempts) return false;
		if (wordState == null) {
			if (other.wordState != null) return false;
		} else if (!wordState.equals(other.wordState)) return false;
		return true;
	}

}
