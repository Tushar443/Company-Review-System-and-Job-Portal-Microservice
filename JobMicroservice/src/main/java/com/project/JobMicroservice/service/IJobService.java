package com.project.JobMicroservice.service;

import com.project.JobMicroservice.dto.request.JobReq;
import com.project.JobMicroservice.dto.response.JobCompanyReviewDTO;

import java.util.List;

public interface IJobService {

    List<JobCompanyReviewDTO> findAll();

    void createJob(List<JobReq> list);

    boolean updateJob(long id ,JobReq job);

    JobCompanyReviewDTO getJobByID(long id);

    boolean deleteJobById(long id);
}
