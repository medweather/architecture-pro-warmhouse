package ru.medweather.manager.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "kafka-topic")
public class KafkaTopicProperties {
    private UpdateDeviceStatusTopic updateDeviceStatusTopic;

    @Setter
    @Getter
    public static class UpdateDeviceStatusTopic {
        private String name;
        private int partitions;
        private int replicas;
        private String retentionMs;
    }
}
