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
    @PutMapping("/{id}/status/{statusId}")
    @Operation(summary = "Включить/выключить отопление")
    public String updateStatus(@PathVariable Integer id, @PathVariable Short statusId) {
        return heatingService.updateStatus(id, statusId);
    }
}
