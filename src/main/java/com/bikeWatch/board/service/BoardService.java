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
			.orElseThrow(() -> new BadRequestException(ErrorCode.NOT_FOUND_REVIEW));
		Board updatedBoard = board.updateTitleAndContent(req.title(), req.content());

		entityManager.flush();

		return UpdateBoardResponse.of(updatedBoard);
	}

	public Page<FindBoardResponse> getListByKeyword(Pageable pageable, String keyword) {
		Page<FindBoardResponse> listByKeyword = boardRepository.getListByKeyword(pageable, keyword);

		if (listByKeyword.isEmpty()) {
			throw new BadRequestException(ErrorCode.NOT_FOUND_REVIEW);
		}
		return listByKeyword;
	}

	public Page<FindBoardResponse> getListByMenu(Pageable pageable, Long menuId) {
		Page<FindBoardResponse> listByMenu = boardRepository.getListByMenu(pageable, menuId);

		if (listByMenu.isEmpty()) {
			throw new BadRequestException(ErrorCode.NOT_FOUND_REVIEW);
		}
		return listByMenu;
	}

	public void deleteBoard(Long boardId) {
		boardRepository.deleteById(boardId);
	}

	public Page<FindBoardResponse> getBoardListByMenuAndKeyword(Pageable pageable, Long menuId, String keyword) {
		Page<FindBoardResponse> findBoardResponses = boardRepository.getBoardListByMenuAndKeyword(pageable,
			menuId, keyword);

		if (findBoardResponses.isEmpty()) {
			throw new BadRequestException(ErrorCode.NOT_FOUND_REVIEW);
		}
		return findBoardResponses;
	}
}
