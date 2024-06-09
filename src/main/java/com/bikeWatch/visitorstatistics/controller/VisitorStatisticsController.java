package com.bikeWatch.visitorstatistics.controller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikeWatch.common.domain.ApiResponse;
import com.bikeWatch.visitorstatistics.dto.response.VisitorCountResponse;
import com.bikeWatch.visitorstatistics.service.VisitorStatisticsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "visitor-statistics-controller", description = "방문자 카운트 서비스를 위한 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/visitor-statistics")
public class VisitorStatisticsController {

	private final VisitorStatisticsService visitorStatisticsService;

	@Operation(summary = "오늘 방문자, 전체 방문자 카운트 조회", description = "페이지에 접속한 오늘 방문자와 전체 방문자의 카운트를 조회합니다.")
	@GetMapping
	public ApiResponse<VisitorCountResponse> todayVisitorCount() {
		return ApiResponse.ok(visitorStatisticsService.visitorCount(LocalDate.now()));
	}
}
