package com.hangdude.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hangdude.model.Category;
import com.hangdude.model.board.HangdudeBoard;
import com.hangdude.model.constants.PathConstants;
import com.hangdude.service.BoardService;

/**
 * A class which is a Rest Controller for managing hangdude game boards
 */
@RestController
@RequestMapping(value = PathConstants.BOARD_PATH)
public class GameBoardController {

	@Autowired
	private BoardService<HangdudeBoard, String> boardService;

	@RequestMapping(value = PathConstants.CATEGORIES, method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getCategories() throws Exception {
		return new ResponseEntity<List<Category>>(Arrays.asList(Category.values()), HttpStatus.OK);
	}
}
