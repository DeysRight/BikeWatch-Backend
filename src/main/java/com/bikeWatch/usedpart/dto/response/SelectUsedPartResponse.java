package com.bikeWatch.usedpart.dto.response;

import java.time.LocalDateTime;

import com.bikeWatch.usedpart.domain.UsedPart;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

public record SelectUsedPartResponse(
	@Schema(description = "부품 번호")
	Long id,

	@Schema(description = "부품 제목")
	String title,

	@Schema(description = "부품 이미지 경로")
	String imagePath,

	@Schema(description = "부품 내용")
	String content,

	@Schema(description = "작성일/시간")
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	LocalDateTime createdDateTime) {

	@Builder
	@QueryProjection
	public SelectUsedPartResponse(Long id, String title, String imagePath, String content,
		LocalDateTime createdDateTime) {
		this.id = id;
		this.title = title;
		this.imagePath = imagePath;
		this.content = content;
		this.createdDateTime = createdDateTime;
	}

	public static SelectUsedPartResponse of(UsedPart usedPart) {
		return SelectUsedPartResponse.builder()
			.id(usedPart.getId())
			.title(usedPart.getTitle())
			.imagePath(usedPart.getImagePath())
			.content(usedPart.getContent())
			.createdDateTime(usedPart.getCreatedDateTime())
			.build();
	}
}
