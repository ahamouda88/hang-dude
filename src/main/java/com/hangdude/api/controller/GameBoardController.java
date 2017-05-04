package com.hangdude.api.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hangdude.api.request.BoardRequest;
import com.hangdude.model.Category;
import com.hangdude.model.HangdudeBoard;
import com.hangdude.model.constants.PathConstants;
import com.hangdude.service.BoardService;

/**
 * A class which is a Rest Controller for managing hangdude game boards
 */
@RestController
public class GameBoardController {

	@Autowired
	private BoardService<HangdudeBoard, String> boardService;

	private final static String USER_ID = "USERID";

	@RequestMapping(value = PathConstants.CATEGORIES, method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getCategories() throws Exception {
		return new ResponseEntity<>(Arrays.asList(Category.values()), HttpStatus.OK);
	}

	@RequestMapping(value = PathConstants.CURRENT_BOARD_PATH, method = RequestMethod.GET)
	public ResponseEntity<HangdudeBoard> getCurrentBoard(HttpSession session) throws Exception {
		Object obj = session.getAttribute(USER_ID);

		HangdudeBoard board = obj instanceof HangdudeBoard ? (HangdudeBoard) obj : null;
		return new ResponseEntity<>(board, HttpStatus.OK);
	}

	@RequestMapping(value = PathConstants.CURRENT_BOARD_PATH, method = RequestMethod.POST)
	public ResponseEntity<HangdudeBoard> addGameBoard(HttpSession session, @RequestBody BoardRequest request)
			throws Exception {
		/* Note: in this implementation, I'm using the session id as the key */
		HangdudeBoard board = boardService.addUpdateBoard(session.getId(), request);

		if (board != null) session.setAttribute(USER_ID, board);
		return createResponse(board);
	}

	@RequestMapping(value = PathConstants.BOARDS_PATH, method = RequestMethod.GET)
	public ResponseEntity<List<HangdudeBoard>> getAllBoards() throws Exception {

		return createResponse(boardService.getAll());
	}

	@RequestMapping(value = PathConstants.CHARACTER_PATH, method = RequestMethod.GET)
	public ResponseEntity<HangdudeBoard> addCharacter(HttpSession session, @PathVariable("char") Character character)
			throws Exception {

		HangdudeBoard board = boardService.addCharacter(character, session.getId());
		return createResponse(board);
	}

	private <T> ResponseEntity<T> createResponse(T data) {
		return data == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(data, HttpStatus.OK);
	}
}
