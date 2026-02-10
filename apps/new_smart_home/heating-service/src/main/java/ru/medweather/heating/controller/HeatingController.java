package ru.medweather.heating.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.medweather.heating.config.ApiErrorRes;
import ru.medweather.heating.service.HeatingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sensors")
public class HeatingController {

    private final HeatingService heatingService;

    @ApiErrorRes
    @PutMapping("/turn-on/{id}")
    @Operation(summary = "Включить отопление")
    public String turnOn(@PathVariable Integer id) {
        heatingService.turnOn(id);
        return "Отопление включено! sensorId = %d".formatted(id);
    }

    @ApiErrorRes
    @PutMapping("/turn-off/{id}")
    @Operation(summary = "Выключить отопление")
    public String turnOff(@PathVariable Integer id) {
        heatingService.turnOff(id);
        return "Отопление выключено! sensorId = %d".formatted(id);
    }
}
