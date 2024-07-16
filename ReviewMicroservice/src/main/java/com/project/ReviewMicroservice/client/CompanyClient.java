package com.project.ReviewMicroservice.client;

import com.project.ReviewMicroservice.dto.response.CompanyRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "COMPANYMICROSERVICE")
public interface CompanyClient {

    @GetMapping("/company/{id}")
    CompanyRes getCompanyById(@PathVariable("id") Long id);

    @PutMapping("/company/updateReviews")
    boolean updateCompanyReviewId(@RequestParam long companyId, @RequestParam long reviewId,@RequestParam boolean isAdd);
}
