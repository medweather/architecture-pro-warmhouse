package ru.medweather.payment.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.medweather.payment.config.ApiErrorRes;
import ru.medweather.payment.model.dto.request.BuySensorRequest;
import ru.medweather.payment.service.PaymentService;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @ApiErrorRes
    @PostMapping("/sensors")
    @Operation(summary = "Покупка устройства")
    public String buySensor(@RequestBody BuySensorRequest request) {
        paymentService.buySensor(request);
        return "Устройство приобретено и подключено: код = %s, локация = %s".formatted(request.sensorCode(), request.location());
    }
}
