package com.bikeWatch.common.config.webMvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(visitorStatisticsInterceptor())
			.addPathPatterns("/**");
	}

	@Bean
	public VisitorStatisticsInterceptor visitorStatisticsInterceptor() {
		return new VisitorStatisticsInterceptor();
	}
}
