package ru.medweather.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.medweather.manager.model.Sensor;

public interface SensorsRepository extends JpaRepository<Sensor, Integer> {
    boolean existsByType_Code(String sensorCode);
}
