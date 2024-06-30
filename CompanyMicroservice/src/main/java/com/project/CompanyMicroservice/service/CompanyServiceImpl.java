package com.project.CompanyMicroservice.service;

import com.project.CompanyMicroservice.repository.CompanyRepo;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements ICompanyService{

    CompanyRepo companyRepo;

    public CompanyServiceImpl(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }


}
