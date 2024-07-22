package com.project.CompanyMicroservice.messaging;

import com.project.CompanyMicroservice.dto.RabbitMQ.ReviewMessage;
import com.project.CompanyMicroservice.service.CompanyServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageConsumer {

    private final CompanyServiceImpl companyService;


    public ReviewMessageConsumer(CompanyServiceImpl companyService) {
        this.companyService = companyService;
    }

    @RabbitListener(queues ="${rabbitmq.queue.reviewName}")
    public void consumeReviewMessage(ReviewMessage reviewMessage){
        if (Operations.ADD.equals(reviewMessage.getOperation())){
            companyService.updateCompanyRating(reviewMessage);
        }else if (Operations.DELETE.equals(reviewMessage.getOperation())){
            companyService.deleteReviewId(reviewMessage);
        }
    }
}
