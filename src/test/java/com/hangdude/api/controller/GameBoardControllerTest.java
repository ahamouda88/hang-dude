package com.hangdude.api.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hangdude.config.spring.SpringBootConfig;
import com.hangdude.model.Category;
import com.hangdude.model.Difficulty;
import com.hangdude.model.HangdudeBoard;
import com.hangdude.model.api.BoardRequest;
import com.hangdude.model.constants.PathConstants;
import com.hangdude.service.BoardService;
import com.hangdude.service.impl.HangdudeBoardService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringBootConfig.class, BoardService.class, HangdudeBoardService.class })
public class GameBoardControllerTest {

	private MockMvc mockMvc;
	private ObjectMapper mapper;

	@Autowired
	private GameBoardController gameController;

	/* Creating two sessions for two different users */
	private MockHttpSession session1 = new MockHttpSession();
	private MockHttpSession session2 = new MockHttpSession();

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);

		mapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();

		testAddBoard(Category.SPORT, Difficulty.EASY, "Ahmed", 0, "SOCCER", session1);
		testAddBoard(Category.ANIMAL, Difficulty.EASY, null, 0, "CAT", session2);
	}

	@Test
	public void testGetAllBoards() throws Exception {
		//@formatter:off
		mockMvc.perform(get(PathConstants.BOARDS_PATH))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$[0].dude.username", is("Ahmed")))
			   .andExpect(jsonPath("$[0].currentWord.word", is("SOCCER")))
			   .andExpect(jsonPath("$[1].dude.username", is("Anonymous")))
		 	   .andExpect(jsonPath("$[1].currentWord.word", is("CAT")));
		//@formatter:on
	}

	@Test
	public void testGetCategories() throws Exception {
		//@formatter:off
		mockMvc.perform(get(PathConstants.CATEGORIES))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$[0]", is(Category.SPORT.name())))
			   .andExpect(jsonPath("$[1]", is(Category.ANIMAL.name())));
		//@formatter:on
	}

	@Test
	public void testGetCurrentBoard() throws Exception {
		//@formatter:off
		mockMvc.perform(get(PathConstants.CURRENT_BOARD_PATH).session(session1))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$.dude.username", is("Ahmed")))
			   .andExpect(jsonPath("$.currentWord.word", is("SOCCER")));
		//@formatter:on
	}

	@Test
	public void testAddingCharacterCompleted() throws Exception {
		testAddCharacter('A', 0, "_A_", false, false, session2); // Test valid character

		testAddCharacter('Z', 1, "_A_", false, false, session2); // Test invalid character

		testAddCharacter('C', 1, "CA_", false, false, session2);

		testAddCharacter('H', 2, "CA_", false, false, session2);

		testAddCharacter('T', 2, "CAT", true, false, session2);
	}
	
	@Test
	public void testAddingCharacterFailed() throws Exception {
		testAddCharacter('Y', 1, "___", false, false, session2);

		testAddCharacter('Z', 2, "___", false, false, session2); 

		testAddCharacter('E', 3, "___", false, false, session2);

		testAddCharacter('X', 4, "___", false, false, session2);
		
		testAddCharacter('L', 5, "___", false, false, session2); 

		testAddCharacter('M', 6, "___", false, false, session2);

		testAddCharacter('N', 7, "___", false, false, session2);
		
		testAddCharacter('O', 8, "CAT", false, true, session2); 
	}

	private void testAddBoard(Category category, Difficulty difficulty, String username, int numOfAttempts,
			String expectedWord, MockHttpSession session) throws Exception {
		//@formatter:off
		String finalUsername = username == null ? "Anonymous" : username;
		char[] chars = new char[expectedWord.length()];
		Arrays.fill(chars, HangdudeBoard.EMPTY_CHARACTER);
		String finalWordState = new String(chars);
		
		BoardRequest boardRequest = new BoardRequest(category, difficulty, username);
		mockMvc.perform(post(PathConstants.BOARDS_PATH, boardRequest).session(session)
			   .contentType(MediaType.APPLICATION_JSON)
			   .content(mapper.writeValueAsString(boardRequest)))
			   .andExpect(status().isCreated())
			   .andExpect(jsonPath("$.dude", notNullValue()))
			   .andExpect(jsonPath("$.dude.username", is(finalUsername)))
			   .andExpect(jsonPath("$.numOfAttempts", is(numOfAttempts)))
			   .andExpect(jsonPath("$.currentWord.word", is(expectedWord)))
			   .andExpect(jsonPath("$.currentWord.difficulty", is(difficulty.name())))
			   .andExpect(jsonPath("$.currentWord.category", is(category.name())))
			   .andExpect(jsonPath("$.wordState", is(finalWordState)));
		//@formatter:on
	}

	private void testAddCharacter(char character, int numOfAttemtps, String expectedWordState, boolean isCompleted,
			boolean isFailed, MockHttpSession session) throws Exception {
		//@formatter:off
		mockMvc.perform(get(PathConstants.CURRENT_BOARD_PATH + "/" + character).session(session))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$.numOfAttempts", is(numOfAttemtps)))
			   .andExpect(jsonPath("$.wordState", is(expectedWordState)))
			   .andExpect(jsonPath("$.completed", is(isCompleted)))
			   .andExpect(jsonPath("$.failed", is(isFailed)));
		//@formatter:on
	}

}
