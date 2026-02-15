package ru.medweather.light.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LightSystemClient {

    public void turnOn(Integer id) {
        log.info("Датчик света включен: sensorId = {}", id);
    }

    public void turnOff(Integer id) {
        log.info("Датчик света выключен: sensorId = {}", id);
    }
}
