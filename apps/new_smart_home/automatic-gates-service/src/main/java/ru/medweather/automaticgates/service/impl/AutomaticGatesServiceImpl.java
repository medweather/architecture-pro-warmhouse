package ru.medweather.automaticgates.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.medweather.automaticgates.service.AutomaticGatesService;
import ru.medweather.automaticgates.config.KafkaTopicProperties;
import ru.medweather.automaticgates.external.AutomaticGatesSystemClient;
import ru.medweather.automaticgates.model.UpdateStatusDto;

@Service
@RequiredArgsConstructor
public class AutomaticGatesServiceImpl implements AutomaticGatesService {

    private final KafkaTopicProperties kafkaTopicProperties;
    private final AutomaticGatesSystemClient automaticGatesSystemClient;
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
        automaticGatesSystemClient.turnOn(id);
        kafkaTemplate.send(
                kafkaTopicProperties.getUpdateDeviceStatusTopic().getName(),
                new UpdateStatusDto(id, ACTIVE_STATUS_ID)
        );
        return "Ворота открылись! sensorId = %d".formatted(id);
    }

    private String turnOff(Integer id) {
        automaticGatesSystemClient.turnOff(id);
        kafkaTemplate.send(
                kafkaTopicProperties.getUpdateDeviceStatusTopic().getName(),
                new UpdateStatusDto(id, INACTIVE_STATUS_ID)
        );
        return "Ворота закрылись! sensorId = %d".formatted(id);
    }
}
