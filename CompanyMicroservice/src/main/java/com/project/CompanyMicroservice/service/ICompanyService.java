package com.project.CompanyMicroservice.service;

import com.project.CompanyMicroservice.dto.RabbitMQ.JobMessage;
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

    void updateCompanyRating(ReviewMessage reviewMessage);

    void deleteReviewId(ReviewMessage reviewMessage);

    void updateJobByCompanyId(JobMessage jobMessage);

    void deleteJobId(JobMessage jobMessage);
}
