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
	public void testAddBoard() {
		boardService.removeAll();

		createAndTestBoard("1", Category.COUNTRY, Difficulty.EASY);

		createAndTestBoard("2", Category.ANIMAL, Difficulty.EASY);

		createAndTestBoard("3", Category.FOOD, Difficulty.EASY);

		createAndTestBoard("4", Category.COUNTRY, Difficulty.EASY);
	}

	@Test
	public void testInvalidAdd() {
		boolean check = boardService.addElement("6", null);
		assertFalse(check);
	}

	@Test
	public void testGetAllBoards() {
		testAllBoards(4);
	}

	@Test
	public void testExistingBoard() {
		Dude dude = Dude.builder().id("invalidDude").build();
		GameWord gameWord = WordFactory.getWord(Category.COUNTRY, Difficulty.EASY);
		HangdudeBoard board = new HangdudeBoard(dude, gameWord);

		boolean check = boardService.addElement("1", board);
		assertFalse(check);
	}

	@Test
	public void testRemoveBoard() {
		boardService.removeElement("2");
		testAllBoards(3);
	}

	@Test
	public void testInvalidRemove() {
		HangdudeBoard board1 = boardService.removeElement(null);
		assertNull(board1);

		HangdudeBoard board2 = boardService.removeElement("99");
		assertNull(board2);
	}

	@Test
	public void testGetBoard() {
		String id = "3";
		HangdudeBoard board = boardService.getElement(id);

		assertEquals("user_" + id, board.getDude().getId());
		assertEquals(Category.FOOD, board.getCurrentWord().getCategory());
		assertEquals(Difficulty.EASY, board.getCurrentWord().getDifficulty());
	}

	@Test
	public void testInvalidGet() {
		HangdudeBoard board1 = boardService.getElement(null);
		assertNull(board1);

		HangdudeBoard board2 = boardService.getElement("999999");
		assertNull(board2);
	}

	@Test
	public void testUpdateBoard() {
		String id = "4";
		HangdudeBoard board = boardService.getElement(id);
		board.setNumOfAttempts(2);

		assertTrue(boardService.updateElement(id, board));
	}

	@Test
	public void testInvalidUpdate() {
		assertFalse(boardService.updateElement(null, null));
	}

	private void createAndTestBoard(String id, Category category, Difficulty difficulty) {
		Dude dude = Dude.builder().id("user_" + id).build();
		GameWord gameWord = WordFactory.getWord(category, difficulty);
		HangdudeBoard board = new HangdudeBoard(dude, gameWord);

		boolean check = boardService.addElement(id, board);

		assertTrue(check);
		assertEquals(dude, board.getDude());
		assertEquals(gameWord, board.getCurrentWord());
		assertEquals(StringUtils.EMPTY, board.getWordState());
		assertEquals(0, board.getNumOfAttempts());
	}

	private void testAllBoards(int expectedSize) {
		List<HangdudeBoard> allBoards = boardService.getAll();
		assertEquals(expectedSize, allBoards.size());
	}
}
