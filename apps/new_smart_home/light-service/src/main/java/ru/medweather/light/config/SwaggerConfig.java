package ru.medweather.light.config;

import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.converter.ResolvedSchema;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.medweather.light.exception.CustomExceptionResponse;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${swagger.server.api}")
    private String apiServerUrl;

    @Value("${eureka.client.enabled}")
    private Boolean eurekaEnabled;

    @Bean
    public OpenAPI openAPI() {
        OpenAPI openAPI = new OpenAPI()
                .info(new Info().title("Light service"));
        if (eurekaEnabled) openAPI.setServers(List.of(
                new Server().url(apiServerUrl).description("API (light)")
        ));
        return openAPI;
    }

    @Bean
    public OpenApiCustomizer openApiCustomizer() {
        ResolvedSchema errResSchema =
                ModelConverters.getInstance().resolveAsResolvedSchema(new AnnotatedType(ApiErrorRes.class));
        return openApi ->
                openApi
                        .getPaths()
                        .values()
                        .forEach(pathItem -> pathItem
                                .readOperations()
                                .forEach(operation -> operation
                                        .getResponses()
                                        .addApiResponse(
                                                "400",
                                                new ApiResponse()
                                                        .description("Bad Request")
                                                        .content(buildContent400(errResSchema)))
                                        .addApiResponse(
                                                "404",
                                                new ApiResponse()
                                                        .description("Resource Not Found")
                                                        .content(buildContent404(errResSchema)))
                                        .addApiResponse(
                                                "500",
                                                new ApiResponse()
                                                        .description("Internal Server Error")
                                                        .content(buildContent500(errResSchema)))));
    }

    private static Content buildContent400(ResolvedSchema errResSchema) {
        Example example400 = new Example();
        example400.setValue(new CustomExceptionResponse(
                400,
                "Bad request",
                "Illegal parameter"
        ));
        MediaType schema400 = new MediaType().schema(errResSchema.schema).addExamples("example400", example400);
        return new Content().addMediaType("application/json", schema400);
    }

    private static Content buildContent404(ResolvedSchema errResSchema) {
        Example example404 = new Example();
        example404.setValue(new CustomExceptionResponse(
                404,
                "Not Found",
                "NotFoundException"
        ));
        MediaType schema404 = new MediaType().schema(errResSchema.schema).addExamples("example404", example404);
        return new Content().addMediaType("application/json", schema404);
    }

    private static Content buildContent500(ResolvedSchema errResSchema) {
        Example example500 = new Example();
        example500.setValue(new CustomExceptionResponse(
                500,
                "Internal Server Error",
                "RuntimeException"
        ));
        MediaType schema500 = new MediaType().schema(errResSchema.schema).addExamples("example500", example500);
        return new Content().addMediaType("application/json", schema500);
    }
}
