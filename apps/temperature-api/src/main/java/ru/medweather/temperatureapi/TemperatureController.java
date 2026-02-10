package ru.medweather.temperatureapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/temperature")
public class TemperatureController {

    @GetMapping
    public String getTemperature(@RequestParam("location") String location) {
        return "Температура дома '%s': %d градусов по Цельсию"
                .formatted(location, new Random().ints(10,30).findFirst().getAsInt());
    }
}
