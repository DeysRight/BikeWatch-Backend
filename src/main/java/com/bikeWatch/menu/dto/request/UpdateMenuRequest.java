package com.bikeWatch.menu.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateMenuRequest(
	@NotBlank(message = "메뉴 이름을 입력해주세요.")
	String title
) {
}
