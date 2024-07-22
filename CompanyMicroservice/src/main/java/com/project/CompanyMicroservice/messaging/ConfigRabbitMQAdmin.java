package com.project.CompanyMicroservice.messaging;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigRabbitMQAdmin {

    private final ConnectionFactory connectionFactory;

    public ConfigRabbitMQAdmin(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Bean
    public RabbitAdmin admin(){
        return new RabbitAdmin(connectionFactory);
    }

}
