package com.rabbitmq.demo.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ConfigurationProperties("app.consumer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertiesConfig {
    private String studentQueue;
    private String exchangeName;
    private String routingKey;
    private String rabbitMQListenConcurrency;
    private int rabbitMQPrefetch;
    private int sucessStatusCode;
    private int failuedtatusCode;
    private String sucessStatusMessage;
    private String failedStatusMessage;
}
