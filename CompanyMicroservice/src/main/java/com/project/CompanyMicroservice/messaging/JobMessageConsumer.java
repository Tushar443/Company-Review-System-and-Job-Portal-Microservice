package com.project.CompanyMicroservice.messaging;

import com.project.CompanyMicroservice.dto.RabbitMQ.JobMessage;
import com.project.CompanyMicroservice.dto.RabbitMQ.ReviewMessage;
import com.project.CompanyMicroservice.service.CompanyServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JobMessageConsumer {

    private final CompanyServiceImpl companyService;


    public JobMessageConsumer(CompanyServiceImpl companyService) {
        this.companyService = companyService;
    }

    @RabbitListener(queues ="${rabbitmq.queue.jobName}")
    public void consumeJobMessage(JobMessage jobMessage){
        if (Operations.ADD.equals(jobMessage.getOperation())){
            companyService.updateJobByCompanyId(jobMessage);
        }else if (Operations.DELETE.equals(jobMessage.getOperation())){
            companyService.deleteJobId(jobMessage);
        }
    }
}
