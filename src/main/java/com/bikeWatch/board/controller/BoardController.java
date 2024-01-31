package com.bikeWatch.board.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bikeWatch.board.dto.request.BoardCreateRequest;
import com.bikeWatch.board.dto.response.BoardCreateResponse;
import com.bikeWatch.board.dto.response.BoardFindResponse;
import com.bikeWatch.board.service.BoardService;
import com.bikeWatch.common.domain.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "board-controller", description = "게시판 서비스를 위한 컨트롤러")
@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	@Operation(summary = "게시판 조회 API by.키워드", description = "제목 또는 내용에 키워드를 포함하는 게시판을 조회합니다.")
	@GetMapping
	public ApiResponse<Page<BoardFindResponse>> getBoardListByKeyword(Pageable pageable,
		@RequestParam(name = "keyword") String keyword) {
		return ApiResponse.ok(boardService.getBoardListByKeyword(pageable, keyword));
	}

	@Operation(summary = "게시판 조회 API by.메뉴", description = "선택한 메뉴의 게시판을 조회합니다.")
	@GetMapping("/{menuId}")
	public ApiResponse<Page<BoardFindResponse>> getBoardListByMenu(Pageable pageable,
		@PathVariable(name = "menuId") Long menuId) {
		return ApiResponse.ok(boardService.getBoardListByMenu(pageable, menuId));
	}

	@Operation(summary = "게시글 등록", description = "선택한 메뉴에 게시글을 등록합니다.")
	@PostMapping("/{menuId}")
	public ApiResponse<BoardCreateResponse> createBoard(@Valid @RequestBody BoardCreateRequest req,
		@PathVariable(name = "menuId") Long menuId) {
		return ApiResponse.of(HttpStatus.CREATED, boardService.createBoard(req, menuId));
	}

}
