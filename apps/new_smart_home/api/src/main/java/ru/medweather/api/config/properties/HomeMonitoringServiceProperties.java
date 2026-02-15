package ru.medweather.api.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@ConfigurationProperties(prefix = "services.home-monitoring-service")
public class HomeMonitoringServiceProperties {
    private String id;
    private String contextPath;
    private String sensorCode;
}
