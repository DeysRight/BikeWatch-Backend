package com.bikeWatch.user.service;

import java.util.NoSuchElementException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bikeWatch.common.error.ErrorCode;
import com.bikeWatch.common.error.exception.BadRequestException;
import com.bikeWatch.jwt.domain.RefreshToken;
import com.bikeWatch.jwt.dto.TokenDto;
import com.bikeWatch.jwt.repository.RefreshTokenRepository;
import com.bikeWatch.jwt.security.JwtTokenProvider;
import com.bikeWatch.user.domain.User;
import com.bikeWatch.user.dto.request.JoinUserRequest;
import com.bikeWatch.user.dto.request.LoginUserRequest;
import com.bikeWatch.user.dto.response.JoinUserResponse;
import com.bikeWatch.user.dto.response.LoginUserResponse;
import com.bikeWatch.user.repository.UserRepository;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserService {

	private final UserRepository userRepository;
	private final RefreshTokenRepository refreshTokenRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;

	@Transactional
	public JoinUserResponse save(JoinUserRequest req) {
		User user = userRepository.save(req.toEntity(bCryptPasswordEncoder.encode(req.password())));

		return JoinUserResponse.of(user);
	}

	@Transactional
	public LoginUserResponse login(LoginUserRequest req, HttpServletResponse response) {

		User user = userRepository.findByEmail(req.email())
			.orElseThrow(() -> new BadRequestException(ErrorCode.NOT_FOUND_USER));

		try {
			// authenticationManager 통해 id, password 검증
			// authenticate() 메소드 실행 시 UserDetailService.java 에서 만든 loadUserByUsername() 메소드 호출됨
			Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user, req.password()));

			// Access, Refresh 토큰 생성
			TokenDto tokenDto = jwtTokenProvider.createAllToken(authentication.getName(),
				authentication.getAuthorities(), user.getId());

			String newRefreshToken = tokenDto.refreshToken();

			// Refresh Token 있는지 확인
			refreshTokenRepository.findByEmail(user.getEmail())
				.ifPresentOrElse(
					(refreshToken) -> refreshToken.change(newRefreshToken), // 존재한다면 Refresh Token 업데이트
					() -> refreshTokenRepository.save(new RefreshToken(user.getEmail(), newRefreshToken))
					// 존재하지 않다면 Refresh Token 저장
				);

			// response 헤더에 Access Token / Refresh Token 넣음

			setHeader(response, tokenDto);

			return new LoginUserResponse(tokenDto.accessToken(), tokenDto.refreshToken());
		} catch (Exception e) {
			log.error("검증 실패");

			throw new NoSuchElementException(e.getMessage());
		}
	}

	private void setHeader(HttpServletResponse response, TokenDto tokenDto) {
		response.addHeader("Authorization", tokenDto.accessToken());
		response.addHeader(JwtTokenProvider.ACCESS_TOKEN, tokenDto.accessToken());
		response.addHeader(JwtTokenProvider.REFRESH_TOKEN, tokenDto.refreshToken());
	}
}
