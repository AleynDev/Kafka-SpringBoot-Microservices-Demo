package com.aleyn.consumer.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * Configuration class for setting up Kafka consumer listeners.
 */
@Slf4j
@Configuration
public class KafkaConsumerListener {

    /**
     * Kafka listener method that subscribes to the "myFirstTopic" topic and consumes messages.
     *
     * @param message The received Kafka message.
     */
    @KafkaListener(topics = {"myFirstTopic"}, groupId = "my-group-id")
    public void listener(String message) {
        log.info("Message received: {}", message);
    }

}