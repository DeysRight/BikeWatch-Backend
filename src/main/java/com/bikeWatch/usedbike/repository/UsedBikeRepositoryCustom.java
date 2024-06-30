package com.bikeWatch.usedbike.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bikeWatch.usedbike.dto.response.SelectUsedBikeResponse;

public interface UsedBikeRepositoryCustom {

	Page<SelectUsedBikeResponse> getUsedBikesByKeyword(Pageable pageable, String keyword);
}
