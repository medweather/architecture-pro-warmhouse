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
    @PutMapping("/{id}/status/{statusId}")
    @Operation(summary = "Включить/выключить наблюдение за домом")
    public String turnOn(@PathVariable Integer id, @PathVariable Short statusId) {
        return homeMonitoringService.updateStatus(id, statusId);
    }
}
