package com.bikeWatch.category.dto;

import com.bikeWatch.category.domain.Category;

public record CategoryCreateResponse(

	Long id,
	String title
) {
	public static CategoryCreateResponse of(Category category) {
		return new CategoryCreateResponse(category.getId(), category.getTitle());
	}
}
