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

    @Override
    public void turnOn(Integer id) {
        heatingSystemClient.turnOn(id);
        kafkaTemplate.send(
                kafkaTopicProperties.getUpdateDeviceStatusTopic().getName(),
                new UpdateStatusDto(id, (short) 1)
        );
    }

    @Override
    public void turnOff(Integer id) {
        heatingSystemClient.turnOff(id);
        kafkaTemplate.send(
                kafkaTopicProperties.getUpdateDeviceStatusTopic().getName(),
                new UpdateStatusDto(id, (short) 2)
        );
    }
}
