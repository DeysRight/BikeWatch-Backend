package com.bikeWatch.visitorstatistics.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bikeWatch.visitorstatistics.domain.VisitorStatistics;
import com.bikeWatch.visitorstatistics.dto.response.TodayVisitorCountResponse;
import com.bikeWatch.visitorstatistics.dto.response.TotalVisitorCountResponse;
import com.bikeWatch.visitorstatistics.repository.VisitorStatisticsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VisitorStatisticsService {

	private final VisitorStatisticsRepository visitorStatisticsRepository;

	public TodayVisitorCountResponse todayVisitorCount(LocalDate todayDate) {
		return new TodayVisitorCountResponse(
			visitorStatisticsRepository.countByVisitorDateTimeBetween(todayDate.atStartOfDay(),
				todayDate.atTime(23, 59, 59)));

	}

	public TotalVisitorCountResponse totalVisitorCount() {
		return new TotalVisitorCountResponse(visitorStatisticsRepository.count());
	}

	@Transactional
	public void save(String ip, LocalDateTime visitorDateTime) {
		visitorStatisticsRepository.save(VisitorStatistics.builder().ip(ip).visitorDateTime(visitorDateTime).build());
	}
}
