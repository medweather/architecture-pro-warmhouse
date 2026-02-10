package ru.medweather.light.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.medweather.light.config.ApiErrorRes;
import ru.medweather.light.service.LightService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sensors")
public class LightController {

    private final LightService lightService;

    @ApiErrorRes
    @PutMapping("/turn-on/{id}")
    @Operation(summary = "Включить свет")
    public String turnOn(@PathVariable Integer id) {
        lightService.turnOn(id);
        return "Свет включен! sensorId = %d".formatted(id);
    }

    @ApiErrorRes
    @PutMapping("/turn-off/{id}")
    @Operation(summary = "Выключить свет")
    public String turnOff(@PathVariable Integer id) {
        lightService.turnOff(id);
        return "Свет выключен! sensorId = %d".formatted(id);
    }
}
