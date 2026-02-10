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

    @Override
    public void turnOn(Integer id) {
        lightSystemClient.turnOn(id);
        kafkaTemplate.send(
                kafkaTopicProperties.getUpdateDeviceStatusTopic().getName(),
                new UpdateStatusDto(id, (short) 1)
        );
    }

    @Override
    public void turnOff(Integer id) {
        lightSystemClient.turnOff(id);
        kafkaTemplate.send(
                kafkaTopicProperties.getUpdateDeviceStatusTopic().getName(),
                new UpdateStatusDto(id, (short) 2)
        );
    }
}
