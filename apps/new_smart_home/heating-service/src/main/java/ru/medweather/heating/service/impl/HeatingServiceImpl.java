package ru.medweather.heating.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.medweather.heating.config.KafkaTopicProperties;
import ru.medweather.heating.service.HeatingService;
import ru.medweather.heating.external.HeatingSystemClient;
import ru.medweather.heating.model.UpdateStatusDto;

@Service
@RequiredArgsConstructor
public class HeatingServiceImpl implements HeatingService {

    private final KafkaTopicProperties kafkaTopicProperties;
    private final HeatingSystemClient heatingSystemClient;
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
        heatingSystemClient.turnOn(id);
        kafkaTemplate.send(
                kafkaTopicProperties.getUpdateDeviceStatusTopic().getName(),
                new UpdateStatusDto(id, ACTIVE_STATUS_ID)
        );
        return "Отопление включено! sensorId = %d".formatted(id);
    }

    private String turnOff(Integer id) {
        heatingSystemClient.turnOff(id);
        kafkaTemplate.send(
                kafkaTopicProperties.getUpdateDeviceStatusTopic().getName(),
                new UpdateStatusDto(id, INACTIVE_STATUS_ID)
        );
        return "Отопление отключено! sensorId = %d".formatted(id);
    }
}
