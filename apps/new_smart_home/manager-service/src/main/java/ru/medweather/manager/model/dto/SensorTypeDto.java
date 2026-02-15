package ru.medweather.manager.model.dto;

import ru.medweather.manager.model.SensorsType;

public record SensorTypeDto(Short id, String code, String name) {
    public static SensorTypeDto from(SensorsType type) {
        return new SensorTypeDto(type.getId(), type.getCode(), type.getName());
    }
}
