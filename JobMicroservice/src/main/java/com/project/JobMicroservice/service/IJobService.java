package com.project.JobMicroservice.service;

import com.project.JobMicroservice.dto.request.JobReq;
import com.project.JobMicroservice.dto.response.JobRes;

import java.util.List;

public interface IJobService {

    List<JobRes> findAll();

    void createJob(List<JobReq> list);

    boolean updateJob(long id ,JobReq job);

    JobRes getJobByID(long id);

    boolean deleteJobById(long id);
}
