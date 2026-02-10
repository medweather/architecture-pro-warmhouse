package ru.medweather.automaticgates.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.medweather.automaticgates.config.ApiErrorRes;
import ru.medweather.automaticgates.service.AutomaticGatesService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sensors")
public class AutomaticGatesController {

    private final AutomaticGatesService automaticGatesService;

    @ApiErrorRes
    @PutMapping("/turn-on/{id}")
    @Operation(summary = "Открыть ворота")
    public String turnOn(@PathVariable Integer id) {
        automaticGatesService.turnOn(id);
        return "Ворота открылись! sensorId = %d".formatted(id);
    }

    @ApiErrorRes
    @PutMapping("/turn-off/{id}")
    @Operation(summary = "Закрыть ворота")
    public String turnOff(@PathVariable Integer id) {
        automaticGatesService.turnOff(id);
        return "Ворота закрылись! sensorId = %d".formatted(id);
    }
}
