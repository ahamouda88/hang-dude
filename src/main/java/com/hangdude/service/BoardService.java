package com.hangdude.service;

import java.util.List;

import com.hangdude.model.Board;
import com.hangdude.model.Category;
import com.hangdude.model.Difficulty;
import com.hangdude.model.GameWord;

public interface BoardService {

	public GameWord getGameWord(Category category, Difficulty difficulty);

	public List<GameWord> getAllBoards();

	public Board getBoard(String email);

}
