package com.bikeWatch.user.dto.request;

import com.bikeWatch.user.domain.User;

public record JoinUserRequest(
	String email,
	String password
) {
	public User toEntity(String bCryptEncodedPassword) {
		return User.builder()
			.email(email)
			.password(bCryptEncodedPassword)
			.build();
	}
}
