package com.bikeWatch.menu.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.bikeWatch.category.domain.Category;
import com.bikeWatch.category.dto.request.CreateCategoryRequest;
import com.bikeWatch.category.repository.CategoryRepository;
import com.bikeWatch.category.service.CategoryService;
import com.bikeWatch.menu.domain.Menu;
import com.bikeWatch.menu.dto.request.CreateMenuRequest;
import com.bikeWatch.menu.repository.MenuRepository;

@SpringBootTest
class MenuServiceTest {

	@Autowired
	private MenuService menuService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private MenuRepository menuRepository;

	@DisplayName("카테고리의 하위로 메뉴를 생성한다.")
	@Transactional
	@Test
	void createMenu() {
		// given
		String categoryTitle = "오토바이";
		CreateCategoryRequest createCategoryRequest = CreateCategoryRequest.builder()
			.title(categoryTitle)
			.build();
		categoryService.createCategory(createCategoryRequest);
		List<Category> categories = categoryRepository.findAll();
		Long categoryId = categories.get(0).getId();

		String menuTitle1 = "슈퍼커브";
		String menuTitle2 = "시티";
		CreateMenuRequest createMenuRequest1 = new CreateMenuRequest(categoryId, menuTitle1);
		CreateMenuRequest createMenuRequest2 = new CreateMenuRequest(categoryId, menuTitle2);

		// when
		menuService.createMenu(createMenuRequest1);
		menuService.createMenu(createMenuRequest2);
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