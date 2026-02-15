package ru.medweather.ws.model;

import java.time.LocalDateTime;

public record TemperatureData(Integer sensorId, Integer value, LocalDateTime timestamp) {
}
