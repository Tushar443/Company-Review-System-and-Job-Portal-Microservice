package com.project.JobMicroservice.client;

import com.project.JobMicroservice.dto.request.CompanyReq;
import com.project.JobMicroservice.dto.response.CompanyRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "COMPANYMICROSERVICE" , url = "${company-service.url}")
public interface CompanyClient {

    @GetMapping("/company/{id}")
    CompanyRes getCompanyById(@PathVariable("id") Long id);

    @PutMapping("/company/updateJobs")
    boolean updateCompanyJobId(@RequestParam long companyId, @RequestParam long jobId,@RequestParam boolean isAdd);
}
