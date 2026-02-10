package ru.medweather.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.medweather.manager.model.SensorsType;

public interface SensorTypeRepository extends JpaRepository<SensorsType, Short> {
    SensorsType findByCode(String code);

    boolean existsByCode(String sensorCode);
}
