package com.bikeWatch.information.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bikeWatch.information.domain.Information;

public interface InformationRepository extends JpaRepository<Information, Long> {
}
