package ru.medweather.homemonitoring.external;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.medweather.homemonitoring.config.KafkaTopicProperties;
import ru.medweather.homemonitoring.model.HomeMonitoringData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class HomeMonitoringClient {

    private final KafkaTopicProperties kafkaTopicProperties;
    private final KafkaTemplate<String, HomeMonitoringData> kafkaTemplate;
    protected static final List<Integer> runningDeviceIds = new ArrayList<>();
    private final Random random = new Random();
    private final List<String> streamingValues =  List.of(
            "Кто-то вошел в дом",
            "Всё спокойно",
            "Кто-то вышел из дома",
            "Кто-то находится во дворе дома",
            "В доме что-то дымит",
            "В доме пожар",
            "Освещение не работает"
    );

    public void turnOn(Integer id) {
        runningDeviceIds.add(id);
        log.info("Датчик наблюдения за домом включен: sensorId = {}", id);
    }

    public void turnOff(Integer id) {
        runningDeviceIds.remove(id);
        log.info("Датчик наблюдения за домом выключен: sensorId = {}", id);
    }

    @Scheduled(fixedRate = 5000)
    public void streamingData() {
        if (runningDeviceIds.isEmpty()) {
            log.info("Все датчики наблюдения за домом отключены");
        }
        runningDeviceIds.forEach(id -> kafkaTemplate.send(
                kafkaTopicProperties.getHomeMonitoringDataTopic().getName(),
                new HomeMonitoringData(
                        id,
                        streamingValues.get(random.nextInt(streamingValues.size()-1)),
                        LocalDateTime.now()
                )
        ));
    }
}
