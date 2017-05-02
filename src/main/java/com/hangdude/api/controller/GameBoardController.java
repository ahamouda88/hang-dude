package com.hangdude.api.controller;

import static org.springframework.util.StringUtils.isEmpty;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hangdude.api.request.BoardRequest;
import com.hangdude.model.Category;
import com.hangdude.model.Dude;
import com.hangdude.model.GameWord;
import com.hangdude.model.HangdudeBoard;
import com.hangdude.model.constants.PathConstants;
import com.hangdude.service.BoardService;
import com.hangdude.service.WordService;

/**
 * A class which is a Rest Controller for managing hangdude game boards
 */
@RestController
@RequestMapping(value = PathConstants.BOARD_PATH)
public class GameBoardController {

	@Autowired
	private BoardService<HangdudeBoard, String> boardService;

	@Autowired
	private WordService<GameWord> wordService;

	@RequestMapping(value = PathConstants.CATEGORIES, method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getCategories() throws Exception {
		return new ResponseEntity<List<Category>>(Arrays.asList(Category.values()), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<HangdudeBoard> addGameBoard(HttpSession session, @RequestBody BoardRequest request)
			throws Exception {

		String username = isEmpty(request.getUsername()) ? "Anonymous" : request.getUsername();
		Dude dude = Dude.builder().id(session.getId()).username(username).build();
		GameWord gameWord = wordService.getWord(request.getCategory(), request.getDifficulty());

		HangdudeBoard board = new HangdudeBoard(dude, gameWord);
		boolean check = boardService.addElement(session.getId(), board);

		if (check) {
			return new ResponseEntity<>(board, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
