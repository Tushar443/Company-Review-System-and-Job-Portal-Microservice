package com.project.ReviewMicroservice.service;

import com.project.ReviewMicroservice.beans.Review;
import com.project.ReviewMicroservice.client.CompanyClient;
import com.project.ReviewMicroservice.dto.request.ReviewReq;
import com.project.ReviewMicroservice.dto.response.CompanyRes;
import com.project.ReviewMicroservice.dto.response.ReviewRes;
import com.project.ReviewMicroservice.messaging.ReviewMessageProducer;
import com.project.ReviewMicroservice.repository.ReviewRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements IReviewService {

    ReviewRepo reviewRepo;

    CompanyClient companyClient;


    private final ReviewMessageProducer reviewMessageProducer;

    public ReviewServiceImpl(ReviewRepo reviewRepo, CompanyClient companyClient, ReviewMessageProducer reviewMessageProducer) {
        this.reviewRepo = reviewRepo;
        this.companyClient = companyClient;
        this.reviewMessageProducer = reviewMessageProducer;
    }

    @Override
    public List<ReviewRes> findAll(long companyId) {
        List<Review> list = reviewRepo.findAllByCompanyId(companyId);
        List<ReviewRes> reviewResList = new ArrayList<>();
        for (Review review : list) {
            ReviewRes reviewRes = new ReviewRes();
            BeanUtils.copyProperties(review, reviewRes);
            reviewResList.add(reviewRes);
        }
        return reviewResList;
    }

    @Override
    public ReviewRes getReviewById(long reviewId) {
        Optional<Review> optionalReview = reviewRepo.findById(reviewId);
        ReviewRes reviewRes = new ReviewRes();
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            CompanyRes companyRes = companyClient.getCompanyByIdForReviews(review.getCompanyId());
            BeanUtils.copyProperties(review, reviewRes);
            reviewRes.setCompanyRes(companyRes);
            return reviewRes;
        }
        return reviewRes;
    }

    @Override
    public Long addReview(long companyId, ReviewReq reviewReq) {
            Review review = new Review();
            BeanUtils.copyProperties(reviewReq, review);
            review.setCompanyId(companyId);
            Review save = reviewRepo.save(review);
            reviewMessageProducer.addReviewToCompany(save);
            return save.getId();
    }

    @Override
    public boolean updateReview(long reviewId, ReviewReq updateReviewReq) {
        Optional<Review> optionalReview = reviewRepo.findById(reviewId);
        ReviewRes reviewRes = new ReviewRes();
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            BeanUtils.copyProperties(updateReviewReq, review);
            reviewRepo.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(long reviewId) {
        Optional<Review> optionalReview = reviewRepo.findById(reviewId);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            reviewMessageProducer.deleteReviewFromCompany(review);
            reviewRepo.delete(review);
            return true;
        }
        return false;
    }
}
