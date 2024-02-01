package com.bikeWatch.category.dto.response;

import com.bikeWatch.category.domain.Category;

public record CreateCategoryResponse(

	Long id,
	String title
) {
	public static CreateCategoryResponse of(Category category) {
		return new CreateCategoryResponse(category.getId(), category.getTitle());
	}
}
