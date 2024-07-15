package com.project.JobMicroservice.client;

import com.project.JobMicroservice.dto.request.CompanyReq;
import com.project.JobMicroservice.dto.response.CompanyRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "COMPANYMICROSERVICE")
public interface CompanyClient {

    @GetMapping("/company/{id}")
    CompanyRes getCompanyById(@PathVariable("id") Long id);

    @PutMapping("/company/{id}")
    void updateCompany(@PathVariable long id ,@RequestBody CompanyReq companyReq);
}
