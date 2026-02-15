package ru.medweather.payment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.medweather.payment.model.dto.SensorCreateDto;

@FeignClient(value = "manager-service")
public interface ManagerClient {

    @GetMapping("/sensors/check-for-buy/{sensorCode}")
    Boolean checkSensorForBuy(@PathVariable String sensorCode);

    @PostMapping("/sensors/create")
    String createSensor(@RequestBody SensorCreateDto createDto);
}
