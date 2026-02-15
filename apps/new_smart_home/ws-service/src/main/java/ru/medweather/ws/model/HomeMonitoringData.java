package ru.medweather.ws.model;

import java.time.LocalDateTime;

public record HomeMonitoringData(Integer sensorId, String value, LocalDateTime timestamp) {
}
