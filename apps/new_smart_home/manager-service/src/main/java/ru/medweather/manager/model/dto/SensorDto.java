package ru.medweather.manager.model.dto;

import lombok.Builder;
import ru.medweather.manager.model.Sensor;

import java.time.LocalDateTime;

@Builder
public record SensorDto(
        Integer id,
        String name,
        SensorTypeDto type,
        AddressDto address,
        Double value,
        String unit,
        SensorStatusDto status,
        LocalDateTime lastUpdated,
        LocalDateTime createdAt) {

    public static SensorDto from(Sensor sensor) {
        return SensorDto.builder()
                .id(sensor.getId())
                .name(sensor.getName())
                .type(SensorTypeDto.from(sensor.getType()))
                .address(AddressDto.from(sensor.getAddress()))
                .value(sensor.getValue())
                .unit(sensor.getUnit())
                .status(SensorStatusDto.from(sensor.getStatus()))
                .lastUpdated(sensor.getLastUpdated())
                .createdAt(sensor.getCreatedAt())
                .build();
    }
}
