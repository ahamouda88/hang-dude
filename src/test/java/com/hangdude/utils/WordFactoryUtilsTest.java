package com.hangdude.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.common.collect.ImmutableSet;
import com.hangdude.model.Category;
import com.hangdude.model.Difficulty;
import com.hangdude.model.GameWord;

public class WordFactoryUtilsTest {

	@Test
	public void testGetWord() {
		GameWord gameword = WordFactoryUtils.getWord(Category.SPORT, Difficulty.EASY);

		assertEquals(gameword.getWord(), "SOCCER");
		assertEquals(gameword.getCategory(), Category.SPORT);
		assertEquals(gameword.getDifficulty(), Difficulty.EASY);
	}

	@Test
	public void testGetWordWithExcludedParam() {
		GameWord country1 = GameWord.builder().category(Category.COUNTRY).difficulty(Difficulty.EASY).word("GERMANY")
				.build();

		GameWord gameword1 = WordFactoryUtils.getWord(Category.COUNTRY, Difficulty.EASY, ImmutableSet.of(country1));
		assertNull(gameword1);

		GameWord gameword2 = WordFactoryUtils.getWord(Category.COUNTRY, Difficulty.EASY);
		assertNotNull(gameword2);
	}

	@Test
	public void testInvalidParameters() {
		GameWord gameword1 = WordFactoryUtils.getWord(null, Difficulty.EASY);
		assertNull(gameword1);

		GameWord gameword2 = WordFactoryUtils.getWord(Category.ANIMAL, null);
		assertNull(gameword2);
	}
	
	@Test
	public void testNonexistingCategory() {
		GameWord gameword1 = WordFactoryUtils.getWord(Category.CITY, Difficulty.EASY);
		assertNull(gameword1);
	}
}
