package ru.medweather.homemonitoring.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.medweather.homemonitoring.config.ApiErrorRes;
import ru.medweather.homemonitoring.service.HomeMonitoringService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sensors")
public class HomeMonitoringController {

    private final HomeMonitoringService homeMonitoringService;

    @ApiErrorRes
    @PutMapping("/turn-on/{id}")
    @Operation(summary = "Включить наблюдение за домом")
    public String turnOn(@PathVariable Integer id) {
        homeMonitoringService.turnOn(id);
        return "Наблюдение за домом включено! sensorId = %d".formatted(id);
    }

    @ApiErrorRes
    @PutMapping("/turn-off/{id}")
    @Operation(summary = "Выключить наблюдение за домом")
    public String turnOff(@PathVariable Integer id) {
        homeMonitoringService.turnOff(id);
        return "Наблюдение за домом отключено! sensorId = %d".formatted(id);
    }
}
