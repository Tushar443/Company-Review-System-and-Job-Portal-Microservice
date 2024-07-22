package com.project.JobMicroservice.service;

import com.project.JobMicroservice.beans.Job;
import com.project.JobMicroservice.client.CompanyClient;
import com.project.JobMicroservice.client.ReviewClient;
import com.project.JobMicroservice.dto.request.JobReq;
import com.project.JobMicroservice.dto.response.CompanyRes;
import com.project.JobMicroservice.dto.response.JobRes;
import com.project.JobMicroservice.dto.response.ReviewRes;
import com.project.JobMicroservice.messaging.JobMessageProducer;
import com.project.JobMicroservice.repository.JobRepo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements IJobService{

    JobRepo jobRepo;

    CompanyClient companyClient;

    ReviewClient reviewClient;

    private final JobMessageProducer jobMessageProducer;

    public JobServiceImpl(JobRepo jobRepo , CompanyClient companyClient, ReviewClient reviewClient, JobMessageProducer jobMessageProducer) {
        this.jobRepo = jobRepo;
        this.companyClient=companyClient;
        this.reviewClient=reviewClient;
        this.jobMessageProducer = jobMessageProducer;
    }

    @Override
    public List<JobRes> findAll() {
        List<Job> list = jobRepo.findAll();
        return list.stream().map(job -> {
            CompanyRes companyRes = companyClient.getCompanyById(job.getCompanyId());
            ResponseEntity<List<ReviewRes>> allReviews = reviewClient.getAllReviews(job.getCompanyId());
            JobRes jobRes = new JobRes();
            BeanUtils.copyProperties(job,jobRes);
            jobRes.setCompanyRes(companyRes);
            jobRes.setReviewRes(allReviews.getBody());
            return jobRes;
        }).collect(Collectors.toList());
    }

    @Override
    public Long createJob(JobReq jobReq) {
            Job job = new Job();
        CompanyRes companyRes = companyClient.getCompanyById(jobReq.getCompanyId());
        if(companyRes != null) {
            BeanUtils.copyProperties(jobReq, job);
            Job saveJob = jobRepo.save(job);
            jobMessageProducer.addJobToCompany(saveJob);
            return saveJob.getId();
        }
        return null;
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
    public JobRes getJobByID(long id) {
       Optional<Job> job= jobRepo.findById(id);
       JobRes jobRes = new JobRes();
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
            CompanyRes companyRes = companyClient.getCompanyById(oldJob.getCompanyId());
            if(companyRes !=  null) {
                jobMessageProducer.deleteJobFromCompany(oldJob);
                jobRepo.delete(oldJob);
                return true;
            }
        }
        return false;
    }
}
