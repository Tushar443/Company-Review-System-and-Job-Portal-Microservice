package com.project.ReviewMicroservice.service;

import com.project.ReviewMicroservice.dao.request.ReviewReq;
import com.project.ReviewMicroservice.dao.response.ReviewRes;

import java.util.List;

public interface IReviewService {
    List<ReviewRes> findAll(long companyId);

    ReviewRes getReviewById(long reviewId, long companyId);

    void addReview(long companyId, List<ReviewReq> reviewReq);

    boolean updateReview(long reviewId, long companyId, ReviewReq updateReviewReq);

    boolean deleteReview(long reviewId, long companyId);
}
