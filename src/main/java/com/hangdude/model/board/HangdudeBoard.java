package com.hangdude.model.board;

import java.io.Serializable;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hangdude.model.Dude;
import com.hangdude.model.GameWord;

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

	private String fillEmptyCharacters() {
		char[] chars = new char[currentWord.getWord().length()];
		Arrays.fill(chars, EMPTY_CHARACTER);

		return new String(chars);
	}
}
