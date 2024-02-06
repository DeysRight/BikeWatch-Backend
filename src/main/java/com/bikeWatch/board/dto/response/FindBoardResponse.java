package com.bikeWatch.board.dto.response;

import com.bikeWatch.board.domain.Board;
import com.querydsl.core.annotations.QueryProjection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

public record FindBoardResponse(
	@Schema(description = "게시글 번호")
	Long id,

	@Schema(description = "게시글 제목")
	String title,

	@Schema(description = "게시글 내용")
	String content) {
	@Builder
	@QueryProjection
	public FindBoardResponse(Long id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public static FindBoardResponse of(Board board) {
		return FindBoardResponse.builder()
			.id(board.getId())
			.title(board.getTitle())
			.content(board.getContent())
			.build();
	}
}
