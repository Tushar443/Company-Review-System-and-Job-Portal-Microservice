package com.project.JobMicroservice.client;

import com.project.JobMicroservice.dto.response.ReviewRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "REVIEWMICROSERVICE" ,url = "${review-service.url}")
public interface ReviewClient {
    @GetMapping("/reviews/")
    ResponseEntity<List<ReviewRes>> getAllReviews(@RequestParam long companyId);
}
