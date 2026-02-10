package ru.medweather.payment.model.dto;

import lombok.Builder;
import ru.medweather.payment.model.dto.request.BuySensorRequest;

@Builder
public record SensorCreateDto(
        String name,
        String code,
        String location,
        Double value,
        String unit
) {
    public static SensorCreateDto from(BuySensorRequest request) {
        return SensorCreateDto.builder()
                .name(request.name())
                .code(request.sensorCode())
                .location(request.location())
                .value(request.value())
                .unit(request.unit())
                .build();
    }
}
