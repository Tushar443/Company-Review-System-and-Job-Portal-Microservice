package com.project.job_microservice.service;



import com.project.job_microservice.dao.request.JobReq;
import com.project.job_microservice.dao.response.JobRes;

import java.util.List;

public interface IJobService {

    List<JobRes> findAll();

    void createJob(List<JobReq> list);

    boolean updateJob(long id ,JobReq job);

    JobRes getJobByID(long id);

    boolean deleteJobById(long id);
}
