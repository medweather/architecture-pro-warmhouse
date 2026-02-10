package ru.medweather.payment.service;

import ru.medweather.payment.model.dto.request.BuySensorRequest;

public interface PaymentService {
    void buySensor(BuySensorRequest request);
}
