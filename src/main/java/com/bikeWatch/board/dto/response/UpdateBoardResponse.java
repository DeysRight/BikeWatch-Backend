package com.bikeWatch.board.dto.response;

import java.time.LocalDateTime;

import com.bikeWatch.board.domain.Board;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;

public record UpdateBoardResponse(
	Long id,
	String title,
	String content,

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime modifiedDateTime) {
	@Builder
	public UpdateBoardResponse(Long id, String title, String content, LocalDateTime modifiedDateTime) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.modifiedDateTime = modifiedDateTime;
	}

	public static UpdateBoardResponse of(Board board) {
		return UpdateBoardResponse.builder()
			.id(board.getId())
			.title(board.getTitle())
			.content(board.getContent())
			.modifiedDateTime(board.getModifiedDateTime())
			.build();
	}
}
