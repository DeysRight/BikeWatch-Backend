package com.bikeWatch.review.dto.request;

import com.bikeWatch.review.domain.Review;

import jakarta.validation.constraints.NotBlank;

public record CreateReviewRequest(

	@NotBlank(message = "제목을 입력해주세요.")
	String title,

	String imagePath,

	@NotBlank(message = "내용을 입력해주세요.")
	String content) {

	public Review toEntity() {
		return Review.builder()
			.title(title)
			.imagePath(imagePath)
			.content(content)
			.build();
	}
}
