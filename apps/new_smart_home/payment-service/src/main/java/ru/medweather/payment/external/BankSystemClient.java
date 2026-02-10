package ru.medweather.payment.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.medweather.payment.model.dto.request.BuySensorRequest;

@Slf4j
@Component
public class BankSystemClient {

    public void buySensor(BuySensorRequest request) {
        log.info("Датчик для умного дома куплен: money = {} {}", request.money(), request.currency());
    }
}
