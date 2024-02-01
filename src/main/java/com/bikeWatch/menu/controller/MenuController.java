package com.bikeWatch.menu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikeWatch.category.dto.response.CreateCategoryResponse;
import com.bikeWatch.common.domain.ApiResponse;
import com.bikeWatch.menu.dto.request.CreateMenuRequest;
import com.bikeWatch.menu.service.MenuService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "menu-controller", description = "메뉴 서비스를 위한 컨트롤러")
@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuController {

	private final MenuService menuService;

	@Operation(summary = "메뉴 생성", description = "메뉴를 생성합니다.")
	@PostMapping
	public ApiResponse<CreateCategoryResponse> createCategory(@Valid @RequestBody CreateMenuRequest req) {
		menuService.createMenu(req);
		return ApiResponse.of(HttpStatus.CREATED, null);
	}
}
