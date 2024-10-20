package com.project.CompanyMicroservice.service;

import com.project.CompanyMicroservice.beans.Company;
import com.project.CompanyMicroservice.client.JobClient;
import com.project.CompanyMicroservice.client.ReviewClient;
import com.project.CompanyMicroservice.dto.RabbitMQ.JobMessage;
import com.project.CompanyMicroservice.dto.RabbitMQ.ReviewMessage;
import com.project.CompanyMicroservice.dto.request.CompanyReq;
import com.project.CompanyMicroservice.dto.request.JobReq;
import com.project.CompanyMicroservice.dto.request.ReviewReq;
import com.project.CompanyMicroservice.dto.response.CompanyRes;
import com.project.CompanyMicroservice.dto.response.JobRes;
import com.project.CompanyMicroservice.dto.response.ReviewRes;
import com.project.CompanyMicroservice.repository.CompanyRepo;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements ICompanyService , BeanPostProcessor {

    CompanyRepo companyRepo;

    JobClient jobClient;

    ReviewClient reviewClient;

    public CompanyServiceImpl(CompanyRepo companyRepo, JobClient jobClient, ReviewClient reviewClient) {
        System.out.println("Company Service constructor");
        this.companyRepo = companyRepo;
        this.jobClient = jobClient;
        this.reviewClient = reviewClient;
    }

    @PostConstruct
    public void init() throws Exception{
        System.out.println("Company Service Bean init method()");
    }

    @PreDestroy
    public void destory() throws Exception{
        System.out.println("Company Service Bean Destroy method()");
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcess After Initialization");
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcess Before Initialization");
        return bean;
    }

    @Override
    public List<CompanyRes> findAll() {
        List<Company> companyList = companyRepo.findAll();
        List<CompanyRes> companyResList = new ArrayList<>();
        for (Company company : companyList) {
            CompanyRes companyRes = getCompanyResObject(company);
            companyResList.add(companyRes);
        }
        return companyResList;
    }

    private CompanyRes getCompanyResObject(Company company) {
        CompanyRes companyRes = new CompanyRes();
        BeanUtils.copyProperties(company, companyRes);
//        for (Long jobId : company.getJobsId()) {
//            JobRes body = jobClient.getJobById(jobId).getBody();
//            companyRes.getJobs().add(body);
//        }
//        for (Long reviewId : company.getReviewsId()) {
//            ReviewRes reviewRes = reviewClient.getReviewById(reviewId).getBody();
//            companyRes.getReviews().add(reviewRes);
//        }
        return companyRes;
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
            for (JobReq jobReq : companyReq.getJobs()) {
                jobReq.setCompanyId(saveCompany.getId());
                Long body = jobClient.addJob(jobReq).getBody();
                saveCompany.getJobsId().add(body);
            }
            // call review microservice
            for (ReviewReq reviewReq : companyReq.getReviews()) {
                Long reviewId = reviewClient.addReview(saveCompany.getId(), reviewReq).getBody();
                saveCompany.getReviewsId().add(reviewId);
            }
            companyRepo.save(saveCompany);
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
            companyRes = getCompanyResObject(entity);
        }
        return companyRes;
    }

    @Override
    public CompanyRes getCompanyByIdForReviews(long id) {
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
            List<Long> jobId = updated.getJobs().stream().map(JobReq::getId).toList();
            List<Long> reviewId = updated.getReviews().stream().map(ReviewReq::getId).toList();
            company.setJobsId(jobId);
            company.setReviewsId(reviewId);
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
            for (Long reviewId : company.getReviewsId()) {
                boolean isDeletedReview = reviewClient.deleteReviewById(reviewId);
            }
            for (Long jobId : company.getJobsId()) {
                boolean isDeletedJob = jobClient.deleteJobById(jobId);
            }
            companyRepo.delete(company);
            return true;
        }
        return false;
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        System.out.println(" id " + reviewMessage.getId() + " " + reviewMessage.getCompanyId());
        Double average = reviewClient.getAverageRating(reviewMessage.getCompanyId());
        Double newAverage = (reviewMessage.getRating() + average) / 2;
        System.out.println(newAverage);
        if (reviewMessage.getCompanyId() != null && reviewMessage.getCompanyId() > 0) {
            Optional<Company> optionalCompany = Optional.ofNullable(companyRepo.findById(reviewMessage.getCompanyId()).orElseThrow(() -> new NotFoundException("Company is Not Found with Id =" + reviewMessage.getCompanyId())));
            if (optionalCompany.isPresent()) {
                Company company = optionalCompany.get();
                company.getReviewsId().add(reviewMessage.getId());
                company.setCompanyRating(newAverage);
                companyRepo.save(company);
            }
        }
    }

    @Override
    public void deleteReviewId(ReviewMessage reviewMessage) {
        if(reviewMessage != null) {
            Optional<Company> optionalCompany = companyRepo.findById(reviewMessage.getCompanyId());
            if (optionalCompany.isPresent()) {
                Company company = optionalCompany.get();
                company.getReviewsId().removeIf(dbReview -> dbReview == reviewMessage.getId());
                companyRepo.save(company);
            }
        }
    }

    @Override
    public void updateJobByCompanyId(JobMessage jobMessage) {
        if (jobMessage.getCompanyId() != null && jobMessage.getCompanyId() > 0) {
            Optional<Company> optionalCompany = Optional.ofNullable(companyRepo.findById(jobMessage.getCompanyId())
                    .orElseThrow(() -> new NotFoundException("Company is Not Found with Id =" + jobMessage.getCompanyId())));
            if (optionalCompany.isPresent()) {
                Company company = optionalCompany.get();
                company.getJobsId().add(jobMessage.getJobId());
                companyRepo.save(company);
            }
        }
    }

    @Override
    public void deleteJobId(JobMessage jobMessage) {
        if(jobMessage != null) {
            Optional<Company> optionalCompany = Optional.ofNullable(companyRepo.findById(jobMessage.getCompanyId()).orElseThrow(() -> new NotFoundException("Company is Not Found with Id =" + jobMessage.getCompanyId())));
            if (optionalCompany.isPresent()) {
                Company company = optionalCompany.get();
                company.getJobsId().removeIf(dbJob-> dbJob == jobMessage.getJobId());
                companyRepo.save(company);
            }
        }
    }

}
