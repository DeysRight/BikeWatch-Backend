package com.bikeWatch.board.dto.response;

import java.time.LocalDateTime;

import com.bikeWatch.board.domain.Board;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;

public record BoardCreateResponse(

	Long id,
	String title,
	String content,

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime createdDateTime) {
	@Builder
	public BoardCreateResponse(Long id, String title, String content, LocalDateTime createdDateTime) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.createdDateTime = createdDateTime;
	}

	public static BoardCreateResponse of(Board board) {
		return BoardCreateResponse.builder()
			.id(board.getId())
			.title(board.getTitle())
			.content(board.getContent())
			.createdDateTime(board.getCreatedDateTime())
			.build();
	}
}
