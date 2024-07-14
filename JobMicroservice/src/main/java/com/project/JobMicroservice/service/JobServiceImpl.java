package com.project.JobMicroservice.service;

import com.project.JobMicroservice.beans.Job;
import com.project.JobMicroservice.client.CompanyClient;
import com.project.JobMicroservice.client.ReviewClient;
import com.project.JobMicroservice.dto.request.JobReq;
import com.project.JobMicroservice.dto.response.CompanyRes;
import com.project.JobMicroservice.dto.response.JobCompanyReviewDTO;
import com.project.JobMicroservice.dto.response.ReviewRes;
import com.project.JobMicroservice.repository.JobRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements IJobService{

    JobRepo jobRepo;

    CompanyClient companyClient;

    ReviewClient reviewClient;

    public JobServiceImpl(JobRepo jobRepo , CompanyClient companyClient,ReviewClient reviewClient) {
        this.jobRepo = jobRepo;
        this.companyClient=companyClient;
        this.reviewClient=reviewClient;
    }

    @Override
    public List<JobCompanyReviewDTO> findAll() {
        List<Job> list = jobRepo.findAll();
        return list.stream().map(job -> {
            CompanyRes companyRes = companyClient.getCompanyById(job.getCompanyId());
            ResponseEntity<List<ReviewRes>> allReviews = reviewClient.getAllReviews(job.getCompanyId());
            JobCompanyReviewDTO jobCompanyReviewDTO = new JobCompanyReviewDTO();
            BeanUtils.copyProperties(job,jobCompanyReviewDTO);
            jobCompanyReviewDTO.setCompanyRes(companyRes);
            jobCompanyReviewDTO.setReviewRes(allReviews.getBody());
            return jobCompanyReviewDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public void createJob(JobReq jobReq) {
            Job job = new Job();
            BeanUtils.copyProperties(jobReq,job);
            jobRepo.save(job);
    }

    @Override
    public boolean updateJob(long id ,JobReq updatedJob) {
        Optional<Job> job= jobRepo.findById(id);
            if(job.isPresent()){
                Job oldJob = job.get();
                BeanUtils.copyProperties(updatedJob,oldJob);
                jobRepo.save(oldJob);
                return true;
            }
        return false;
    }

    @Override
    public JobCompanyReviewDTO getJobByID(long id) {
       Optional<Job> job= jobRepo.findById(id);
       JobCompanyReviewDTO jobRes = new JobCompanyReviewDTO();
       if(job.isPresent()){
           BeanUtils.copyProperties(job.get(),jobRes);
           return jobRes;
       }
       return jobRes;
    }

    @Override
    public boolean deleteJobById(long id) {
        Optional<Job> job= jobRepo.findById(id);
        if(job.isPresent()) {
            Job oldJob = job.get();
            jobRepo.delete(oldJob);
            return true;
        }
        return false;
    }
}
