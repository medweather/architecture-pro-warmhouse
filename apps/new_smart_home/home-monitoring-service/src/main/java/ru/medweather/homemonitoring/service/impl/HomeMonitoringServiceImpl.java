package ru.medweather.homemonitoring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.medweather.homemonitoring.config.KafkaTopicProperties;
import ru.medweather.homemonitoring.external.HomeMonitoringClient;
import ru.medweather.homemonitoring.model.UpdateStatusDto;
import ru.medweather.homemonitoring.service.HomeMonitoringService;

@Service
@RequiredArgsConstructor
public class HomeMonitoringServiceImpl implements HomeMonitoringService {

    private final KafkaTopicProperties kafkaTopicProperties;
    private final HomeMonitoringClient homeMonitoringClient;
    private final KafkaTemplate<String, UpdateStatusDto> kafkaTemplate;

    @Override
    public void turnOn(Integer id) {
        homeMonitoringClient.turnOn(id);
        kafkaTemplate.send(
                kafkaTopicProperties.getUpdateDeviceStatusTopic().getName(),
                new UpdateStatusDto(id, (short) 1)
        );
    }

    @Override
    public void turnOff(Integer id) {
        homeMonitoringClient.turnOff(id);
        kafkaTemplate.send(
                kafkaTopicProperties.getUpdateDeviceStatusTopic().getName(),
                new UpdateStatusDto(id, (short) 2)
        );
    }
}
