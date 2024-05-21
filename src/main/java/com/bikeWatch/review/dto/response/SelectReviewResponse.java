package com.bikeWatch.review.dto.response;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.bikeWatch.review.domain.Review;
import com.fasterxml.jackson.annotation.JsonFormat;
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
	String content,

	@Schema(description = "작성일/시간")
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	LocalDateTime createdDateTime) {

	@Builder
	@QueryProjection
	public SelectReviewResponse(Long id, String title, String imagePath, String content,
		LocalDateTime createdDateTime) {
		this.id = id;
		this.title = title;
		this.imagePath = imagePath;
		this.content = content;
		this.createdDateTime = createdDateTime;
	}

	public static SelectReviewResponse of(Review review) {
		return SelectReviewResponse.builder()
			.id(review.getId())
			.title(review.getTitle())
			.imagePath(review.getImagePath())
			.content(review.getContent())
			.createdDateTime(review.getCreatedDateTime())
			.build();
	}
}
