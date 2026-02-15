package ru.medweather.payment.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.medweather.payment.client.ManagerClient;
import ru.medweather.payment.exception.NotFoundException;
import ru.medweather.payment.external.BankSystemClient;
import ru.medweather.payment.model.dto.SensorCreateDto;
import ru.medweather.payment.model.dto.request.BuySensorRequest;
import ru.medweather.payment.service.PaymentService;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final BankSystemClient bankSystemClient;
    private final ManagerClient managerClient;

    @Override
    public void buySensor(BuySensorRequest request) {
        if (managerClient.checkSensorForBuy(request.sensorCode())) {
            bankSystemClient.buySensor(request);
            managerClient.createSensor(SensorCreateDto.from(request));
        } else throw new NotFoundException("Устройство не доступно для покупки!");
    }
}
