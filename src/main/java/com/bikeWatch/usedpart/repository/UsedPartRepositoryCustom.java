package com.bikeWatch.usedpart.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bikeWatch.usedpart.dto.response.SelectUsedPartResponse;

public interface UsedPartRepositoryCustom {

	Page<SelectUsedPartResponse> getUsedPartsByKeyword(Pageable pageable, String keyword);
}
