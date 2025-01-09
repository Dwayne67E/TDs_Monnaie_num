package com.example.event_indexer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class EventIndexerApplication {

	public static void main(String[] args) {
		System.out.println("L'application démarre...");
		SpringApplication.run(EventIndexerApplication.class, args);
		System.out.println("L'application a démarré avec succès !");
	}

}
