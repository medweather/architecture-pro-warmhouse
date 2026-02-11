package ru.medweather.light.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.medweather.light.config.KafkaTopicProperties;
import ru.medweather.light.external.LightSystemClient;
import ru.medweather.light.model.UpdateStatusDto;
import ru.medweather.light.service.LightService;

@Service
@RequiredArgsConstructor
public class LightServiceImpl implements LightService {

    private final KafkaTopicProperties kafkaTopicProperties;
    private final LightSystemClient lightSystemClient;
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
        lightSystemClient.turnOn(id);
        kafkaTemplate.send(
                kafkaTopicProperties.getUpdateDeviceStatusTopic().getName(),
                new UpdateStatusDto(id, ACTIVE_STATUS_ID)
        );
        return "Свет включен! sensorId = %d".formatted(id);
    }

    private String turnOff(Integer id) {
        lightSystemClient.turnOff(id);
        kafkaTemplate.send(
                kafkaTopicProperties.getUpdateDeviceStatusTopic().getName(),
                new UpdateStatusDto(id, INACTIVE_STATUS_ID)
        );
        return "Свет выключен! sensorId = %d".formatted(id);
    }
}
