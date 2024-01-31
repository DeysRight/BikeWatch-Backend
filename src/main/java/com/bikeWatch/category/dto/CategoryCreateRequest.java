package com.bikeWatch.category.dto;

import com.bikeWatch.category.domain.Category;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public record CategoryCreateRequest(

	@NotBlank(message = "카테고리 이름을 입력해주세요.")
	String categoryTitle) {

	@Builder
	public CategoryCreateRequest(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public Category toEntity() {
		return Category.builder().title(categoryTitle).build();
	}
}
