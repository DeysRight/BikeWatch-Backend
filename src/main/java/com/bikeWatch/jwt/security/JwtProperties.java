package com.bikeWatch.jwt.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

	@NotEmpty
	private String issuer;

	@NotEmpty
	private String secretKey;

	@Min(60)
	private Long accessTokenValidTime;

	@Min(60)
	private Long refreshTokenValidTime;
}
