package com.bikeWatch.category.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bikeWatch.category.domain.Category;
import com.bikeWatch.category.dto.request.CreateCategoryRequest;
import com.bikeWatch.category.dto.request.UpdateCategoryRequest;
import com.bikeWatch.category.dto.response.FindCategoryResponse;
import com.bikeWatch.category.repository.CategoryRepository;
import com.bikeWatch.common.error.ErrorCode;
import com.bikeWatch.common.error.exception.BadRequestException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;

	@Transactional
	public void createCategory(CreateCategoryRequest req) {
		validateDuplicatedCategoryTitle(req.title());
		categoryRepository.save(req.toEntity());
	}

	@Transactional
	public void updateCategory(UpdateCategoryRequest req, Long categoryId) {
		Category category = categoryRepository.findById(categoryId)
			.orElseThrow(() -> new BadRequestException(ErrorCode.NOT_FOUND_CATEGORY));
		validateDuplicatedCategoryTitle(req.title());
		category.changeTitle(req.title());
	}

	private void validateDuplicatedCategoryTitle(String req) {
		boolean existsCategoryTitle = categoryRepository.existsByTitle(req);
		if (existsCategoryTitle) {
			throw new BadRequestException(ErrorCode.DUPLICATION_CATEGORY);
		}
	}

	@Transactional
	public void deleteCategory(Long categoryId) {
		Category category = categoryRepository.findById(categoryId)
			.orElseThrow(() -> new BadRequestException(ErrorCode.NOT_FOUND_CATEGORY));

		if (!category.getMenus().isEmpty()) {
			throw new BadRequestException(ErrorCode.NOT_REMOVE_CATEGORY);
		}
		categoryRepository.deleteById(categoryId);
	}

	public List<FindCategoryResponse> findCategoryAndMenu() {
		return categoryRepository.findCategoryAndMenu()
			.stream()
			.map(FindCategoryResponse::of)
			.toList();
	}
}
