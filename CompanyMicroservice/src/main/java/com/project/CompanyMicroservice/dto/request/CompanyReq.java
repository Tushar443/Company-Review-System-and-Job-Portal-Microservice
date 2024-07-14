package com.project.CompanyMicroservice.dto.request;

import java.util.List;

public class CompanyReq {
    private String name;
    private String description;
    private List<JobReq> jobs;
    private List<ReviewReq> reviews;

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

    public List<JobReq> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobReq> jobs) {
        this.jobs = jobs;
    }

    public List<ReviewReq> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewReq> reviews) {
        this.reviews = reviews;
    }
}
