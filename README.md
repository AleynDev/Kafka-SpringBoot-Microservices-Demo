# Microservices with Spring Boot and Apache Kafka

This project demonstrates a basic setup of microservices using Spring Boot and Apache Kafka for communication between services.

## Project Structure

- `SpringBootForKafka`: Parent project containing the microservices.
    - `SpringBootConsumer`: Microservice for consuming messages from Kafka.
    - `SpringBootProvider`: Microservice for producing messages to Kafka.

## Prerequisites

Before you begin, ensure you have the following installed:

- **Java 17**: Make sure you have Java 17 installed on your system. You can download it from the [official website](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).
- **Spring Boot Knowledge**: Familiarize yourself with Spring Boot. If you're new to Spring Boot, you can explore the [official documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html).
- **Docker**: Install Docker on your machine to run Apache Kafka and Zookeeper easily. You can find Docker installation instructions [here](https://docs.docker.com/get-docker/).


1. **Clone the Repository**

    ```bash
    git clone https://github.com/AleynDev/Kafka-SpringBoot-Microservices-Demo.git
    ```

2. **Start Kafka and Zookeeper using Docker**

   Navigate to the project directory:

    ```bash
    cd SpringBootForKafka
    ```

   Run Docker Compose to start Kafka and Zookeeper containers:

    ```bash
    docker-compose up -d
    ```

   Obtain the IP address of the Kafka container. Run the following command:

    ```bash
    docker network inspect bridge | grep Gateway
    ```

   Locate the IP address associated with the Kafka container. Update the `application.properties` file in both `SpringBootConsumer` and `SpringBootProvider` with the obtained IP address.


3. **Run the Microservices**

   **SpringBootConsumer**

   Navigate to the `SpringBootConsumer` directory:

    ```bash
    cd SpringBootConsumer
    ```

   Build and run the consumer:

    ```bash
    ./mvnw clean install
    ./mvnw spring-boot:run
    ```

   **SpringBootProvider**

   Navigate to the `SpringBootProvider` directory:

    ```bash
    cd SpringBootProvider
    ```

   Build and run the provider:

    ```bash
    ./mvnw clean install
    ./mvnw spring-boot:run
    ```

Now, your microservices are up and running, and Kafka is ready for communication between them.

If you check the logs of `SpringBootConsumer`, you should see the message "Hello World with Kafka from Spring Boot".

This message is sent when `SpringBootProviderApplication` starts, triggered by the `CommandLineRunner` initialization.

## Credits

This project is based on the following YouTube video:

- **Video Title:** [Spring Boot con Apache Kafka - Guía completa](https://www.youtube.com/watch?v=UbbyW5Z1lv8&t=1411s)
- **Creator:** [@unprogramadornace](https://www.youtube.com/@unprogramadornace)
