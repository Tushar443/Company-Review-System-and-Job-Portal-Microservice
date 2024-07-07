package com.project.JobMicroservice.service;

import com.project.JobMicroservice.beans.Job;
import com.project.JobMicroservice.dto.request.JobReq;
import com.project.JobMicroservice.dto.response.JobRes;
import com.project.JobMicroservice.repository.JobRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements IJobService{

    JobRepo jobRepo;

    public JobServiceImpl(JobRepo jobRepo) {
        this.jobRepo = jobRepo;
    }

    @Override
    public List<JobRes> findAll() {
        List<Job> list = jobRepo.findAll();
        List<JobRes> resList = new ArrayList<>();
        for(Job job : list){
            JobRes jobRes = new JobRes();
            BeanUtils.copyProperties(job,jobRes);
            resList.add(jobRes);
        }
        return resList;
    }

    @Override
    public void createJob(List<JobReq> list) {
        for (JobReq jobReq : list) {
            Job job = new Job();
            BeanUtils.copyProperties(jobReq,job);
            jobRepo.save(job);
        }
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
            jobRepo.delete(oldJob);
            return true;
        }
        return false;
    }
}
