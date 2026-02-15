package ru.medweather.ws.service;

import ru.medweather.ws.model.HomeMonitoringData;
import ru.medweather.ws.model.TemperatureData;

public interface WsService {
    void sendHomeMonitoringData(HomeMonitoringData data);
    void sendTemperatureData(TemperatureData data);
}
