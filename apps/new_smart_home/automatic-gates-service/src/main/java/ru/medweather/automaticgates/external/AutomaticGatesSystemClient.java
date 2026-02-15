package ru.medweather.automaticgates.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AutomaticGatesSystemClient {

    public void turnOn(Integer id) {
        log.info("Ворота открываются: sensorId = {}", id);
    }

    public void turnOff(Integer id) {
        log.info("Ворота закрываются: sensorId = {}", id);
    }
}
