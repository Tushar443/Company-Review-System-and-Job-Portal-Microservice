package com.project.CompanyMicroservice.service;

import com.project.CompanyMicroservice.dao.request.CompanyReq;
import com.project.CompanyMicroservice.dao.response.CompanyRes;

import java.util.List;

public interface ICompanyService {
    List<CompanyRes> findAll();

    boolean addCompany(List<CompanyReq> companyReqs);

    CompanyRes getCompanyById(long id);

    boolean updateCompany(long id,CompanyReq updated);

    boolean deleteCompany(long id);
}
