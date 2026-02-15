package ru.medweather.homemonitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HomeMonitoringServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeMonitoringServiceApplication.class, args);
	}
}
