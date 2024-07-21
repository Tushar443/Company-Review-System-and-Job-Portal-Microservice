package com.project.CompanyMicroservice.service;

import com.project.CompanyMicroservice.dto.RabbitMQ.ReviewMessage;
import com.project.CompanyMicroservice.dto.request.CompanyReq;
import com.project.CompanyMicroservice.dto.response.CompanyRes;

import java.util.List;

public interface ICompanyService {
    List<CompanyRes> findAll();

    boolean addCompany(CompanyReq companyReqs);

    CompanyRes getCompanyById(long id);

    CompanyRes getCompanyByIdForReviews(long id);

    boolean updateCompany(long id,CompanyReq updated);

    boolean deleteCompany(long id);

    boolean updateCompanyJobId(long companyId, long jobId,boolean isAdd);

    boolean updateCompanyReviewId(long companyId, long reviewId, boolean isAdd);

    void updateCompanyRating(ReviewMessage reviewMessage);
}
