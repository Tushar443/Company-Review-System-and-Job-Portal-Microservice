package com.project.CompanyMicroservice.client;

import com.project.CompanyMicroservice.dto.request.ReviewReq;
import com.project.CompanyMicroservice.dto.response.ReviewRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "REVIEWMICROSERVICE")
public interface ReviewClient {
    @PostMapping("/reviews/")
    ResponseEntity<Long> addReview(@RequestParam long companyId, @RequestBody ReviewReq reviewReq);

    @GetMapping("/reviews/{reviewId}")
    ResponseEntity<ReviewRes> getReviewById(@PathVariable long reviewId);

    @DeleteMapping("/reviews/{reviewId}")
    boolean deleteReviewById(@PathVariable long reviewId);
}
