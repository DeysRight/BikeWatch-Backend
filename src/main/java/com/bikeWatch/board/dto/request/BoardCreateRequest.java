package com.bikeWatch.board.dto.request;

import com.bikeWatch.board.domain.Board;
import com.bikeWatch.menu.domain.Menu;

import jakarta.validation.constraints.NotBlank;

public record BoardCreateRequest(

	@NotBlank(message = "제목을 입력해주세요.")
	String title,

	@NotBlank(message = "내용을 입력해주세요.")
	String content) {

	public Board toEntity(Menu menu) {
		return Board.builder()
			.title(title)
			.content(content)
			.menu(menu)
			.build();
	}
}
