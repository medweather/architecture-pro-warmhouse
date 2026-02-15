package ru.medweather.api.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "services.manager-service")
public class ManagerServiceProperties {
    private String id;
    private String contextPath;
}
