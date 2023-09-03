package com.practice.notion.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/*")
                .allowedOrigins("*") //TODO 환경에 맞게 바꿀 것
                .allowedMethods(
                        HttpMethod.OPTIONS.name(), HttpMethod.GET.name(),
                        HttpMethod.POST.name(), HttpMethod.PUT.name(),
                        HttpMethod.PATCH.name(), HttpMethod.DELETE.name(),
                        HttpMethod.HEAD.name()
                );
    }
}
