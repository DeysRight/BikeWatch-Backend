package com.bikeWatch.category.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bikeWatch.category.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	boolean existsByTitle(String categoryTitle);

	@Query("SELECT DISTINCT c FROM Category c LEFT OUTER JOIN FETCH c.menus")
	List<Category> findCategoryAndMenu();
}
