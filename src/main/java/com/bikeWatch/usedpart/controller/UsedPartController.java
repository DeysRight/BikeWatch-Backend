package com.bikeWatch.usedpart.controller;

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
import com.bikeWatch.usedpart.dto.request.CreateUsedPartRequest;
import com.bikeWatch.usedpart.dto.response.SelectUsedPartResponse;
import com.bikeWatch.usedpart.service.UsedPartService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "used-part-controller", description = "중고 부품 서비스를 위한 컨트롤러")
@RestController
@RequestMapping("/api/used-part")
@RequiredArgsConstructor
public class UsedPartController {

	private final UsedPartService usedPartService;

	@Operation(summary = "중고 부품 등록", description = "중고 부품를 등록합니다.")
	@PostMapping("/admin")
	public ApiResponse<Void> addUsedPart(@RequestBody @Valid CreateUsedPartRequest request) {
		usedPartService.createUsedPart(request);

		return ApiResponse.of(HttpStatus.CREATED, null);
	}

	@Operation(summary = "중고 부품 키워드 조회", description = "중고 부품을 키워드로 조회합니다.")
	@GetMapping
	public ApiResponse<Page<SelectUsedPartResponse>> getUsedPartsByKeyword(Pageable pageable,
		@RequestParam(name = "keyword", required = false) String keyword) {
		return ApiResponse.ok(usedPartService.getUsedPartsByKeyword(pageable, keyword));
	}

	@Operation(summary = "중고 부품 상세 조회", description = "중고 부품을 상세 조회합니다.")
	@GetMapping("/{id}")
	public ApiResponse<SelectUsedPartResponse> getUsedPartDetail(@PathVariable(name = "id") Long id) {
		return ApiResponse.ok(usedPartService.getUsedPartDetail(id));
	}

	@Operation(summary = "중고 부품 수정", description = "중고 부품을 수정합니다.")
	@PutMapping("/{id}/admin")
	public ApiResponse<Void> modifyUsedPart(@PathVariable(name = "id") Long id,
		@RequestBody @Valid CreateUsedPartRequest request) {
		usedPartService.updateUsedPart(id, request);

		return ApiResponse.of(HttpStatus.CREATED, null);
	}

	@Operation(summary = "중고 부품 삭제", description = "중고 부품을 삭제합니다.")
	@DeleteMapping("/{id}/admin")
	public ApiResponse<Void> removeUsedPart(@PathVariable(name = "id") Long id) {
		usedPartService.deleteUsedPart(id);

		return ApiResponse.of(HttpStatus.NO_CONTENT, null);
	}

}
