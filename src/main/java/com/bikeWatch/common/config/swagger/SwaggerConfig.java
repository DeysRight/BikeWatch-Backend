package com.bikeWatch.common.config.swagger;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
	info = @Info(title = "바이크워치(BikeWatch) 서비스 API 명세서",
		description = "오토바이 24시 출장 수리 바이크워치(BikeWatch) 웹 서비스 API 명세서 입니다.",
		version = "v1.0.0"))
@Configuration
public class SwaggerConfig {
}
