package com.bikeWatch.visitorstatistics.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bikeWatch.visitorstatistics.domain.VisitorStatistics;

@Repository
public interface VisitorStatisticsRepository extends JpaRepository<VisitorStatistics, Long> {

	long countByVisitorDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
