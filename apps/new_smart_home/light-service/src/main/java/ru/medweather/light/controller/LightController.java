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
    @PutMapping("/{id}/status/{statusId}")
    @Operation(summary = "Включить/выключить свет")
    public String turnOn(@PathVariable Integer id, @PathVariable Short statusId) {
        return lightService.updateStatus(id, statusId);
    }
}
