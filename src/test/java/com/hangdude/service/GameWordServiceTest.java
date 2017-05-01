package com.hangdude.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.hangdude.model.Category;
import com.hangdude.model.Difficulty;
import com.hangdude.model.GameWord;
import com.hangdude.service.impl.GameWordService;

public class GameWordServiceTest {

	private final WordService<GameWord> wordService = new GameWordService();

	@Test
	public void testAddCharacter() {
		GameWord gameWord = wordService.getWord(Category.SPORT, Difficulty.EASY);
		String initialWord = "______";

		String word = wordService.addCharacter('C', initialWord, gameWord);
		assertEquals("__CC__", word);

		String newWord = wordService.addCharacter('S', word, gameWord);
		assertEquals("S_CC__", newWord);
	}

	@Test
	public void testInvalidAdd() {
		GameWord gameWord = wordService.getWord(Category.SPORT, Difficulty.EASY);
		String initialWord = "______";

		// Testing null parameters
		String word = wordService.addCharacter(null, initialWord, gameWord);
		assertNull(word);

		// Testing a none existing character
		String newWord = wordService.addCharacter('A', initialWord, gameWord);
		assertNull(newWord);
	}
}
