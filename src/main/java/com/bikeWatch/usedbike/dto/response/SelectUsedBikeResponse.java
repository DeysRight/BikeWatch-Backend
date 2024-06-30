package com.bikeWatch.usedbike.dto.response;

import java.time.LocalDateTime;

import com.bikeWatch.usedbike.domain.UsedBike;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

public record SelectUsedBikeResponse(
	@Schema(description = "중고 바이크 번호")
	Long id,

	@Schema(description = "중고 바이크 제목")
	String title,

	@Schema(description = "중고 바이크 이미지 경로")
	String imagePath,

	@Schema(description = "중고 바이크 내용")
	String content,

	@Schema(description = "작성일/시간")
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	LocalDateTime createdDateTime) {

	@Builder
	@QueryProjection
	public SelectUsedBikeResponse(Long id, String title, String imagePath, String content,
		LocalDateTime createdDateTime) {
		this.id = id;
		this.title = title;
		this.imagePath = imagePath;
		this.content = content;
		this.createdDateTime = createdDateTime;
	}

	public static SelectUsedBikeResponse of(UsedBike usedBike) {
		return SelectUsedBikeResponse.builder()
			.id(usedBike.getId())
			.title(usedBike.getTitle())
			.imagePath(usedBike.getImagePath())
			.content(usedBike.getContent())
			.createdDateTime(usedBike.getCreatedDateTime())
			.build();
	}
}
