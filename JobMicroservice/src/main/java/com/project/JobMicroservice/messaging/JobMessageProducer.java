package com.project.JobMicroservice.messaging;

import com.project.JobMicroservice.beans.Job;
import com.project.JobMicroservice.dto.RabbitMQ.JobMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JobMessageProducer {

    @Value("${rabbitmq.queue.jobName}")
    private String queueName;

    @Value("${rabbitmq.exchange.jobName}")
    private String exchangeName;

    @Value("${rabbitmq.routing.jobKey}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public JobMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void addJobToCompany(Job job){
        JobMessage jobMessage = new JobMessage();
        jobMessage.setOperation(Operations.ADD);
        BeanUtils.copyProperties(job,jobMessage);
        rabbitTemplate.convertAndSend(exchangeName,routingKey,jobMessage);
    }

    public void deleteJobFromCompany(Job job) {
        JobMessage jobMessage = new JobMessage();
        jobMessage.setOperation(Operations.DELETE);
        BeanUtils.copyProperties(job,jobMessage);
        rabbitTemplate.convertAndSend(exchangeName,routingKey,jobMessage);
    }
}
