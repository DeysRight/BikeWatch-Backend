package com.bikeWatch.category.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bikeWatch.category.domain.Category;
import com.bikeWatch.category.dto.request.CreateCategoryRequest;
import com.bikeWatch.category.repository.CategoryRepository;

@SpringBootTest
class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryRepository categoryRepository;

	@DisplayName("카테고리를 생성한다.")
	@Test
	void createCategory() {
		// given
		String categoryName = "오토바이";
		CreateCategoryRequest createCategoryRequest = CreateCategoryRequest.builder()
			.title(categoryName)
			.build();

		// when
		categoryService.createCategory(createCategoryRequest);
		List<Category> categories = categoryRepository.findAll();

		// then
		assertThat(categories.get(0).getTitle()).isEqualTo(categoryName);
	}
}