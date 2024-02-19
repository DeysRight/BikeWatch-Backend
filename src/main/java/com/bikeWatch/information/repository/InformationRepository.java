package com.bikeWatch.information.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bikeWatch.information.domain.Information;

@Repository
public interface InformationRepository extends JpaRepository<Information, Long> {
}
