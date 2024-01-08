package com.bikeWatch.board.dto.response;

import com.bikeWatch.board.domain.Board;

import lombok.Builder;

public record BoardCreateResponse(Long id, String title, String content) {

	@Builder
	public BoardCreateResponse(Long id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public static BoardCreateResponse of(Board board) {
		return BoardCreateResponse.builder()
			.id(board.getId())
			.title(board.getTitle())
			.content(board.getContent())
			.build();
	}
}
