package com.bikeWatch.visitorstatistics.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bikeWatch.visitorstatistics.domain.VisitorStatistics;
import com.bikeWatch.visitorstatistics.dto.response.FindVisitorStatisticsResponse;
import com.bikeWatch.visitorstatistics.repository.VisitorStatisticsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VisitorStatisticsService {

	private final VisitorStatisticsRepository visitorStatisticsRepository;

	public FindVisitorStatisticsResponse findVisitorStatistics() {
		VisitorStatistics visitorStatistics = visitorStatisticsRepository.findAll().get(0);

		return FindVisitorStatisticsResponse.of(visitorStatistics);
	}
}
