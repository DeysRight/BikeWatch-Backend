package com.bikeWatch.board.dto.response;

import com.bikeWatch.board.domain.Board;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

public record BoardFindResponse(

	@Schema(description = "게시글 번호")
	Long id,

	@Schema(description = "게시글 제목")
	String title,

	@Schema(description = "게시글 내용")
	String content) {
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
