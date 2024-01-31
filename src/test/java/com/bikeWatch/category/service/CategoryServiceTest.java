package com.bikeWatch.category.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bikeWatch.category.dto.CategoryCreateRequest;
import com.bikeWatch.category.dto.CategoryCreateResponse;

@SpringBootTest
class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;

	@DisplayName("카테고리를 생성한다.")
	@Test
	void createCategory() {
		// given
		String categoryName = "오토바이";
		CategoryCreateRequest categoryCreateRequest = CategoryCreateRequest.builder()
			.categoryTitle(categoryName)
			.build();

		// when
		CategoryCreateResponse categoryCreateResponse = categoryService.createCategory(categoryCreateRequest);

		// then
		assertThat(categoryCreateResponse.title()).isEqualTo(categoryName);
	}
}