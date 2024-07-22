package com.project.CompanyMicroservice.messaging;


import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RabbitMQConfig {

    private final ConfigRabbitMQAdmin configRabbitMQAdmin;

    @Value("${rabbitmq.queue.reviewName}")
    private String queueReviewName;

    @Value("${rabbitmq.queue.jobName}")
    private String queueJobName;

    public RabbitMQConfig(ConfigRabbitMQAdmin configRabbitMQAdmin) {
        this.configRabbitMQAdmin = configRabbitMQAdmin;
    }

    @PostConstruct
    public void createQueue(){
        configRabbitMQAdmin.admin().declareQueue(new Queue(queueReviewName));
        configRabbitMQAdmin.admin().declareQueue(new Queue(queueJobName));
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
