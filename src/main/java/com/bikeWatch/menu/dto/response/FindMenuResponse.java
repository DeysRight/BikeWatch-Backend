package com.bikeWatch.menu.dto.response;

import com.bikeWatch.menu.domain.Menu;

public record FindMenuResponse(
	Long id,
	String title
) {
	public static FindMenuResponse of(Menu menu) {
		return new FindMenuResponse(menu.getId(), menu.getTitle());
	}
}
