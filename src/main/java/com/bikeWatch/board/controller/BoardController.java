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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "board-controller", description = "게시판 서비스를 위한 컨트롤러")
@RestController
@RequestMapping("/api/boards")
public class BoardController {

	@Operation(summary = "게시판 리스트 전체 조회 API", description = "해당 카테고리 게시판의 전체 리스트를 조회 합니다.")
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
