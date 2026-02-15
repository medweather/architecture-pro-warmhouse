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
    @PutMapping("/{id}/status/{statusId}")
    @Operation(summary = "Открыть/закрыть ворота")
    public String updateStatus(@PathVariable Integer id, @PathVariable Short statusId) {
        return automaticGatesService.updateStatus(id, statusId);
    }
}
