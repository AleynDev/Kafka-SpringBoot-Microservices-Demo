package com.aleyn.consumer.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class for Kafka consumer settings.
 */
@Configuration
public class KafkaConsumerConfig {

    /**
     * The bootstrap servers for connecting to the Kafka cluster.
     */
    @Value("${spring.kafka.bootstrapServers}")
    private String bootstrapServers;

    /**
     * Configures the properties for the Kafka consumer.
     *
     * @return A map of properties for configuring the Kafka consumer.
     */
    public Map<String, Object> consumerConfig() {
        Map<String, Object> properties = new HashMap<>();

        // Configure the bootstrap servers property for the consumer to connect to the Kafka cluster.
        // The 'bootstrapServers' variable should contain the list of addresses and ports of Kafka servers.
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        // Configure the key deserializer for the consumer.
        // In this case, the StringSerializer is used to convert keys to a String format.
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // Configure the value deserializer for the consumer.
        // In this case, the StringSerializer is used to convert values to a String format.
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return properties;
    }

    /**
     * Creates a ConsumerFactory for Kafka consumers with the specified configurations.
     *
     * @return A ConsumerFactory for Kafka consumers.
     */
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    /**
     * Creates a KafkaListenerContainerFactory for concurrent Kafka message listeners using the provided ConsumerFactory.
     *
     * @param consumerFactory The ConsumerFactory for Kafka consumers.
     * @return A KafkaListenerContainerFactory for concurrent message listeners.
     */
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> consumer(
            ConsumerFactory<String, String> consumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

}