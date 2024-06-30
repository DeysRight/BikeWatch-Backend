package com.bikeWatch.usedbike.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bikeWatch.usedbike.domain.UsedBike;

@Repository
public interface UsedBikeRepository extends JpaRepository<UsedBike, Long>, UsedBikeRepositoryCustom {
}
