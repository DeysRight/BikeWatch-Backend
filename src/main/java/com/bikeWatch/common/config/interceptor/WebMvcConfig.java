package com.bikeWatch.common.config.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(visitorStatisticsInterceptor())
			.order(1)
			.addPathPatterns("/api/informations")
			.excludePathPatterns("/css/**", "/*.ico", "/error");
	}

	@Bean
	public VisitorStatisticsInterceptor visitorStatisticsInterceptor() {
		return new VisitorStatisticsInterceptor();
	}
}
