package com.project.CompanyMicroservice.messaging;

import com.project.CompanyMicroservice.dto.RabbitMQ.ReviewMessage;
import com.project.CompanyMicroservice.service.CompanyServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageConsumer {

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    private final CompanyServiceImpl companyService;


    public ReviewMessageConsumer(CompanyServiceImpl companyService) {
        this.companyService = companyService;
    }

    @RabbitListener(queues ="${rabbitmq.queue.name}")
    public void consumeMessage(ReviewMessage reviewMessage){
        companyService.updateCompanyRating(reviewMessage);
    }
}
