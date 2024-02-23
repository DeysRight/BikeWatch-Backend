package com.bikeWatch.visitorstatistics.dto.response;

public record VisitorCountResponse(
	Long todayVisitorCount, Long totalVisitorCount) {
}
