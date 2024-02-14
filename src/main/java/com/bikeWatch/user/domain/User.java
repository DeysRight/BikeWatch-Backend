package com.bikeWatch.user.domain;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "users")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // PK

	private String email; // 아이디

	private String password;

	@Enumerated(value = EnumType.STRING)
	private Role role = Role.USER;

	@Builder
	public User(String email, String password, Role role) {
		this.email = email;
		this.password = password;
		this.role = role;
	}

	// 권한 반환
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	// 사용자의 id를 반환(고유한 값)
	@Override
	public String getUsername() {
		return email;
	}

	// 사용자의 패스워드를 반한
	@Override
	public String getPassword() {
		return password;
	}

	// 계정 만료 여부 반환
	@Override
	public boolean isAccountNonExpired() {
		// TODO: 만료되었는지 확인하는 로직
		return true; // true -> 만료되지 않았음
	}

	// 계정 잠금 여부 반환
	@Override
	public boolean isAccountNonLocked() {
		// TODO: 계정 잠금되어있는지 확인하는 로직
		return true; // true -> 잠금되지 않음
	}

	// 패스워드의 만료 여부 반환
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO: 패스워드가 만료되었는지 확인하는 로직
		return true; // true -> 만료되지 않음
	}

	// 계정 사용 가능 여부 반환
	@Override
	public boolean isEnabled() {
		// TODO: 계정이 사용 가능한지 확인하는 로직
		return true; // true -> 사용 가능
	}
}
