package com.bikeWatch.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bikeWatch.board.dto.response.FindBoardResponse;

public interface BoardRepositoryCustom {

	Page<FindBoardResponse> getListByKeyword(Pageable pageable, String keyword);

	Page<FindBoardResponse> getListByMenu(Pageable pageable, Long menuId);
}
