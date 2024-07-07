package com.project.ReviewMicroservice.controller;

import com.project.ReviewMicroservice.dto.request.ReviewReq;
import com.project.ReviewMicroservice.dto.response.ReviewRes;
import com.project.ReviewMicroservice.service.IReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/reviews")
public class ReviewController {

    IReviewService reviewService;

    public ReviewController(IReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<ReviewRes>> getAllReviews(@PathVariable long companyId){
        List<ReviewRes> reviewRes = reviewService.findAll(companyId);
        if(reviewRes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reviewRes,HttpStatus.OK);
    }
    //get company By Id
    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewRes> getReviewByIdAndCompanyId(@PathVariable long reviewId ,@PathVariable long companyId){
        ReviewRes reviewRes = reviewService.getReviewById(reviewId,companyId);
        if(reviewRes != null){
            return  new ResponseEntity<>(reviewRes,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //add
    @PostMapping
    public ResponseEntity<String> addReview(@PathVariable long companyId,@RequestBody List<ReviewReq> reviewReq){
        reviewService.addReview(companyId,reviewReq);
        return new ResponseEntity<>("Company Added",HttpStatus.OK);
    }
    //update
    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable long reviewId ,@PathVariable long companyId ,@RequestBody ReviewReq updateReviewReq){
        boolean update = reviewService.updateReview(reviewId,companyId,updateReviewReq);
        if(update){
            return new ResponseEntity<>("Update Company Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }
    //delete
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable long reviewId ,@PathVariable long companyId){
        boolean deleted = reviewService.deleteReview(reviewId,companyId);
        if(deleted){
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }
}
