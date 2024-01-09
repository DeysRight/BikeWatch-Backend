package com.bikeWatch.board.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikeWatch.board.dto.request.BoardCreateRequest;
import com.bikeWatch.board.dto.response.BoardCreateResponse;
import com.bikeWatch.board.dto.response.BoardFindResponse;
import com.bikeWatch.common.domain.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

	@GetMapping
	public ApiResponse<List<BoardFindResponse>> getBoardList() {
		return ApiResponse.ok(
			List.of(new BoardFindResponse(1L, "testT", "testC"), new BoardFindResponse(2L, "testT2", "testC2")));
	}

	@PostMapping
	public ApiResponse<BoardCreateResponse> createBoard(@Valid @RequestBody BoardCreateRequest request) {
		return ApiResponse.of(HttpStatus.CREATED, new BoardCreateResponse(5L, "title", "content"));
	}
}
