package com.bikeWatch.user.domain;

public enum Role {
	USER("사용자"),
	ADMIN("관리자");

	private final String label;

	Role(String label) {
		this.label = label;
	}
}
