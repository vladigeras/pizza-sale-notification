package ru.vladigeras.masterpizzasale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MasterpizzaSaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasterpizzaSaleApplication.class, args);
	}
}
