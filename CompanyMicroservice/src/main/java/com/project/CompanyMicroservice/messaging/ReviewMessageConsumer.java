package com.project.CompanyMicroservice.messaging;

import com.project.CompanyMicroservice.dto.RabbitMQ.ReviewMessage;
import com.project.CompanyMicroservice.service.CompanyServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageConsumer {
    private final CompanyServiceImpl companyService;


    public ReviewMessageConsumer(CompanyServiceImpl companyService) {
        this.companyService = companyService;
    }

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage){
        companyService.updateCompanyRating(reviewMessage);
    }
}
