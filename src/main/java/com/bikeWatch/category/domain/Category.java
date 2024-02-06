package com.bikeWatch.category.domain;

import java.util.ArrayList;
import java.util.List;

import com.bikeWatch.common.domain.BaseTimeEntity;
import com.bikeWatch.menu.domain.Menu;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Category extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Menu> menus = new ArrayList<>();

	@Builder
	public Category(String title) {
		this.title = title;
	}

	public void changeTitle(String title) {
		this.title = title;
	}
}
