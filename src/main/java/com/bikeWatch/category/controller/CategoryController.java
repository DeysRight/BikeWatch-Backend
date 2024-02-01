package com.bikeWatch.category.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikeWatch.category.dto.request.CreateCategoryRequest;
import com.bikeWatch.category.dto.response.CreateCategoryResponse;
import com.bikeWatch.category.service.CategoryService;
import com.bikeWatch.common.domain.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "category-controller", description = "카테고리 서비스를 위한 컨트롤러")
@RestController
@RequestMapping("/api/categorys")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;

	@Operation(summary = "카테고리 생성", description = "카테고리를 생성합니다.")
	@PostMapping
	public ApiResponse<CreateCategoryResponse> createCategory(
		@Valid @RequestBody CreateCategoryRequest req) {
		CreateCategoryResponse createCategoryResponse = categoryService.createCategory(req);

		return ApiResponse.of(HttpStatus.CREATED, createCategoryResponse);
	}
}
