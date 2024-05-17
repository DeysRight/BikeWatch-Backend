package com.bikeWatch.review.dto.response;

import com.bikeWatch.review.domain.Review;
import com.querydsl.core.annotations.QueryProjection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

public record SelectReviewResponse(
	@Schema(description = "리뷰 번호")
	Long id,

	@Schema(description = "리뷰 제목")
	String title,

	@Schema(description = "리뷰 이미지 경로")
	String imagePath,

	@Schema(description = "리뷰 내용")
	String content) {

	@Builder
	@QueryProjection
	public SelectReviewResponse(Long id, String title, String imagePath, String content) {
		this.id = id;
		this.title = title;
		this.imagePath = imagePath;
		this.content = content;
	}

	public static SelectReviewResponse of(Review review) {
		return SelectReviewResponse.builder()
			.id(review.getId())
			.title(review.getTitle())
			.imagePath(review.getImagePath())
			.content(review.getContent())
			.build();
	}
}
