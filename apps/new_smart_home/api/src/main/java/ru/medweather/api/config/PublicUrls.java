package ru.medweather.api.config;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.function.ServerRequest;

import java.util.List;

@Slf4j
public class PublicUrls {
    public static final List<String> SWAGGER_PATHS = List.of(
            "/v3/api-docs",
            "/swagger-resources",
            "/swagger-resources/",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/",
            "/swagger-ui/"
    );

    public static boolean requestIsPublic(@NotNull ServerRequest request) {
        return requestIsSwaggerPath(request);
    }

    private static boolean requestIsSwaggerPath(@NotNull ServerRequest request) {
        return SWAGGER_PATHS.stream()
                .anyMatch(swaggerPath -> request.path().contains(swaggerPath));
    }
}
