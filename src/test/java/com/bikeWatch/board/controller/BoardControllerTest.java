package com.bikeWatch.board.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bikeWatch.board.dto.request.CreateBoardRequest;
import com.bikeWatch.board.service.BoardService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = BoardController.class)
class BoardControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BoardService boardService;

	@Autowired
	private ObjectMapper objectMapper;

	@DisplayName("신규 게시글을 등록한다.")
	@Test
	void 게시글_등록() throws Exception {
		// given
		CreateBoardRequest request =
			new CreateBoardRequest("슈퍼커브", "슈퍼커브는 ~ 이다.");
		Long menuId = 1L;

		// when & then
		mockMvc.perform(MockMvcRequestBuilders.post("/api/boards/" + menuId)
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.code").value("201"))
			.andExpect(jsonPath("$.httpStatus").value("CREATED"))
			.andExpect(jsonPath("$.message").value("CREATED"));
	}
}
