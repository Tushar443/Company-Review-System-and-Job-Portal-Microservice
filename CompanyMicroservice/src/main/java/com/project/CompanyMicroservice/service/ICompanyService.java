package com.project.CompanyMicroservice.service;

import com.project.CompanyMicroservice.dto.request.CompanyReq;
import com.project.CompanyMicroservice.dto.response.CompanyRes;

import java.util.List;

public interface ICompanyService {
    List<CompanyRes> findAll();

    boolean addCompany(CompanyReq companyReqs);

    CompanyRes getCompanyById(long id);

    boolean updateCompany(long id,CompanyReq updated);

    boolean deleteCompany(long id);

    boolean updateCompanyJobId(long companyId, long jobId);

    boolean updateCompanyReviewId(long companyId, long reviewId);
}
