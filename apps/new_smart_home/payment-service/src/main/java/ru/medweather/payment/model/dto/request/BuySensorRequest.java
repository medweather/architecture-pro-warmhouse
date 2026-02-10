package ru.medweather.payment.model.dto.request;

import ru.medweather.payment.model.PaymentCurrency;

public record BuySensorRequest(
        String name,
        String sensorCode,
        String location,
        Double value,
        String unit,
        Double money,
        PaymentCurrency currency
) {
}
