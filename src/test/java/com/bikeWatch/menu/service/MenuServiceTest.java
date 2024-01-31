package com.bikeWatch.menu.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.bikeWatch.category.dto.CategoryCreateRequest;
import com.bikeWatch.category.dto.CategoryCreateResponse;
import com.bikeWatch.category.service.CategoryService;
import com.bikeWatch.menu.domain.Menu;
import com.bikeWatch.menu.dto.MenuCreateRequest;
import com.bikeWatch.menu.repository.MenuRepository;

@SpringBootTest
class MenuServiceTest {

	@Autowired
	private MenuService menuService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private MenuRepository menuRepository;

	@DisplayName("카테고리의 하위로 메뉴를 생성한다.")
	@Transactional
	@Test
	void createMenu() {
		// given
		String categoryTitle = "오토바이";
		CategoryCreateRequest categoryCreateRequest = CategoryCreateRequest.builder()
			.categoryTitle(categoryTitle)
			.build();
		CategoryCreateResponse categoryCreateResponse = categoryService.createCategory(categoryCreateRequest);
		Long categoryId = categoryCreateResponse.id();

		String menuTitle1 = "슈퍼커브";
		String menuTitle2 = "시티";
		MenuCreateRequest menuCreateRequest1 = new MenuCreateRequest(categoryId, menuTitle1);
		MenuCreateRequest menuCreateRequest2 = new MenuCreateRequest(categoryId, menuTitle2);

		// when
		menuService.createMenu(menuCreateRequest1);
		menuService.createMenu(menuCreateRequest2);
		List<Menu> menus = menuRepository.findAll();

		// then
		assertThat(menus).hasSize(2)
			.extracting(Menu::getTitle)
			.containsExactlyInAnyOrder(
				menuTitle1,
				menuTitle2
			);
		assertThat(menus.get(0).getCategory().getTitle()).isEqualTo(categoryTitle);
		assertThat(menus.get(1).getCategory().getTitle()).isEqualTo(categoryTitle);
	}
}