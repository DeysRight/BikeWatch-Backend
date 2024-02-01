package com.bikeWatch.menu.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bikeWatch.category.domain.Category;
import com.bikeWatch.category.repository.CategoryRepository;
import com.bikeWatch.common.error.ErrorCode;
import com.bikeWatch.common.error.exception.BadRequestException;
import com.bikeWatch.menu.dto.request.CreateMenuRequest;
import com.bikeWatch.menu.repository.MenuRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenuService {

	private final MenuRepository menuRepository;
	private final CategoryRepository categoryRepository;

	@Transactional
	public void createMenu(CreateMenuRequest createMenuRequest) {
		boolean existsMenuTitle = menuRepository.existsByTitle(createMenuRequest.menuTitle());
		Category category = categoryRepository.findById(createMenuRequest.categoryId())
			.orElseThrow(() -> new BadRequestException(ErrorCode.NOT_FOUND_CATEGORY));

		if (existsMenuTitle) {
			throw new BadRequestException(ErrorCode.DUPLICATION_MENU);
		}
		menuRepository.save(createMenuRequest.toEntity(category));
	}
}
