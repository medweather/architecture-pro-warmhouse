package ru.medweather.api.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "services.heating-service")
public class HeatingServiceProperties {
    private String id;
    private String contextPath;
    private String sensorCode;
}
