package com.bikeWatch.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bikeWatch.board.dto.response.BoardFindResponse;

public interface BoardRepositoryCustom {

	Page<BoardFindResponse> getBoardListByKeyword(Pageable pageable, String keyword);

	Page<BoardFindResponse> getBoardListByMenu(Pageable pageable, Long menuId);
}
