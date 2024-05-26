package com.bikeWatch.review.controller;

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

import com.bikeWatch.common.domain.ApiResponse;
import com.bikeWatch.review.dto.request.CreateReviewRequest;
import com.bikeWatch.review.dto.response.SelectReviewResponse;
import com.bikeWatch.review.service.ReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "review-controller", description = "리뷰 서비스를 위한 컨트롤러")
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewService reviewService;

	@Operation(summary = "리뷰 등록", description = "리뷰를 등록합니다.")
	@PostMapping("/admin")
	public ApiResponse<Void> addReview(@RequestBody @Valid CreateReviewRequest request) {
		reviewService.createReview(request);

		return ApiResponse.of(HttpStatus.CREATED, null);
	}

	@Operation(summary = "리뷰 키워드 조회", description = "리뷰를 키워드로 조회합니다.")
	@GetMapping
	public ApiResponse<Page<SelectReviewResponse>> getReviewsByKeyword(Pageable pageable,
		@RequestParam(name = "keyword", required = false) String keyword) {
		return ApiResponse.ok(reviewService.getReviewsByKeyword(pageable, keyword));
	}

	@Operation(summary = "리뷰 상세 조회", description = "리뷰를 상세 조회합니다.")
	@GetMapping("/{id}")
	public ApiResponse<SelectReviewResponse> getReviewDetail(@PathVariable(name = "id") Long id) {
		return ApiResponse.ok(reviewService.getReviewDetail(id));
	}

	@Operation(summary = "리뷰 수정", description = "리뷰를 수정합니다.")
	@PutMapping("/{id}/admin")
	public ApiResponse<Void> modifyReview(@PathVariable(name = "id") Long id,
		@RequestBody @Valid CreateReviewRequest request) {
		reviewService.updateReview(id, request);

		return ApiResponse.of(HttpStatus.CREATED, null);
	}

	@Operation(summary = "리뷰 삭제", description = "리뷰를 삭제합니다.")
	@DeleteMapping("/{id}/admin")
	public ApiResponse<Void> removeReview(@PathVariable(name = "id") Long id) {
		reviewService.deleteReview(id);

		return ApiResponse.of(HttpStatus.NO_CONTENT, null);
	}

}
