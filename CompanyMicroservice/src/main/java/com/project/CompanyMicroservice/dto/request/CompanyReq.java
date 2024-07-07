package com.project.CompanyMicroservice.dto.request;

import java.util.List;

public class CompanyReq {
    private String name;
    private String description;
    private List<Long> jobsId;
    private List<Long> reviewsId;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getJobsId() {
        return jobsId;
    }

    public void setJobsId(List<Long> jobsId) {
        this.jobsId = jobsId;
    }

    public List<Long> getReviewsId() {
        return reviewsId;
    }

    public void setReviewsId(List<Long> reviewsId) {
        this.reviewsId = reviewsId;
    }
}
