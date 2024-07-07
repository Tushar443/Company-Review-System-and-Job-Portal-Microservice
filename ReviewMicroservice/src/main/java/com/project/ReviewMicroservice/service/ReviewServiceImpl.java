package com.project.ReviewMicroservice.service;

import com.project.ReviewMicroservice.beans.Review;
import com.project.ReviewMicroservice.dto.request.ReviewReq;
import com.project.ReviewMicroservice.dto.response.ReviewRes;
import com.project.ReviewMicroservice.repository.ReviewRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements IReviewService{

    ReviewRepo reviewRepo;

    public ReviewServiceImpl(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    @Override
    public List<ReviewRes> findAll(long companyId) {
        List<Review> list = reviewRepo.findAllByCompanyId(companyId);
        List<ReviewRes> reviewResList = new ArrayList<>();
        for(Review review : list){
            ReviewRes reviewRes = new ReviewRes();
            BeanUtils.copyProperties(review,reviewRes);
            reviewResList.add(reviewRes);
        }
        return reviewResList;
    }

    @Override
    public ReviewRes getReviewById(long reviewId) {
        Optional<Review> optionalReview = reviewRepo.findById(reviewId);
        ReviewRes reviewRes = new ReviewRes();
        if(optionalReview.isPresent()){
            Review review = optionalReview.get();
            BeanUtils.copyProperties(review,reviewRes);
            return reviewRes;
        }
        return reviewRes;
    }

    @Override
    public void addReview(long companyId, List<ReviewReq> reviewReqList) {
        for(ReviewReq reviewReq : reviewReqList){
            Review review = new Review();
            BeanUtils.copyProperties(reviewReq,review);
            review.setCompanyId(companyId);
            reviewRepo.save(review);
        }
    }

    @Override
    public boolean updateReview(long reviewId, ReviewReq updateReviewReq) {
        Optional<Review> optionalReview = reviewRepo.findById(reviewId);
        ReviewRes reviewRes = new ReviewRes();
        if(optionalReview.isPresent()){
            Review review = optionalReview.get();
            BeanUtils.copyProperties(updateReviewReq,review);
            reviewRepo.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(long reviewId) {
        Optional<Review> optionalReview = reviewRepo.findById(reviewId);
        if(optionalReview.isPresent()){
            Review review = optionalReview.get();
            reviewRepo.delete(review);
            return true;
        }
        return false;
    }
}
