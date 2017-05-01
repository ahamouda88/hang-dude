package com.hangdude.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.hangdude.model.Category;
import com.hangdude.model.Difficulty;
import com.hangdude.model.Dude;
import com.hangdude.model.GameWord;
import com.hangdude.model.board.HangdudeBoard;
import com.hangdude.utils.factory.BoardServiceFactory;
import com.hangdude.utils.factory.WordFactory;

public class HangdudeBoardServiceTest {

	private BoardService<HangdudeBoard, String> boardService;

	@Before
	public void testCreateBoard() {
		boardService = BoardServiceFactory.getInstance();
		
		createAndTestBoard("1", Category.COUNTRY, Difficulty.EASY);

		createAndTestBoard("1", Category.ANIMAL, Difficulty.EASY);

		createAndTestBoard("3", Category.FOOD, Difficulty.EASY);

		createAndTestBoard("4", Category.COUNTRY, Difficulty.EASY);
	}

	@Test
	public void testGetAllBoards() {
		testAllBoards(4);
	}

	private void createAndTestBoard(String id, Category category, Difficulty difficulty) {
		Dude dude = Dude.builder().id("user_" + id).build();
		GameWord gameWord = WordFactory.getWord(category, difficulty);

		HangdudeBoard board = boardService.createBoard(id, dude, gameWord);

		assertEquals(dude, board.getDude());
		assertEquals(gameWord, board.getCurrentWord());
		assertEquals(StringUtils.EMPTY, board.getWordState());
		assertEquals(0, board.getNumOfAttempts());
	}

	private void testAllBoards(int expectedSize) {
		List<HangdudeBoard> allBoards = boardService.getAllBoards();
		assertEquals(4, allBoards.size());
	}
}
