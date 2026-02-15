package ru.medweather.manager.config;

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
    public NewTopic updateDeviceStatusTopic() {
        return TopicBuilder.name(kafkaTopicProperties.getUpdateDeviceStatusTopic().getName())
                .partitions(kafkaTopicProperties.getUpdateDeviceStatusTopic().getPartitions())
                .replicas(kafkaTopicProperties.getUpdateDeviceStatusTopic().getReplicas())
                .config(TopicConfig.RETENTION_MS_CONFIG, kafkaTopicProperties.getUpdateDeviceStatusTopic().getRetentionMs())
                .config(TopicConfig.LOCAL_LOG_RETENTION_MS_CONFIG, kafkaTopicProperties.getUpdateDeviceStatusTopic().getRetentionMs())
                .build();
    }

}
