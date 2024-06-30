package com.bikeWatch.usedbike.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bikeWatch.common.domain.ApiResponse;
import com.bikeWatch.usedbike.dto.request.CreateUsedBikeRequest;
import com.bikeWatch.usedbike.dto.response.SelectUsedBikeResponse;
import com.bikeWatch.usedbike.service.UsedBikeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "used-bike-controller", description = "중고바이크 서비스를 위한 컨트롤러")
@RestController
@RequestMapping("/api/used-bike")
@RequiredArgsConstructor
public class UsedBikeController {

	private final UsedBikeService usedBikeService;

	@Operation(summary = "중고 바이크 등록", description = "중고 바이크를 등록합니다.")
	@PostMapping("/admin")
	public ApiResponse<Void> addUsedBike(@RequestBody @Valid CreateUsedBikeRequest request) {
		usedBikeService.createUsedBike(request);

		return ApiResponse.of(HttpStatus.CREATED, null);
	}

	@Operation(summary = "중고 바이크 키워드 조회", description = "중고 바이크를 키워드로 조회합니다.")
	@GetMapping
	public ApiResponse<Page<SelectUsedBikeResponse>> getUsedBikesByKeyword(Pageable pageable,
		@RequestParam(name = "keyword", required = false) String keyword) {
		return ApiResponse.ok(usedBikeService.getUsedBikesByKeyword(pageable, keyword));
	}

	@Operation(summary = "중고 바이크 상세 조회", description = "중고 바이크를 상세 조회합니다.")
	@GetMapping("/{id}")
	public ApiResponse<SelectUsedBikeResponse> getUsedBikeDetail(@PathVariable(name = "id") Long id) {
		return ApiResponse.ok(usedBikeService.getUsedBikeDetail(id));
	}

	@Operation(summary = "중고 바이크 수정", description = "중고 바이크를 수정합니다.")
	@PutMapping("/{id}/admin")
	public ApiResponse<Void> modifyUsedBike(@PathVariable(name = "id") Long id,
		@RequestBody @Valid CreateUsedBikeRequest request) {
		usedBikeService.updateUsedBike(id, request);

		return ApiResponse.of(HttpStatus.CREATED, null);
	}

	@Operation(summary = "중고 바이크 삭제", description = "중고 바이크를 삭제합니다.")
	@DeleteMapping("/{id}/admin")
	public ApiResponse<Void> removeUsedBike(@PathVariable(name = "id") Long id) {
		usedBikeService.deleteUsedBike(id);

		return ApiResponse.of(HttpStatus.NO_CONTENT, null);
	}

}
