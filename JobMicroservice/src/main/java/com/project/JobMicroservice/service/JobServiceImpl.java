package com.project.JobMicroservice.service;

import com.project.JobMicroservice.dao.request.JobReq;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements IJobService{
    List<JobReq> arr = new ArrayList<>();
    @Override
    public List<JobReq> findAll() {
        return arr;
    }

    @Override
    public void createJob(List<JobReq> list) {
        list.forEach(a-> arr.add(a));
    }

    @Override
    public void updateJob(JobReq job) {

    }

    @Override
    public JobReq getJobByID(int id) {
       Optional<JobReq> job = arr.stream().filter(jobReq -> jobReq.getId() == id).findAny();
       if(job.isPresent()){
           return job.get();
       }
       return null;
    }
}
