package com.bikeWatch.board.domain;

import com.bikeWatch.common.domain.BaseTimeEntity;
import com.bikeWatch.menu.domain.Menu;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@Lob
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id")
	private Menu menu;

	@Builder
	public Board(String title, String content, Menu menu) {
		this.title = title;
		this.content = content;
		this.menu = menu;
	}

	public Board update(String title, String content) {
		this.title = title;
		this.content = content;

		return this;
	}
}
