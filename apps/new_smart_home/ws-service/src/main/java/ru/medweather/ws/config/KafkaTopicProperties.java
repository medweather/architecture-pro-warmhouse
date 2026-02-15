package ru.medweather.ws.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "kafka-topic")
public class KafkaTopicProperties {
    private TemperatureDataTopic temperatureDataTopic;
    private HomeMonitoringDataTopic homeMonitoringDataTopic;

    @Setter
    @Getter
    public static class TemperatureDataTopic {
        private String name;
        private int partitions;
        private int replicas;
        private String retentionMs;
    }

    @Setter
    @Getter
    public static class HomeMonitoringDataTopic {
        private String name;
        private int partitions;
        private int replicas;
        private String retentionMs;
    }
}
