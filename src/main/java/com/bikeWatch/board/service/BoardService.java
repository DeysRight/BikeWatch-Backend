package com.bikeWatch.board.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bikeWatch.board.domain.Board;
import com.bikeWatch.board.dto.request.CreateBoardRequest;
import com.bikeWatch.board.dto.request.UpdateBoardRequest;
import com.bikeWatch.board.dto.response.CreateBoardResponse;
import com.bikeWatch.board.dto.response.FindBoardResponse;
import com.bikeWatch.board.dto.response.UpdateBoardResponse;
import com.bikeWatch.board.repository.BoardRepository;
import com.bikeWatch.common.error.ErrorCode;
import com.bikeWatch.common.error.exception.BadRequestException;
import com.bikeWatch.menu.domain.Menu;
import com.bikeWatch.menu.repository.MenuRepository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	private final MenuRepository menuRepository;
	private final EntityManager entityManager;

	@Transactional
	public CreateBoardResponse createBoard(CreateBoardRequest req, Long menuId) {
		Menu menu = menuRepository.findById(menuId)
			.orElseThrow(() -> new BadRequestException(ErrorCode.NOT_FOUND_MENU));
		Board board = boardRepository.save(req.toEntity(menu));

		return CreateBoardResponse.of(board);
	}

	@Transactional
	public UpdateBoardResponse updateBoard(UpdateBoardRequest req, Long boardId) {
		Board board = boardRepository.findById(boardId)
			.orElseThrow(() -> new BadRequestException(ErrorCode.NOT_FOUND_BOARD));
		Board updatedBoard = board.updateTitleAndContent(req.title(), req.content());

		entityManager.flush();

		return UpdateBoardResponse.of(updatedBoard);
	}

	public Page<FindBoardResponse> getListByKeyword(Pageable pageable, String keyword) {
		return boardRepository.getListByKeyword(pageable, keyword);
	}

	public Page<FindBoardResponse> getListByMenu(Pageable pageable, Long menuId) {
		return boardRepository.getListByMenu(pageable, menuId);
	}

	public void deleteBoard(Long boardId) {
		boardRepository.deleteById(boardId);
	}
}
