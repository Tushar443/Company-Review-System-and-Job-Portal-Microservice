package com.project.CompanyMicroservice.service;

import com.project.CompanyMicroservice.beans.Company;
import com.project.CompanyMicroservice.dto.request.CompanyReq;
import com.project.CompanyMicroservice.dto.response.CompanyRes;
import com.project.CompanyMicroservice.repository.CompanyRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements ICompanyService{

    CompanyRepo companyRepo;

    public CompanyServiceImpl(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }


    @Override
    public List<CompanyRes> findAll() {
        List<Company> companyList = companyRepo.findAll();
        List<CompanyRes> companyResList = new ArrayList<>();
        for (Company company : companyList){
            CompanyRes companyRes = new CompanyRes();
            BeanUtils.copyProperties(company,companyRes);
            companyResList.add(companyRes);
        }
        return companyResList;
    }

    @Override
    public boolean addCompany(List<CompanyReq> companyReqList) {
        for(CompanyReq companyReq : companyReqList ){
            Company company = new Company();
            BeanUtils.copyProperties(companyReq,company);
            company.setReviewsId(companyReq.getReviewsId());
            company.setJobsId(companyReq.getJobsId());
            companyRepo.save(company);
        }
        return true;
    }

    @Override
    public CompanyRes getCompanyById(long id) {
        Optional<Company> company = companyRepo.findById(id);
        CompanyRes companyRes = new CompanyRes();
        if(company.isPresent()){
            Company entity=company.get();
            BeanUtils.copyProperties(entity,companyRes);
        }
        return companyRes;
    }

    @Override
    public boolean updateCompany(long id,CompanyReq updated) {
        Optional<Company> opCompany = companyRepo.findById(id);
        if(opCompany.isPresent()){
            Company company = opCompany.get();
            BeanUtils.copyProperties(updated,company);
            companyRepo.save(company);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCompany(long id) {
        Optional<Company> opCompany = companyRepo.findById(id);
        if(opCompany.isPresent()){
            Company company = opCompany.get();
            companyRepo.delete(company);
            return true;
        }
        return false;
    }
}
