package com.hangdude.api.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hangdude.api.request.BoardRequest;
import com.hangdude.config.spring.SpringBootConfig;
import com.hangdude.model.Category;
import com.hangdude.model.Difficulty;
import com.hangdude.model.constants.PathConstants;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringBootConfig.class })
@WebAppConfiguration
@Ignore
public class GameBoardControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;
	private ObjectMapper mapper;

	@Before
	public void setUp() throws Exception {
		mapper = new ObjectMapper();
		mockMvc = webAppContextSetup(webApplicationContext).build();

//		BoardRequest boardRequest = new BoardRequest(Category.SPORT, Difficulty.EASY, "Ahmed");
//		
//		mockMvc.perform(post(PathConstants.BOARD_PATH).contentType(MediaType.APPLICATION_JSON)
//				.content(mapper.writeValueAsString(boardRequest))).andExpect(status().isOk());
////				.andExpect(jsonPath("$.id", Matchers.nullValue())).andDo(print());
	}

//	@Test
//	public void testGetCurrentBoard() throws Exception {
//		mockMvc.perform(get(PathConstants.BOARD_PATH)).andExpect(status().isOk()).andDo(print());
//	}

	@Test
	public void testGetCategories() throws Exception {
		String path = PathConstants.BOARD_PATH + PathConstants.CATEGORIES;
		mockMvc.perform(get(path)).andExpect(status().isOk());
				// .andExpect(jsonPath("$[0].title", Matchers.startsWith("ahmed")))
//				.andDo(print());
	}

}
