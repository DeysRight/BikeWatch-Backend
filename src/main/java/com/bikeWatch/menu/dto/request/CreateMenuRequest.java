package com.bikeWatch.menu.dto.request;

import com.bikeWatch.category.domain.Category;
import com.bikeWatch.menu.domain.Menu;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateMenuRequest(
	@Positive
	Long id,

	@NotBlank(message = "메뉴 이름을 입력해주세요.")
	String title
) {
	public Menu toEntity(Category category) {
		return Menu.builder().title(title).category(category).build();
	}
}
