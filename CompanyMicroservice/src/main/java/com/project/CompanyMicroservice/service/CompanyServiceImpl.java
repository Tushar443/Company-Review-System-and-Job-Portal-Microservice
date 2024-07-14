package com.project.CompanyMicroservice.service;

import com.project.CompanyMicroservice.beans.Company;
import com.project.CompanyMicroservice.client.JobClient;
import com.project.CompanyMicroservice.client.ReviewClient;
import com.project.CompanyMicroservice.dto.request.CompanyReq;
import com.project.CompanyMicroservice.dto.request.ReviewReq;
import com.project.CompanyMicroservice.dto.response.CompanyRes;
import com.project.CompanyMicroservice.repository.CompanyRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements ICompanyService {

    CompanyRepo companyRepo;

    JobClient jobClient;

    ReviewClient reviewClient;

    public CompanyServiceImpl(CompanyRepo companyRepo, JobClient jobClient, ReviewClient reviewClient) {
        this.companyRepo = companyRepo;
        this.jobClient = jobClient;
        this.reviewClient = reviewClient;
    }

    @Override
    public List<CompanyRes> findAll() {
        List<Company> companyList = companyRepo.findAll();
        List<CompanyRes> companyResList = new ArrayList<>();
        for (Company company : companyList) {
            CompanyRes companyRes = new CompanyRes();
            BeanUtils.copyProperties(company, companyRes);
            companyResList.add(companyRes);
        }
        return companyResList;
    }

    @Override
    public boolean addCompany(CompanyReq companyReq) {
        boolean flag;
        Company dbCompany = companyRepo.findCompanyByName(companyReq.getName());
        if (dbCompany == null) {
            Company company = new Company();
            BeanUtils.copyProperties(companyReq, company);
            Company saveCompany = companyRepo.save(company);
            // call job microservice
            companyReq.getJobs().forEach(jobReq -> {
                jobReq.setCompanyId(saveCompany.getId());
                jobClient.addJob(jobReq);
            });
            // call review microservice
            companyReq.getReviews().forEach(reviewReq -> reviewClient.addReview(saveCompany.getId(), reviewReq));
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    @Override
    public CompanyRes getCompanyById(long id) {
        Optional<Company> company = companyRepo.findById(id);
        CompanyRes companyRes = new CompanyRes();
        if (company.isPresent()) {
            Company entity = company.get();
            BeanUtils.copyProperties(entity, companyRes);
        }
        return companyRes;
    }

    @Override
    public boolean updateCompany(long id, CompanyReq updated) {
        Optional<Company> opCompany = companyRepo.findById(id);
        if (opCompany.isPresent()) {
            Company company = opCompany.get();
            BeanUtils.copyProperties(updated, company);
            companyRepo.save(company);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCompany(long id) {
        Optional<Company> opCompany = companyRepo.findById(id);
        if (opCompany.isPresent()) {
            Company company = opCompany.get();
            companyRepo.delete(company);
            return true;
        }
        return false;
    }
}
