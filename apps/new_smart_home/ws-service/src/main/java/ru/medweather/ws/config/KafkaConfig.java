package ru.medweather.ws.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    private final KafkaTopicProperties kafkaTopicProperties;

    @Bean
    public NewTopic homeMonitoringDataTopic() {
        return TopicBuilder.name(kafkaTopicProperties.getHomeMonitoringDataTopic().getName())
                .partitions(kafkaTopicProperties.getHomeMonitoringDataTopic().getPartitions())
                .replicas(kafkaTopicProperties.getHomeMonitoringDataTopic().getReplicas())
                .config(TopicConfig.RETENTION_MS_CONFIG, kafkaTopicProperties.getHomeMonitoringDataTopic().getRetentionMs())
                .config(TopicConfig.LOCAL_LOG_RETENTION_MS_CONFIG, kafkaTopicProperties.getHomeMonitoringDataTopic().getRetentionMs())
                .build();
    }

    @Bean
    public NewTopic temperatureDataTopic() {
        return TopicBuilder.name(kafkaTopicProperties.getTemperatureDataTopic().getName())
                .partitions(kafkaTopicProperties.getTemperatureDataTopic().getPartitions())
                .replicas(kafkaTopicProperties.getTemperatureDataTopic().getReplicas())
                .config(TopicConfig.RETENTION_MS_CONFIG, kafkaTopicProperties.getTemperatureDataTopic().getRetentionMs())
                .config(TopicConfig.LOCAL_LOG_RETENTION_MS_CONFIG, kafkaTopicProperties.getTemperatureDataTopic().getRetentionMs())
                .build();
    }
}
