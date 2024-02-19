package com.bikeWatch.information.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikeWatch.common.domain.ApiResponse;
import com.bikeWatch.information.domain.Information;
import com.bikeWatch.information.service.InformationService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "information-controller", description = "기본 정보 서비스를 위한 컨트롤러")
@RestController
@RequestMapping("/api/informations")
@RequiredArgsConstructor
public class InformationController {

	private final InformationService informationService;

	@GetMapping
	public ApiResponse<Information> findInformation() {
		return ApiResponse.ok(informationService.findAll());
	}
}
