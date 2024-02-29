package com.bikeWatch.user.dto.request;

public record LoginUserRequest(
	String email,
	String password
) {
}
