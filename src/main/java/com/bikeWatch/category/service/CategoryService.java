package com.bikeWatch.category.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bikeWatch.category.domain.Category;
import com.bikeWatch.category.dto.request.CreateCategoryRequest;
import com.bikeWatch.category.dto.response.CreateCategoryResponse;
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
	public CreateCategoryResponse createCategory(CreateCategoryRequest req) {
		boolean existsCategoryTitle = categoryRepository.existsByTitle(req.categoryTitle());

		if (existsCategoryTitle) {
			throw new BadRequestException(ErrorCode.DUPLICATION_CATEGORY);
		}
		Category category = categoryRepository.save(req.toEntity());

		return CreateCategoryResponse.of(category);
	}
}