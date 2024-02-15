package com.bikeWatch.visitorstatistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bikeWatch.visitorstatistics.domain.VisitorStatistics;

@Repository
public interface VisitorStatisticsRepository extends JpaRepository<VisitorStatistics, Long> {
}
