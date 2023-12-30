package com.aleyn.provider.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class for Kafka producer settings.
 */
@Configuration
public class KafkaProviderConfig {

    /**
     * The bootstrap servers for connecting to the Kafka cluster.
     */
    @Value("${spring.kafka.bootstrapServers}")
    private String bootstrapServers;

    /**
     * Configures the properties for the Kafka producer.
     *
     * @return A map of properties for configuring the Kafka producer.
     */
    public Map<String, Object> producerConfig() {
        Map<String, Object> properties = new HashMap<>();

        // Configure the bootstrap servers property for the producer to connect to the Kafka cluster.
        // The 'bootstrapServers' variable should contain the list of addresses and ports of Kafka servers.
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        // Configure the key serializer for the producer.
        // In this case, the StringSerializer is used to convert keys to a String format.
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // Configure the value serializer for the producer.
        // In this case, the StringSerializer is used to convert values to a String format.
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return properties;
     }

    /**
     * Creates a Kafka producer factory using the configured properties.
     *
     * @return The Kafka producer factory.
     */
     @Bean
     public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
     }

    /**
     * Creates a Kafka template using the provided producer factory.
     *
     * @param producerFactory The Kafka producer factory.
     * @return The Kafka template.
     */
     @Bean
     public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
     }

}