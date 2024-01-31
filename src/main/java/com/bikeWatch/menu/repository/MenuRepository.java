package com.bikeWatch.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bikeWatch.menu.domain.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

	boolean existsByTitle(String menuTitle);
}
