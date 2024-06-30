package com.bikeWatch.usedpart.dto.request;

import com.bikeWatch.usedpart.domain.UsedPart;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public record CreateUsedPartRequest(

	@NotBlank(message = "제목을 입력해주세요.")
	String title,

	@Nullable
	String imagePath,

	@NotBlank(message = "내용을 입력해주세요.")
	String content) {

	public UsedPart toEntity() {
		return UsedPart.builder()
			.title(title)
			.imagePath(imagePath)
			.content(content)
			.build();
	}
}
