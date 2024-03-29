package com.hiufestainfo.global.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        ArrayList<String> allowedOriginPatterns = new ArrayList<>();
        allowedOriginPatterns.add("https://2023hiufestainfo.com");
        allowedOriginPatterns.add("https://stag.2023hiufestainfo.com");
        allowedOriginPatterns.add("http://localhost:3000");
        String[] patterns = allowedOriginPatterns.toArray(String[]::new);
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "DELETE", "PATCH")
                .allowedOriginPatterns(patterns)
                .allowCredentials(true);
    }
}
