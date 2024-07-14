package com.project.ReviewMicroservice.service;

import com.project.ReviewMicroservice.dto.request.ReviewReq;
import com.project.ReviewMicroservice.dto.response.ReviewRes;

import java.util.List;

public interface IReviewService {
    List<ReviewRes> findAll(long companyId);

    ReviewRes getReviewById(long reviewId);

    void addReview(long companyId, ReviewReq reviewReq);

    boolean updateReview(long reviewId, ReviewReq updateReviewReq);

    boolean deleteReview(long reviewId);
}
