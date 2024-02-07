package com.bikeWatch.board.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bikeWatch.board.dto.request.CreateBoardRequest;
import com.bikeWatch.board.dto.request.UpdateBoardRequest;
import com.bikeWatch.board.dto.response.CreateBoardResponse;
import com.bikeWatch.board.dto.response.FindBoardResponse;
import com.bikeWatch.board.dto.response.UpdateBoardResponse;
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

	@Operation(summary = "게시글 등록", description = "선택한 메뉴에 게시글을 등록합니다.")
	@PostMapping("/{menuId}")
	public ApiResponse<CreateBoardResponse> createBoard(@RequestBody @Valid CreateBoardRequest req,
		@PathVariable(name = "menuId") Long menuId) {
		return ApiResponse.of(HttpStatus.CREATED, boardService.createBoard(req, menuId));
	}

	@Operation(summary = "게시글 수정", description = "게시글을 수정합니다.")
	@PutMapping("/{boardId}")
	public ApiResponse<UpdateBoardResponse> updateBoard(@RequestBody @Valid UpdateBoardRequest req,
		@PathVariable(name = "boardId") Long boardId) {
		return ApiResponse.of(HttpStatus.CREATED, boardService.updateBoard(req, boardId));
	}

	@Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
	@DeleteMapping("{boardId}")
	public ApiResponse<Void> deleteBoard(@PathVariable(name = "boardId") Long boardId) {
		boardService.deleteBoard(boardId);
		return ApiResponse.of(HttpStatus.NO_CONTENT, null);
	}

	@Operation(summary = "게시판 조회 API by.키워드", description = "제목 또는 내용에 키워드를 포함하는 게시판을 조회합니다.")
	@GetMapping
	public ApiResponse<Page<FindBoardResponse>> getBoardListByKeyword(Pageable pageable,
		@RequestParam(name = "keyword", required = false) String keyword) {
		return ApiResponse.ok(boardService.getListByKeyword(pageable, keyword));
	}

	@Operation(summary = "게시판 조회 API by.메뉴", description = "선택한 메뉴의 게시판을 조회합니다.")
	@GetMapping("/{menuId}")
	public ApiResponse<Page<FindBoardResponse>> getBoardListByMenu(Pageable pageable,
		@PathVariable(name = "menuId") Long menuId) {
		return ApiResponse.ok(boardService.getListByMenu(pageable, menuId));
	}
}
