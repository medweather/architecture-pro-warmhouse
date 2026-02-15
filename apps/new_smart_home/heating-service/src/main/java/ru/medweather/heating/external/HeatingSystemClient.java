package ru.medweather.heating.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HeatingSystemClient {

    public void turnOn(Integer id) {
        log.info("Датчик отопления включен: sensorId = {}", id);
    }

    public void turnOff(Integer id) {
        log.info("Датчик отопления выключен: sensorId = {}", id);
    }
}
