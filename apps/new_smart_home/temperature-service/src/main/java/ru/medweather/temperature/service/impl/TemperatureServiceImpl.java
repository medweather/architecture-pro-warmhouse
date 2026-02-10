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

    @Override
    public void turnOn(Integer id) {
        heatingClient.turnOn(id);
        kafkaTemplate.send(
                kafkaTopicProperties.getUpdateDeviceStatusTopic().getName(),
                new UpdateStatusDto(id, (short) 1)
        );
    }

    @Override
    public void turnOff(Integer id) {
        heatingClient.turnOff(id);
        kafkaTemplate.send(
                kafkaTopicProperties.getUpdateDeviceStatusTopic().getName(),
                new UpdateStatusDto(id, (short) 2)
        );
    }
}
