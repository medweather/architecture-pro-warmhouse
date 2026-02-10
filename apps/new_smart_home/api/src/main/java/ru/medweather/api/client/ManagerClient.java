package ru.medweather.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "manager-service")
public interface ManagerClient {

    @GetMapping("/sensors/check/{sensorCode}")
    Boolean checkSensor(@PathVariable String sensorCode);
}
