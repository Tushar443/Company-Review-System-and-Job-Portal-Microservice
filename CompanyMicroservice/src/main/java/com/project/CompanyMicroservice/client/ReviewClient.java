package com.project.CompanyMicroservice.client;

import com.project.CompanyMicroservice.dto.request.ReviewReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "REVIEWMICROSERVICE")
public interface ReviewClient {
    @PostMapping("/reviews/")
    public ResponseEntity<String> addReview(@RequestParam long companyId, @RequestBody ReviewReq reviewReq);
}
