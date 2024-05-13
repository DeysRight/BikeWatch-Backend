package com.bikeWatch.common.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.bikeWatch.jwt.security.JwtAuthenticationFilter;
import com.bikeWatch.jwt.security.JwtProperties;
import com.bikeWatch.jwt.security.JwtTokenProvider;
import com.bikeWatch.user.domain.Role;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

	private final CorsConfigurationSource corsConfigurationSource;
	private final UserDetailsService userDetailsService;
	private final JwtProperties jwtProperties;

	@Bean
	public JwtTokenProvider jwtTokenProvider() {
		return new JwtTokenProvider(userDetailsService, jwtProperties);
	}

	@Bean
	public WebSecurityCustomizer configure() {
		return (web) -> web.ignoring()
			.requestMatchers("/static/**");
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers(new AntPathRequestMatcher("/api/**/admin")).hasRole(Role.ROLE_ADMIN.getLabel())
				.requestMatchers("/**").permitAll()
				.anyRequest().authenticated()
			)
			.csrf(AbstractHttpConfigurer::disable) // 백 작업시 필요없고 403 포비든 에러, 프론트(ssr일 경우인 듯)가 붙으면 csrf() 켜줘야한다.
			.cors(config -> config.configurationSource(corsConfigurationSource))
			.httpBasic(AbstractHttpConfigurer::disable) // login form redirect 필요 X (rest api)
			.formLogin(AbstractHttpConfigurer::disable) // 너가 만들어주는 폼 로그인 안쓰고싶어
			.rememberMe(AbstractHttpConfigurer::disable)
			.logout(AbstractHttpConfigurer::disable)
			.sessionManagement(
				config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // jwt token으로 인증하므로 세션 필요 X
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider()),
				UsernamePasswordAuthenticationFilter.class) // 1번 파라미터 구현체를 2번 파라미터 타입으로 사용
			.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOriginPattern("*");
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(
		AuthenticationConfiguration authenticationConfiguration
	) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
