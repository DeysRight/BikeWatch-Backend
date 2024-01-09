package com.bikeWatch.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public record BoardCreateRequest(

	@NotBlank(message = "제목을 입력해주세요.")
	String title,

	@NotBlank(message = "내용을 입력해주세요.")
	String content) {

	@Builder
	public BoardCreateRequest(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
