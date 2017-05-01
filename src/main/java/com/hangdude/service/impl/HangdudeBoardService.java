package com.hangdude.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hangdude.model.GameWord;
import com.hangdude.model.board.HangdudeBoard;
import com.hangdude.service.BoardService;
import com.hangdude.service.WordService;

/**
 * A class that implements the {@link BoardService} interface and extends main operations from {@link AbsMainService}
 * 
 * @author ahamouda
 *
 */
public class HangdudeBoardService extends AbsMainService<HangdudeBoard, String>
		implements BoardService<HangdudeBoard, String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(HangdudeBoardService.class);

	private final WordService<GameWord> wordService;

	public HangdudeBoardService(WordService<GameWord> wordService) {
		this.wordService = wordService;
	}

	/**
	 * @see BoardService#addCharacter(Character, Object)
	 */
	@Override
	public HangdudeBoard addCharacter(Character character, String key) {
		return null;
	}

}
