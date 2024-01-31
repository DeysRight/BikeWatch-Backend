package com.bikeWatch.board.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bikeWatch.board.domain.Board;
import com.bikeWatch.board.dto.request.BoardCreateRequest;
import com.bikeWatch.board.dto.response.BoardCreateResponse;
import com.bikeWatch.board.dto.response.BoardFindResponse;
import com.bikeWatch.board.repository.BoardRepository;
import com.bikeWatch.common.error.ErrorCode;
import com.bikeWatch.common.error.exception.BadRequestException;
import com.bikeWatch.menu.domain.Menu;
import com.bikeWatch.menu.repository.MenuRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	private final MenuRepository menuRepository;

	public Page<BoardFindResponse> getBoardListByKeyword(Pageable pageable, String keyword) {
		return boardRepository.getBoardListByKeyword(pageable, keyword);
	}

	public Page<BoardFindResponse> getBoardListByMenu(Pageable pageable, Long menuId) {
		return boardRepository.getBoardListByMenu(pageable, menuId);
	}

	@Transactional
	public BoardCreateResponse createBoard(BoardCreateRequest req, Long menuId) {
		Menu menu = menuRepository.findById(menuId)
			.orElseThrow(() -> new BadRequestException(ErrorCode.NOT_FOUND_MENU));
		Board board = boardRepository.save(req.toEntity(menu));

		return BoardCreateResponse.of(board);
	}
}
