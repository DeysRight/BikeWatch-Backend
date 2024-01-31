package com.bikeWatch.menu.dto;

import com.bikeWatch.category.domain.Category;
import com.bikeWatch.menu.domain.Menu;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record MenuCreateRequest(

	@Positive
	Long categoryId,

	@NotBlank(message = "메뉴 이름을 입력해주세요.")
	String menuTitle
) {
	public Menu toEntity(Category category) {
		return Menu.builder().title(menuTitle).category(category).build();
	}
}
