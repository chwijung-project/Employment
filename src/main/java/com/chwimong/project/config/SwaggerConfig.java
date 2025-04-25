package com.chwimong.project.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
	info = @Info(
		title = "Chwimong API",
		description = "API Documentation for Chwimong Service",
		version = "1.0.0"
	),
	servers = {
        @Server(
            description = "개발 서버",
            url = "http://localhost:8080"
        ),
        @Server(
            description = "운영 서버",
            url = "http://www.employ.chwimong.com"
        )
    }
	
)

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {
	@Bean
	GroupedOpenApi employmentApi() {
	    return GroupedOpenApi.builder()
	            .group("채용정보 API")
	            .pathsToMatch("/v1/employments/**")
	            .build();
	}

	@Bean
	GroupedOpenApi compactEmploymentApi() {
	    return GroupedOpenApi.builder()
	            .group("요약 채용정보 API")
	            .pathsToMatch("/v1/view/compact-employments/**")
	            .build();
	}

	@Bean
	GroupedOpenApi mentorPickApi() {
	    return GroupedOpenApi.builder()
	            .group("멘토픽 API")
	            .pathsToMatch("/v1/mentorpicks/**")
	            .build();
	}
	
	@Bean
	GroupedOpenApi projectApi() {
	    return GroupedOpenApi.builder()
	            .group("프로젝트 정보 API")
	            .pathsToMatch("/v1/educations/projects/**")
	            .build();
	}

	@Bean
	GroupedOpenApi commercialApi() {
	    return GroupedOpenApi.builder()
	            .group("부트캠프 정보 API")
	            .pathsToMatch("/v1/educations/commercials/**")
	            .build();
	}
}
