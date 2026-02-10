package ru.medweather.manager.model.dto;

public record SensorCreateDto(
        String name,
        String code,
        String location,
        Double value,
        String unit
) {
}
