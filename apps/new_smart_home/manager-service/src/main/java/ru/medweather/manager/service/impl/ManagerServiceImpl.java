package ru.medweather.manager.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.medweather.manager.exception.NotFoundException;
import ru.medweather.manager.model.Address;
import ru.medweather.manager.model.Sensor;
import ru.medweather.manager.model.SensorsStatus;
import ru.medweather.manager.model.SensorsType;
import ru.medweather.manager.model.dto.SensorCreateDto;
import ru.medweather.manager.model.dto.SensorDto;
import ru.medweather.manager.model.dto.SensorTypeDto;
import ru.medweather.manager.model.dto.UpdateStatusDto;
import ru.medweather.manager.repository.AddressRepository;
import ru.medweather.manager.repository.SensorTypeRepository;
import ru.medweather.manager.repository.SensorsRepository;
import ru.medweather.manager.service.ManagerService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final SensorsRepository sensorsRepository;
    private final SensorTypeRepository sensorTypeRepository;
    private final AddressRepository addressRepository;

    @Override
    public List<SensorTypeDto> sensorTypes() {
        return sensorTypeRepository.findAll().stream().map(SensorTypeDto::from).toList();
    }

    @Override
    public List<SensorDto> availableSensors() {
        return sensorsRepository.findAll().stream().map(SensorDto::from).toList();
    }

    @Override
    public boolean checkSensor(String sensorCode) {
        return sensorsRepository.existsByType_Code(sensorCode);
    }

    @Override
    public boolean checkSensorForBuy(String sensorCode) {
        return sensorTypeRepository.existsByCode(sensorCode);
    }

    @Override
    @Transactional
    public void updateSensorStatus(UpdateStatusDto updateStatusDto) {
        sensorsRepository.findById(updateStatusDto.sensorId()).ifPresentOrElse(sensor -> {
            SensorsStatus sensorsStatus = new SensorsStatus();
            sensorsStatus.setId(updateStatusDto.statusId());
            sensor.setStatus(sensorsStatus);
            sensorsRepository.save(sensor);
        }, () -> {
            throw new NotFoundException("Такое устройство не подключено!");
        });
    }

    @Override
    @Transactional
    public String createSensor(SensorCreateDto createDto) {
        SensorsType type = sensorTypeRepository.findByCode(createDto.code());
        if (type == null) { throw new NotFoundException("Неправильно выбранное устройство!"); }
        SensorsStatus activeStatus = new SensorsStatus();
        activeStatus.setId((short) 1);
        Address address = addressRepository.save(Address.builder().name(createDto.location()).build());
        Sensor saved = sensorsRepository.save(Sensor.builder()
                .type(type)
                .name(createDto.name())
                .value(createDto.value())
                .address(address)
                .unit(createDto.unit())
                .status(activeStatus)
                .build()
        );
        return "Устройство умного дома подключено: %d".formatted(saved.getId());
    }
}
