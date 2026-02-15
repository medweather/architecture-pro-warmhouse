package ru.medweather.manager.model.dto;

import ru.medweather.manager.model.SensorsStatus;

public record SensorStatusDto(Short id, String name) {
    public static SensorStatusDto from(SensorsStatus status) {
        return new SensorStatusDto(status.getId(), status.getName());
    }
}
