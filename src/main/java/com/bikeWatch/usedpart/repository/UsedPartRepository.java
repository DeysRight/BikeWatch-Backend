package com.bikeWatch.usedpart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bikeWatch.usedpart.domain.UsedPart;

@Repository
public interface UsedPartRepository extends JpaRepository<UsedPart, Long>, UsedPartRepositoryCustom {
}
