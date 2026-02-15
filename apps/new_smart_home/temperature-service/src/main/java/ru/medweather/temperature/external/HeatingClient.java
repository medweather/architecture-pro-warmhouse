package ru.medweather.temperature.external;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.medweather.temperature.config.KafkaTopicProperties;
import ru.medweather.temperature.model.TemperatureData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class HeatingClient {

    private final KafkaTopicProperties kafkaTopicProperties;
    private final KafkaTemplate<String, TemperatureData> kafkaTemplate;
    protected static final List<Integer> runningDeviceIds = new ArrayList<>();
    private final Random random = new Random();

    public void turnOn(Integer id) {
        runningDeviceIds.add(id);
        log.info("Датчик мониторинга температуры включен: sensorId = {}", id);
    }

    public void turnOff(Integer id) {
        runningDeviceIds.remove(id);
        log.info("Датчик мониторинга температуры выключен: sensorId = {}", id);
    }

    @Scheduled(fixedRate = 5000)
    public void streamingData() {
        if (runningDeviceIds.isEmpty()) {
            log.info("Все датчики мониторинга температуры отключены");
        }
        runningDeviceIds.forEach(id -> kafkaTemplate.send(
                kafkaTopicProperties.getTemperatureDataTopic().getName(),
                new TemperatureData(
                        id,
                        random.ints(17,27).findFirst().getAsInt(),
                        LocalDateTime.now()
                )
        ));
    }
}
