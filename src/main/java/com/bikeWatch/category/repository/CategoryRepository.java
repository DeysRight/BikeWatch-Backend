package com.bikeWatch.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bikeWatch.category.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	boolean existsByTitle(String categoryTitle);
}
