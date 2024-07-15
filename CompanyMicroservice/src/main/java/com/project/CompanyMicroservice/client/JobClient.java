package com.project.CompanyMicroservice.client;

import com.project.CompanyMicroservice.dto.request.JobReq;
import com.project.CompanyMicroservice.dto.response.JobRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "JOBMICROSERVICE")
public interface JobClient {
    @PostMapping("/jobs/")
    ResponseEntity<Long> addJob(@RequestBody JobReq jobReq);

    @GetMapping("/jobs/{id}")
    ResponseEntity<JobRes> getJobById(@PathVariable Long id);

    @DeleteMapping("/jobs/{id}")
    boolean deleteJobById(@PathVariable long id );

}
