package com.bikeWatch.menu.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bikeWatch.category.domain.Category;
import com.bikeWatch.category.repository.CategoryRepository;
import com.bikeWatch.common.error.ErrorCode;
import com.bikeWatch.common.error.exception.BadRequestException;
import com.bikeWatch.menu.domain.Menu;
import com.bikeWatch.menu.dto.request.CreateMenuRequest;
import com.bikeWatch.menu.dto.request.UpdateMenuRequest;
import com.bikeWatch.menu.repository.MenuRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenuService {

	private final MenuRepository menuRepository;
	private final CategoryRepository categoryRepository;

	@Transactional
	public void createMenu(CreateMenuRequest req) {
		validateDuplicatedMenuTitle(req.title());
		Category category = categoryRepository.findById(req.id())
			.orElseThrow(() -> new BadRequestException(ErrorCode.NOT_FOUND_CATEGORY));

		menuRepository.save(req.toEntity(category));
	}

	@Transactional
	public void updateMenu(UpdateMenuRequest req, Long menuId) {
		Menu menu = menuRepository.findById(menuId)
			.orElseThrow(() -> new BadRequestException(ErrorCode.NOT_FOUND_MENU));

		String title = req.title();
		validateDuplicatedMenuTitle(title);
		menu.changeTitle(title);
	}

	private void validateDuplicatedMenuTitle(String title) {
		boolean existsMenuTitle = menuRepository.existsByTitle(title);
		if (existsMenuTitle) {
			throw new BadRequestException(ErrorCode.DUPLICATION_MENU);
		}
	}

	@Transactional
	public void deleteMenu(Long menuId) {
		Menu menu = menuRepository.findById(menuId)
			.orElseThrow(() -> new BadRequestException(ErrorCode.NOT_FOUND_MENU));

		if (!menu.getBoards().isEmpty()) {
			throw new BadRequestException(ErrorCode.NOT_REMOVE_MENU);
		}
		menuRepository.deleteById(menuId);
	}
}
