package ru.medweather.manager.service;

import ru.medweather.manager.model.dto.SensorCreateDto;
import ru.medweather.manager.model.dto.SensorDto;
import ru.medweather.manager.model.dto.SensorTypeDto;
import ru.medweather.manager.model.dto.UpdateStatusDto;

import java.util.List;

public interface ManagerService {
    List<SensorTypeDto> sensorTypes();

    List<SensorDto> availableSensors();

    boolean checkSensor(String sensorCode);

    boolean checkSensorForBuy(String sensorCode);

    void updateSensorStatus(UpdateStatusDto updateStatusDto);

    String createSensor(SensorCreateDto createDto);
}
