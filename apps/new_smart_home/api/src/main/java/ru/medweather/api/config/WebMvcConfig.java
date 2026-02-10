package ru.medweather.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.medweather.api.config.properties.CorsProperties;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig {

    private final CorsProperties corsProperties;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(corsProperties.getAllowedOrigins())
                        .allowedMethods(corsProperties.getAllowedMethods())
                        .allowedHeaders(corsProperties.getAllowedHeaders())
                        .allowCredentials(corsProperties.getAllowCredentials());
            }
        };
    }
}
