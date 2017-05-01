package com.hangdude.utils.factory;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
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

	@Test
	public void testCharPositions() {
		GameWord gameword = WordFactory.getWord(Category.SPORT, Difficulty.EASY);
		Map<Character, List<Integer>> expectedCharPositions = new HashMap<>();
		expectedCharPositions.put('S', ImmutableList.of(0));
		expectedCharPositions.put('O', ImmutableList.of(1));
		expectedCharPositions.put('C', ImmutableList.of(2, 3));
		expectedCharPositions.put('E', ImmutableList.of(4));
		expectedCharPositions.put('R', ImmutableList.of(5));

		assertEquals(expectedCharPositions, gameword.getCharPositions());
	}
}
