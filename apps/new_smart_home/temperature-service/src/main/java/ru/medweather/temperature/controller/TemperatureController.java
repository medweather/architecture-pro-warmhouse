package ru.medweather.temperature.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.medweather.temperature.config.ApiErrorRes;
import ru.medweather.temperature.service.TemperatureService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sensors")
public class TemperatureController {

    private final TemperatureService temperatureService;

    @ApiErrorRes
    @PutMapping("/turn-on/{id}")
    @Operation(summary = "Включить мониторинг температуры")
    public String turnOn(@PathVariable Integer id) {
        temperatureService.turnOn(id);
        return "Мониторинг температуры включен! sensorId = %d".formatted(id);
    }

    @ApiErrorRes
    @PutMapping("/turn-off/{id}")
    @Operation(summary = "Выключить мониторинг температуры")
    public String turnOff(@PathVariable Integer id) {
        temperatureService.turnOff(id);
        return "Мониторинг температуры выключен! sensorId = %d".formatted(id);
    }
}
