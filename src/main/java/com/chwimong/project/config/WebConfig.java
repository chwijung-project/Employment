package com.chwimong.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.chwimong.project.interceptor.SwaggerAccessInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Value("${cors.allowed-origins:http://localhost:3000}")
    private String[] allowedOrigins;
	
	@Autowired
    private SwaggerAccessInterceptor swaggerAccessInterceptor;
	
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(swaggerAccessInterceptor)
                .addPathPatterns("/swagger-ui/**", "/v3/api-docs/**");
    }
}
