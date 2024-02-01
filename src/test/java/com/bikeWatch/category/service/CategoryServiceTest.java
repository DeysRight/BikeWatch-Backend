package com.bikeWatch.category.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bikeWatch.category.dto.request.CreateCategoryRequest;
import com.bikeWatch.category.dto.response.CreateCategoryResponse;

@SpringBootTest
class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;

	@DisplayName("카테고리를 생성한다.")
	@Test
	void createCategory() {
		// given
		String categoryName = "오토바이";
		CreateCategoryRequest createCategoryRequest = CreateCategoryRequest.builder()
			.categoryTitle(categoryName)
			.build();

		// when
		CreateCategoryResponse createCategoryResponse = categoryService.createCategory(createCategoryRequest);

		// then
		assertThat(createCategoryResponse.title()).isEqualTo(categoryName);
	}
}