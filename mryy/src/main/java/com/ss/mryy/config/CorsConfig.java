package com.ss.mryy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author:ljy.s
 * @Date:2023/4/28 - 04 - 28 - 15:19
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    public static final String[] ORIGINS = {"GET", "POST", "PUT", "DELETE", "PATCH"};

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders(CorsConfiguration.ALL)
                .allowedMethods(ORIGINS)
                .allowCredentials(true)
                .maxAge(3600);
    }
}