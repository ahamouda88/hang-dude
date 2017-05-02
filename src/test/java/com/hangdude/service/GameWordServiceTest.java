package com.hangdude.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hangdude.config.SpringBootConfig;
import com.hangdude.model.Category;
import com.hangdude.model.Difficulty;
import com.hangdude.model.GameWord;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringBootConfig.class })
public class GameWordServiceTest {

	@Autowired
	private WordService<GameWord> wordService;

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
