package ru.medweather.api.config;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import ru.medweather.api.client.ManagerClient;
import ru.medweather.api.config.properties.*;
import ru.medweather.api.exception.handler.CustomExceptionResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.rewritePath;
import static org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions.lb;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates.path;
import static ru.medweather.api.config.PublicUrls.requestIsPublic;

@Configuration
@RequiredArgsConstructor
public class RoutesConfig {

    public static final String REWRITE_PATH_PATTERN = "/(?<path>.*)";
    public static final String REPLACEMENT_PATH = "/$\\{path}";
    public static final String ALL_APP_PATHS = "/**";

    private final ManagerServiceProperties managerServiceProperties;
    private final PaymentServiceProperties paymentServiceProperties;
    private final HeatingServiceProperties heatingServiceProperties;
    private final TemperatureServiceProperties temperatureServiceProperties;
    private final HomeMonitoringServiceProperties homeMonitoringServiceProperties;
    private final LightServiceProperties lightServiceProperties;
    private final AutomaticGatesServiceProperties automaticGatesServiceProperties;
    private final ManagerClient managerClient;

    @Bean
    public RouterFunction<@NonNull ServerResponse> managerRoute() {
        return GatewayRouterFunctions
                .route(managerServiceProperties.getId())
                .filter(lb(managerServiceProperties.getId()))
                .route(path(managerServiceProperties.getContextPath() + ALL_APP_PATHS), http())
                .filter(rewritePath(managerServiceProperties.getContextPath() + REWRITE_PATH_PATTERN, REPLACEMENT_PATH))
                .build();
    }

    @Bean
    public RouterFunction<@NonNull ServerResponse> paymentRoute() {
        return GatewayRouterFunctions
                .route(paymentServiceProperties.getId())
                .filter(lb(paymentServiceProperties.getId()))
                .route(path(paymentServiceProperties.getContextPath() + ALL_APP_PATHS), http())
                .filter(rewritePath(paymentServiceProperties.getContextPath() + REWRITE_PATH_PATTERN, REPLACEMENT_PATH))
                .build();
    }

    @Bean
    public RouterFunction<@NonNull ServerResponse> heatingRoute() {
        return GatewayRouterFunctions
                .route(heatingServiceProperties.getId())
                .filter(lb(heatingServiceProperties.getId()))
                .route(path(heatingServiceProperties.getContextPath() + ALL_APP_PATHS), http())
                .filter(rewritePath(heatingServiceProperties.getContextPath() + REWRITE_PATH_PATTERN, REPLACEMENT_PATH))
                .filter(checkAccessSensorFilter(heatingServiceProperties.getSensorCode()))
                .build();
    }

    @Bean
    public RouterFunction<@NonNull ServerResponse> temperatureRoute() {
        return GatewayRouterFunctions
                .route(temperatureServiceProperties.getId())
                .filter(lb(temperatureServiceProperties.getId()))
                .route(path(temperatureServiceProperties.getContextPath() + ALL_APP_PATHS), http())
                .filter(rewritePath(temperatureServiceProperties.getContextPath() + REWRITE_PATH_PATTERN, REPLACEMENT_PATH))
                .filter(checkAccessSensorFilter(temperatureServiceProperties.getSensorCode()))
                .build();
    }

    @Bean
    public RouterFunction<@NonNull ServerResponse> homeMonitoringRoute() {
        return GatewayRouterFunctions
                .route(homeMonitoringServiceProperties.getId())
                .filter(lb(homeMonitoringServiceProperties.getId()))
                .route(path(homeMonitoringServiceProperties.getContextPath() + ALL_APP_PATHS), http())
                .filter(rewritePath(homeMonitoringServiceProperties.getContextPath() + REWRITE_PATH_PATTERN, REPLACEMENT_PATH))
                .filter(checkAccessSensorFilter(homeMonitoringServiceProperties.getSensorCode()))
                .build();
    }

    @Bean
    public RouterFunction<@NonNull ServerResponse> lightRoute() {
        return GatewayRouterFunctions
                .route(lightServiceProperties.getId())
                .filter(lb(lightServiceProperties.getId()))
                .route(path(lightServiceProperties.getContextPath() + ALL_APP_PATHS), http())
                .filter(rewritePath(lightServiceProperties.getContextPath() + REWRITE_PATH_PATTERN, REPLACEMENT_PATH))
                .filter(checkAccessSensorFilter(lightServiceProperties.getSensorCode()))
                .build();
    }

    @Bean
    public RouterFunction<@NonNull ServerResponse> automaticGatesRoute() {
        return GatewayRouterFunctions
                .route(automaticGatesServiceProperties.getId())
                .filter(lb(automaticGatesServiceProperties.getId()))
                .route(path(automaticGatesServiceProperties.getContextPath() + ALL_APP_PATHS), http())
                .filter(rewritePath(automaticGatesServiceProperties.getContextPath() + REWRITE_PATH_PATTERN, REPLACEMENT_PATH))
                .filter(checkAccessSensorFilter(automaticGatesServiceProperties.getSensorCode()))
                .build();
    }

    private HandlerFilterFunction<@NonNull ServerResponse, @NonNull ServerResponse> checkAccessSensorFilter(String sensorCode) {
        return (request, next) -> {
            if (requestIsPublic(request) || accessToSensor(sensorCode)) return next.handle(request);
            return ServerResponse
                    .status(HttpStatus.NOT_FOUND)
                    .body(new CustomExceptionResponse(
                            HttpStatus.NOT_FOUND.value(),
                            "У вас нет возможности управлять данным устройством! [sensorCode=%s]".formatted(sensorCode),
                            "Проверить список устройств, доступных вам, можно через manager-service"
                    ));
        };
    }

    private boolean accessToSensor(String sensorCode) {
        return managerClient.checkSensor(sensorCode);
    }
}
