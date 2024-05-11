package com.bikeWatch.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikeWatch.user.dto.request.JoinUserRequest;
import com.bikeWatch.user.dto.request.LoginUserRequest;
import com.bikeWatch.user.dto.response.JoinUserResponse;
import com.bikeWatch.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Tag(name = "user-controller", description = "회원 서비스를 위한 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	@Operation(summary = "회원가입", description = "회원을 생성합니다.")
	@PostMapping("/join")
	public JoinUserResponse join(@RequestBody JoinUserRequest req) {
		return userService.save(req);
	}

	@GetMapping("/status")
	public Map status() {
		Map<String, Object> map = new HashMap<>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		boolean isAuthenticated = authentication.isAuthenticated();
		Object principal = authentication.getPrincipal();
		map.put("userName", userName);
		map.put("isAuthenticated", isAuthenticated);
		map.put("principal", principal);

		return map;
	}

	@Operation(summary = "로그인", description = "로그인을 합니다.")
	@PostMapping("/login")
	public void login(@RequestBody LoginUserRequest req, HttpServletResponse response) {
		userService.login(req, response);
	}
}
