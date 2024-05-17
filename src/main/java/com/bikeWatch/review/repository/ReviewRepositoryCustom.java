package com.bikeWatch.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bikeWatch.review.dto.response.SelectReviewResponse;

public interface ReviewRepositoryCustom {

	Page<SelectReviewResponse> getReviewsByKeyword(Pageable pageable, String keyword);
}
