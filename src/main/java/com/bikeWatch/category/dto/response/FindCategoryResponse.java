package com.bikeWatch.category.dto.response;

import java.util.List;

import com.bikeWatch.category.domain.Category;
import com.bikeWatch.menu.dto.response.FindMenuResponse;

public record FindCategoryResponse(
	Long id,
	String title,
	List<FindMenuResponse> menu
) {
	public static FindCategoryResponse of(Category category) {
		return new FindCategoryResponse(category.getId(), category.getTitle()
			, category.getMenus().stream().map(FindMenuResponse::of).toList());
	}
}
