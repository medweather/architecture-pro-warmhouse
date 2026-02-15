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
    @PutMapping("/{id}/status/{statusId}")
    @Operation(summary = "Включить/выключить мониторинг температуры")
    public String turnOn(@PathVariable Integer id, @PathVariable Short statusId) {
        return temperatureService.updateStatus(id, statusId);
    }
}
