package com.hangdude.utils.factory;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.common.collect.ImmutableSet;
import com.hangdude.model.Category;
import com.hangdude.model.Difficulty;
import com.hangdude.model.GameWord;
import com.hangdude.utils.factory.WordFactory;

public class WordFactoryTest {

	@Test
	public void testGetWord() {
		GameWord gameword = WordFactory.getWord(Category.SPORT, Difficulty.EASY);

		assertEquals(gameword.getWord(), "SOCCER");
		assertEquals(gameword.getCategory(), Category.SPORT);
		assertEquals(gameword.getDifficulty(), Difficulty.EASY);
	}

	@Test
	public void testGetWordWithExcludedParam() {
		GameWord country1 = GameWord.builder().category(Category.COUNTRY).difficulty(Difficulty.EASY).word("GERMANY")
				.build();

		GameWord gameword1 = WordFactory.getWord(Category.COUNTRY, Difficulty.EASY, ImmutableSet.of(country1));
		assertNull(gameword1);

		GameWord gameword2 = WordFactory.getWord(Category.COUNTRY, Difficulty.EASY);
		assertNotNull(gameword2);
	}

	@Test
	public void testInvalidParameters() {
		GameWord gameword1 = WordFactory.getWord(null, Difficulty.EASY);
		assertNull(gameword1);

		GameWord gameword2 = WordFactory.getWord(Category.ANIMAL, null);
		assertNull(gameword2);
	}
	
	@Test
	public void testNonexistingCategory() {
		GameWord gameword1 = WordFactory.getWord(Category.CITY, Difficulty.EASY);
		assertNull(gameword1);
	}
}
