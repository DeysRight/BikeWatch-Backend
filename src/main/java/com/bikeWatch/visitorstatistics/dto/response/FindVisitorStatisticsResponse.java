package com.bikeWatch.visitorstatistics.dto.response;

import com.bikeWatch.visitorstatistics.domain.VisitorStatistics;

import lombok.Builder;

public record FindVisitorStatisticsResponse(Long todayVisitorCount, Long sumVisitorCount) {

	@Builder
	public FindVisitorStatisticsResponse {
	}

	public static FindVisitorStatisticsResponse of(VisitorStatistics visitorStatistics) {
		return FindVisitorStatisticsResponse.builder()
			.todayVisitorCount(visitorStatistics.getTodayVisitorCount())
			.sumVisitorCount(visitorStatistics.getSumVisitorCount())
			.build();
	}
}
