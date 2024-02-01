package com.bikeWatch.board.dto.response;

import java.time.LocalDateTime;

import com.bikeWatch.board.domain.Board;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;

public record CreateBoardResponse(
	Long id,
	String title,
	String content,

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime createdDateTime) {
	@Builder
	public CreateBoardResponse(Long id, String title, String content, LocalDateTime createdDateTime) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.createdDateTime = createdDateTime;
	}

	public static CreateBoardResponse of(Board board) {
		return CreateBoardResponse.builder()
			.id(board.getId())
			.title(board.getTitle())
			.content(board.getContent())
			.createdDateTime(board.getCreatedDateTime())
			.build();
	}
}
