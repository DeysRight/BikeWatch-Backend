package com.bikeWatch.board.dto.response;

import com.bikeWatch.board.domain.Board;

import lombok.Builder;

public record BoardFindResponse(Long id, String title, String content) {

	@Builder
	public BoardFindResponse(Long id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public static BoardFindResponse of(Board board) {
		return BoardFindResponse.builder()
			.id(board.getId())
			.title(board.getTitle())
			.content(board.getContent())
			.build();
	}
}
