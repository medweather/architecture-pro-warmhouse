package ru.medweather.ws.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.medweather.ws.model.HomeMonitoringData;
import ru.medweather.ws.model.TemperatureData;
import ru.medweather.ws.service.WsService;

@Component
@RequiredArgsConstructor
public class MessageBrokerListener {

    private final WsService wsService;

    @KafkaListener(topics = "${kafka-topic.temperature-data-topic.name}")
    public void sendTemperateData(TemperatureData message) {
        wsService.sendTemperatureData(message);
    }

    @KafkaListener(topics = "${kafka-topic.home-monitoring-data-topic.name}")
    public void sendHomeMonitoringData(HomeMonitoringData message) {
        wsService.sendHomeMonitoringData(message);
    }
}
