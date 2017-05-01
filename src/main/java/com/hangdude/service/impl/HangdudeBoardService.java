package com.hangdude.service.impl;

import static org.apache.commons.lang.StringUtils.isEmpty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;
import com.hangdude.model.Dude;
import com.hangdude.model.GameWord;
import com.hangdude.model.board.HangdudeBoard;
import com.hangdude.service.BoardService;

/**
 * A class that implements the {@link BoardService} interface
 * 
 * @author ahamouda
 *
 */
public class HangdudeBoardService implements BoardService<HangdudeBoard, String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(HangdudeBoardService.class);

	/** A variable that keeps track of currently being played boards **/
	private Map<String, HangdudeBoard> boards = new HashMap<>();

	/**
	 * @see BoardService#getAllBoards()
	 */
	@Override
	public List<HangdudeBoard> getAllBoards() {
		return ImmutableList.copyOf(boards.values());
	}

	/**
	 * @see BoardService#getBoard(Object)
	 */
	@Override
	public HangdudeBoard getBoard(String key) {
		if (isEmpty(key)) {
			LOGGER.error("Failed to return a game board, due to invalid parameter. Key: {}.", key);
			return null;
		}

		return boards.get(key);
	}

	/**
	 * @see BoardService#createBoard(Dude, GameWord)
	 */
	@Override
	public HangdudeBoard createBoard(String key, Dude dude, GameWord gameWord) {
		if (dude == null || gameWord == null) {
			LOGGER.error(
					"Failed to create a game board, due to invalid parameters. Key: {}, Dude: {}, and GameWord: {}.",
					key, dude, gameWord);
			return null;
		}

		if (boards.containsKey(key)) {
			LOGGER.error("Failed to create a game board. Board with the given key '{}' already exists.", key);
			return null;
		}
		HangdudeBoard newBoard = new HangdudeBoard(dude, gameWord);
		boards.put(key, newBoard);

		return newBoard;
	}

	/**
	 * @see BoardService#updateBoard(Object, Object)
	 */
	@Override
	public boolean updateBoard(String key, HangdudeBoard board) {
		if (isEmpty(key) || board == null) {
			LOGGER.error("Failed to update a game board, due to invalid parameters. key: {}, and Board: {}.", key,
					board);
			return false;
		}

		if (!boards.containsKey(key)) {
			LOGGER.error("Failed to update a game board. Board with the given key '{}' doesn't exist.", key);
			return false;
		}
		boards.put(key, board);

		return true;
	}

	/**
	 * @see BoardService#removeBoard(Object)
	 */
	@Override
	public HangdudeBoard removeBoard(String key) {
		if (isEmpty(key)) {
			LOGGER.error("Failed to remove a game board, due to invalid parameter. key: {}.", key);
			return null;
		}

		if (!boards.containsKey(key)) {
			LOGGER.error("Failed to remove a game board. Board with the given key '{}', doesn't exist.", key);
			return null;
		}

		return boards.remove(key);
	}

	/**
	 * @see BoardService#removeAllBoards()
	 */
	@Override
	public void removeAllBoards() {
		boards.clear();
	}

}
