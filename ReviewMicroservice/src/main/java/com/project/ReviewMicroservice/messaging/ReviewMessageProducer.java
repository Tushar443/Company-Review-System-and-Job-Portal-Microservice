package com.project.ReviewMicroservice.messaging;

import com.project.ReviewMicroservice.beans.Review;
import com.project.ReviewMicroservice.dto.RabbitMQ.ReviewMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageProducer {

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void addReviewToCompany(Review review){
        ReviewMessage reviewMessage = new ReviewMessage();
        reviewMessage.setOperation(Operations.ADD);
        BeanUtils.copyProperties(review,reviewMessage);
        rabbitTemplate.convertAndSend(exchangeName,routingKey,reviewMessage);
    }

    public void deleteReviewFromCompany(Review review) {
        ReviewMessage reviewMessage = new ReviewMessage();
        reviewMessage.setOperation(Operations.DELETE);
        BeanUtils.copyProperties(review,reviewMessage);
        rabbitTemplate.convertAndSend(exchangeName,routingKey,reviewMessage);
    }
}
