package com.bikeWatch.user.dto.response;

import com.bikeWatch.user.domain.Role;
import com.bikeWatch.user.domain.User;

import lombok.Builder;

public record JoinUserResponse(
	Long id,
	String email,
	Role role
) {
	@Builder
	public JoinUserResponse {
	}

	public static JoinUserResponse of(User user) {
		return JoinUserResponse.builder()
			.id(user.getId())
			.email(user.getEmail())
			.role(user.getRole())
			.build();
	}
}
