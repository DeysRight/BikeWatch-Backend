package com.bikeWatch.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bikeWatch.user.domain.User;
import com.bikeWatch.user.dto.request.JoinUserRequest;
import com.bikeWatch.user.dto.response.JoinUserResponse;
import com.bikeWatch.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Transactional
	public JoinUserResponse save(JoinUserRequest req) {
		User user = userRepository.save(req.toEntity(bCryptPasswordEncoder.encode(req.password())));

		return JoinUserResponse.of(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username).orElseThrow();
	}
}
