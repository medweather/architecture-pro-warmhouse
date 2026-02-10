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

    @Override
    public void turnOn(Integer id) {
        automaticGatesSystemClient.turnOn(id);
        kafkaTemplate.send(
                kafkaTopicProperties.getUpdateDeviceStatusTopic().getName(),
                new UpdateStatusDto(id, (short) 1)
        );
    }

    @Override
    public void turnOff(Integer id) {
        automaticGatesSystemClient.turnOff(id);
        kafkaTemplate.send(
                kafkaTopicProperties.getUpdateDeviceStatusTopic().getName(),
                new UpdateStatusDto(id, (short) 2)
        );
    }
}
