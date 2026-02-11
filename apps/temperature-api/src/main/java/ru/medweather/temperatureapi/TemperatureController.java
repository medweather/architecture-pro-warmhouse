package ru.medweather.temperatureapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/temperature")
public class TemperatureController {

    @GetMapping("/{location}")
    public TemperatureData getTemperature(@PathVariable("location") String location) {
        return new TemperatureData(
                location,
                new Random().ints(10,30).findFirst().getAsInt(),
                "C",
                "active",
                "какое-то описание"
        );
    }

    record TemperatureData(
            String location,
            Integer value,
            String unit,
            String status,
            String description
    ) {

    }
}
