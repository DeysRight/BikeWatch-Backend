package com.bikeWatch.category.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikeWatch.category.dto.request.CreateCategoryRequest;
import com.bikeWatch.category.dto.request.UpdateCategoryRequest;
import com.bikeWatch.category.dto.response.FindCategoryResponse;
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
	public ApiResponse<Void> createCategory(
		@RequestBody @Valid CreateCategoryRequest req) {
		categoryService.createCategory(req);

		return ApiResponse.of(HttpStatus.CREATED, null);
	}

	@Operation(summary = "카테고리 수정", description = "카테고리를 수정합니다.")
	@PutMapping("{categoryId}")
	public ApiResponse<Void> updateCategory(@RequestBody @Valid UpdateCategoryRequest req,
		@PathVariable(value = "categoryId") Long categoryId) {
		categoryService.updateCategory(req, categoryId);

		return ApiResponse.of(HttpStatus.CREATED, null);
	}

	@Operation(summary = "카테고리 삭제", description = "카테고리를 삭제합니다.")
	@DeleteMapping("{categoryId}")
	public ApiResponse<Void> deleteCategory(@PathVariable(value = "categoryId") Long categoryId) {
		categoryService.deleteCategory(categoryId);

		return ApiResponse.of(HttpStatus.NO_CONTENT, null);
	}

	@Operation(summary = "카테고리와 하위 메뉴 조회", description = "카테고리와 그 하위의 메뉴를 조회합니다.")
	@GetMapping("/menus")
	public ApiResponse<List<FindCategoryResponse>> findCategoryAndMenu() {
		return ApiResponse.ok(categoryService.findCategoryAndMenu());
	}
}
