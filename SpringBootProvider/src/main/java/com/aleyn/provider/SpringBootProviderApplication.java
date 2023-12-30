package com.aleyn.provider;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class SpringBootProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootProviderApplication.class, args);
	}

	/**
	 * CommandLineRunner bean to be executed on application startup.
	 */
	@Bean
	CommandLineRunner init(KafkaTemplate<String, String> kafkaTemplate) {
		return args -> {
			// Sending a message to the "myFirstTopic" Kafka topic
			kafkaTemplate.send("myFirstTopic", "Hello World with Kafka from Spring Boot");
		};
	}

}
