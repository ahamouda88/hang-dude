package com.hangdude.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

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

	private BoardService<HangdudeBoard, String> boardService = BoardServiceFactory.getInstance();

	@Before
	public void testCreateBoard() {
		boardService.removeAllBoards();

		createAndTestBoard("1", Category.COUNTRY, Difficulty.EASY);

		createAndTestBoard("2", Category.ANIMAL, Difficulty.EASY);

		createAndTestBoard("3", Category.FOOD, Difficulty.EASY);

		createAndTestBoard("4", Category.COUNTRY, Difficulty.EASY);
	}

	@Test
	public void testInvalidCreate() {
		HangdudeBoard board = boardService.createBoard("6", null, null);
		assertNull(board);
	}

	@Test
	public void testGetAllBoards() {
		testAllBoards(4);
	}

	@Test
	public void testExistingBoard() {
		Dude dude = Dude.builder().id("invalidDude").build();
		GameWord gameWord = WordFactory.getWord(Category.COUNTRY, Difficulty.EASY);

		HangdudeBoard board = boardService.createBoard("1", dude, gameWord);
		assertNull(board);
	}

	@Test
	public void testRemoveBoard() {
		boardService.removeBoard("2");
		testAllBoards(3);
	}

	@Test
	public void testInvalidRemove() {
		HangdudeBoard board1 = boardService.removeBoard(null);
		assertNull(board1);

		HangdudeBoard board2 = boardService.removeBoard("99");
		assertNull(board2);
	}

	@Test
	public void testGetBoard() {
		String id = "3";
		HangdudeBoard board = boardService.getBoard(id);

		assertEquals("user_" + id, board.getDude().getId());
		assertEquals(Category.FOOD, board.getCurrentWord().getCategory());
		assertEquals(Difficulty.EASY, board.getCurrentWord().getDifficulty());
	}

	@Test
	public void testInvalidGet() {
		HangdudeBoard board1 = boardService.getBoard(null);
		assertNull(board1);

		HangdudeBoard board2 = boardService.getBoard("999999");
		assertNull(board2);
	}

	@Test
	public void testUpdateBoard() {
		String id = "4";
		HangdudeBoard board = boardService.getBoard(id);
		board.setNumOfAttempts(2);

		assertTrue(boardService.updateBoard(id, board));
	}

	@Test
	public void testInvalidUpdate() {
		assertFalse(boardService.updateBoard(null, null));
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
		assertEquals(expectedSize, allBoards.size());
	}
}
