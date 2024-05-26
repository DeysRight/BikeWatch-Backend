package com.bikeWatch.review.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bikeWatch.common.error.ErrorCode;
import com.bikeWatch.common.error.exception.BadRequestException;
import com.bikeWatch.review.domain.Review;
import com.bikeWatch.review.dto.request.CreateReviewRequest;
import com.bikeWatch.review.dto.response.SelectReviewResponse;
import com.bikeWatch.review.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

	private final ReviewRepository reviewRepository;

	@Transactional
	public void createReview(CreateReviewRequest request) {
		reviewRepository.save(request.toEntity());
	}

	public Page<SelectReviewResponse> getReviewsByKeyword(Pageable pageable, String keyword) {
		return reviewRepository.getReviewsByKeyword(pageable, keyword);
	}

	public SelectReviewResponse getReviewDetail(Long id) {
		return SelectReviewResponse.of(reviewRepository.findById(id)
			.orElseThrow(() -> new BadRequestException(ErrorCode.NOT_FOUND_REVIEW)));
	}

	@Transactional
	public void updateReview(Long id, CreateReviewRequest request) {
		Review review = reviewRepository.findById(id)
			.orElseThrow(() -> new BadRequestException(ErrorCode.NOT_FOUND_REVIEW));

		review.updateReview(request.title(), request.imagePath(), request.content());
	}

	@Transactional
	public void deleteReview(Long id) {
		Review review = reviewRepository.findById(id)
			.orElseThrow(() -> new BadRequestException(ErrorCode.NOT_FOUND_REVIEW));

		reviewRepository.delete(review);
	}
}
