package com.aleyn.provider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class for defining Kafka topics and their configurations.
 */
@Configuration
public class KafkaTopicConfig {

    /**
     * Bean method to generate and configure a Kafka topic.
     *
     * @return A NewTopic object representing the configured Kafka topic.
     */
    @Bean
    public NewTopic generateTopic() {

        // Map for topic configurations
        Map<String, String> configurations = new HashMap<>();

        // Cleanup policy options: "delete" (deletes the message) or "compact" (retains the latest message)
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);

        // Message retention time | Default: -1 (never delete) | 1 day in milliseconds = 86400000
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");

        // Maximum size of each segment within the topic | Default: 1 GB in bytes = 1073741824
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824");

        // Maximum size of each message | Default: 1 MB (1000012)
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012");

        // Building the topic with specified configurations
        return TopicBuilder
                .name("myFirstTopic")               // Topic name
                .partitions(2)     // Number of partitions
                .replicas(2)         // Number of replicas
                .configs(configurations)            // Additional configurations
                .build();
    }

}