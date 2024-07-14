package com.project.CompanyMicroservice.client;

import com.project.CompanyMicroservice.dto.request.JobReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "JOBMICROSERVICE")
public interface JobClient {
    @PostMapping("/jobs/")
    ResponseEntity<Long> addJob(@RequestBody JobReq jobReq);
}
