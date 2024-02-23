package com.bikeWatch.visitorstatistics.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bikeWatch.visitorstatistics.domain.VisitorStatistics;
import com.bikeWatch.visitorstatistics.dto.response.VisitorCountResponse;
import com.bikeWatch.visitorstatistics.repository.VisitorStatisticsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VisitorStatisticsService {

	private final VisitorStatisticsRepository visitorStatisticsRepository;

	public VisitorCountResponse visitorCount(LocalDate todayDate) {
		long todayVisitorCount = visitorStatisticsRepository.countByVisitorDateTimeBetween(todayDate.atStartOfDay(),
			todayDate.atTime(23, 59, 59));
		long totalVisitorCount = visitorStatisticsRepository.count();

		return new VisitorCountResponse(todayVisitorCount, totalVisitorCount);
	}

	@Transactional
	public void save(String ip, LocalDateTime visitorDateTime) {
		visitorStatisticsRepository.save(VisitorStatistics.builder().ip(ip).visitorDateTime(visitorDateTime).build());
	}
}
