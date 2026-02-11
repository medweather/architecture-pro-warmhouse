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

    private static final Short ACTIVE_STATUS_ID = 1;
    private static final Short INACTIVE_STATUS_ID = 2;

    @Override
    public String updateStatus(Integer id, Short statusId) {
        return switch (statusId) {
            case 1 -> turnOn(id);
            case 2 -> turnOff(id);
            default -> "Не корректно передана команда устройству!";
        };
    }

    private String turnOn(Integer id) {
        homeMonitoringClient.turnOn(id);
        kafkaTemplate.send(
                kafkaTopicProperties.getUpdateDeviceStatusTopic().getName(),
                new UpdateStatusDto(id, ACTIVE_STATUS_ID)
        );
        return "Наблюдение за домом включено! sensorId = %d".formatted(id);
    }

    private String turnOff(Integer id) {
        homeMonitoringClient.turnOff(id);
        kafkaTemplate.send(
                kafkaTopicProperties.getUpdateDeviceStatusTopic().getName(),
                new UpdateStatusDto(id, INACTIVE_STATUS_ID)
        );
        return "Наблюдение за домом отключено! sensorId = %d".formatted(id);
    }
}
