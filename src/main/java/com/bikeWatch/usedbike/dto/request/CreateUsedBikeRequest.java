package com.bikeWatch.usedbike.dto.request;

import com.bikeWatch.usedbike.domain.UsedBike;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public record CreateUsedBikeRequest(

	@NotBlank(message = "제목을 입력해주세요.")
	String title,

	@Nullable
	String imagePath,

	@NotBlank(message = "내용을 입력해주세요.")
	String content) {

	public UsedBike toEntity() {
		return UsedBike.builder()
			.title(title)
			.imagePath(imagePath)
			.content(content)
			.build();
	}
}
