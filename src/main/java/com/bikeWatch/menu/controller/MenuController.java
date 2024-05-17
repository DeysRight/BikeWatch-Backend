// package com.bikeWatch.menu.controller;
//
// import org.springframework.http.HttpStatus;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
//
// import com.bikeWatch.common.domain.ApiResponse;
// import com.bikeWatch.menu.dto.request.CreateMenuRequest;
// import com.bikeWatch.menu.dto.request.UpdateMenuRequest;
// import com.bikeWatch.menu.service.MenuService;
//
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import jakarta.validation.Valid;
// import lombok.RequiredArgsConstructor;
//
// @Tag(name = "menu-controller", description = "메뉴 서비스를 위한 컨트롤러")
// @RestController
// @RequestMapping("/api/menus")
// @RequiredArgsConstructor
// public class MenuController {
//
// 	private final MenuService menuService;
//
// 	@Operation(summary = "메뉴 생성", description = "메뉴를 생성합니다.")
// 	@PostMapping("/admin")
// 	public ApiResponse<Void> createMenu(@RequestBody @Valid CreateMenuRequest req) {
// 		menuService.createMenu(req);
//
// 		return ApiResponse.of(HttpStatus.CREATED, null);
// 	}
//
// 	@Operation(summary = "메뉴 수정", description = "메뉴를 수정합니다.")
// 	@PutMapping("{menuId}/admin")
// 	public ApiResponse<Void> updateMenu(@RequestBody @Valid UpdateMenuRequest req,
// 		@PathVariable(value = "menuId") Long menuId) {
// 		menuService.updateMenu(req, menuId);
//
// 		return ApiResponse.of(HttpStatus.CREATED, null);
// 	}
//
// 	@Operation(summary = "메뉴 삭제", description = "메뉴를 삭제합니다.")
// 	@DeleteMapping("{menuId}/admin")
// 	public ApiResponse<Void> deleteMenu(@PathVariable(value = "menuId") Long menuId) {
// 		menuService.deleteMenu(menuId);
//
// 		return ApiResponse.of(HttpStatus.RESET_CONTENT, null);
// 	}
// }
