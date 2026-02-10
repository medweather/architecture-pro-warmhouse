package ru.medweather.manager.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.medweather.manager.model.dto.UpdateStatusDto;
import ru.medweather.manager.service.ManagerService;

@Component
@RequiredArgsConstructor
public class MessageBrokerListener {

    private final ManagerService managerService;

    @KafkaListener(topics = "${kafka-topic.update-device-status-topic.name}")
    public void updateSensorStatus(UpdateStatusDto message) {
        managerService.updateSensorStatus(message);
    }
}
