package com.project.JobMicroservice.service;

import com.project.JobMicroservice.dao.request.JobReq;

import java.util.List;
import java.util.Optional;

public interface IJobService {

    List<JobReq> findAll();

    void createJob(List<JobReq> list);

    void updateJob(JobReq job);

    JobReq getJobByID(int id);
}
