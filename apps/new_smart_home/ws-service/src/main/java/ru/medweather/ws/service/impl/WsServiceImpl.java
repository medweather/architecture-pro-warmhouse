package ru.medweather.ws.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.medweather.ws.model.HomeMonitoringData;
import ru.medweather.ws.model.TemperatureData;
import ru.medweather.ws.service.WsService;

@Slf4j
@Service
@RequiredArgsConstructor
public class WsServiceImpl implements WsService {

    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void sendHomeMonitoringData(HomeMonitoringData data) {
        log.info("Получение данных наблюдения за домом: {}", data);
        messagingTemplate.convertAndSend("/topic/home-monitoring-data", data);
    }

    @Override
    public void sendTemperatureData(TemperatureData data) {
        log.info("Получение данных мониторинга температуры: {}", data);
        messagingTemplate.convertAndSend("/topic/temperature-data", data);
    }
}
