package com.bikeWatch.category.dto.request;

import com.bikeWatch.category.domain.Category;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public record CreateCategoryRequest(
	@NotBlank(message = "카테고리 이름을 입력해주세요.")
	String title) {

	@Builder
	public CreateCategoryRequest(String title) {
		this.title = title;
	}

	public Category toEntity() {
		return Category.builder().title(title).build();
	}
}
