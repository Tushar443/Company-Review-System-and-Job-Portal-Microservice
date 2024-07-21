package com.project.ReviewMicroservice.controller;

import com.project.ReviewMicroservice.dto.request.ReviewReq;
import com.project.ReviewMicroservice.dto.response.ReviewRes;
import com.project.ReviewMicroservice.messaging.ReviewMessageProducer;
import com.project.ReviewMicroservice.service.IReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("reviews")
public class ReviewController {

    private final IReviewService reviewService;

    public ReviewController(IReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<ReviewRes>> getAllReviews(@RequestParam long companyId){
        List<ReviewRes> reviewRes = reviewService.findAll(companyId);
        if(reviewRes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reviewRes,HttpStatus.OK);
    }
    //get company By Id
    @GetMapping("/{reviewId}")
    public ReviewRes getReviewById(@PathVariable long reviewId){
        ReviewRes reviewRes = reviewService.getReviewById(reviewId);
        if(reviewRes != null){
            return  reviewRes;
        }
        return null;
    }
    //add
    @PostMapping
    public ResponseEntity<Long> addReview(@RequestParam long companyId,@RequestBody ReviewReq reviewReq){
        Long reviewId = reviewService.addReview(companyId,reviewReq);
        if(reviewId != null){
            return new ResponseEntity<>(reviewId,HttpStatus.OK);
        }
        return new ResponseEntity<>(reviewId,HttpStatus.OK);
    }
    //update
    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable long reviewId ,@RequestBody ReviewReq updateReviewReq){
        boolean update = reviewService.updateReview(reviewId,updateReviewReq);
        if(update){
            return new ResponseEntity<>("Update Company Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }
    //delete
    @DeleteMapping("/{reviewId}")
    public boolean deleteReviewById(@PathVariable long reviewId ){
        return reviewService.deleteReview(reviewId);
    }

    @GetMapping("/getAverageRating")
    public Double getAverageRating(@RequestParam Long companyId){
        List<ReviewRes> reviewResList = reviewService.findAll(companyId);
        return reviewResList.stream().mapToDouble(ReviewRes::getRating).average().orElse(0.0);
    }


}
