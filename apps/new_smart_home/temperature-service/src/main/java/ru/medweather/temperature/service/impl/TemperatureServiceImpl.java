package ru.medweather.temperature.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.medweather.temperature.config.KafkaTopicProperties;
import ru.medweather.temperature.external.HeatingClient;
import ru.medweather.temperature.model.UpdateStatusDto;
import ru.medweather.temperature.service.TemperatureService;

@Service
@RequiredArgsConstructor
public class TemperatureServiceImpl implements TemperatureService {

    private final KafkaTopicProperties kafkaTopicProperties;
    private final HeatingClient heatingClient;
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
        heatingClient.turnOn(id);
        kafkaTemplate.send(
                kafkaTopicProperties.getUpdateDeviceStatusTopic().getName(),
                new UpdateStatusDto(id, ACTIVE_STATUS_ID)
        );
        return "Мониторинг температуры включен! sensorId = %d".formatted(id);
    }

    private String turnOff(Integer id) {
        heatingClient.turnOff(id);
        kafkaTemplate.send(
                kafkaTopicProperties.getUpdateDeviceStatusTopic().getName(),
                new UpdateStatusDto(id, INACTIVE_STATUS_ID)
        );
        return "Мониторинг температуры отключен! sensorId = %d".formatted(id);
    }
}
