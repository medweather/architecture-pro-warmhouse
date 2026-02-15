package ru.medweather.manager.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.medweather.manager.config.ApiErrorRes;
import ru.medweather.manager.model.dto.SensorCreateDto;
import ru.medweather.manager.model.dto.SensorDto;
import ru.medweather.manager.model.dto.SensorTypeDto;
import ru.medweather.manager.service.ManagerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sensors")
public class ManagerController {

    private final ManagerService managerService;

    @ApiErrorRes
    @GetMapping("/types")
    @Operation(summary = "Все возможные типы устройств в системе")
    public List<SensorTypeDto> sensorTypes() {
        return managerService.sensorTypes();
    }

    @ApiErrorRes
    @GetMapping
    @Operation(summary = "Подключенные датчики")
    public List<SensorDto> availableSensors() {
        return managerService.availableSensors();
    }

    @ApiErrorRes
    @GetMapping("/check/{sensorCode}")
    @Operation(summary = "Проверка подключения устройства к умному дому")
    public Boolean checkSensor(@PathVariable String sensorCode) {
        return managerService.checkSensor(sensorCode);
    }

    @ApiErrorRes
    @GetMapping("/check-for-buy/{sensorCode}")
    @Operation(summary = "Проверка доступности устройства для покупки")
    public Boolean checkSensorForBuy(@PathVariable String sensorCode) {
        return managerService.checkSensorForBuy(sensorCode);
    }

    @ApiErrorRes
    @PostMapping("/create")
    @Operation(summary = "Проверка доступности датчика")
    public String createSensor(@RequestBody SensorCreateDto createDto) {
        return managerService.createSensor(createDto);
    }
}
