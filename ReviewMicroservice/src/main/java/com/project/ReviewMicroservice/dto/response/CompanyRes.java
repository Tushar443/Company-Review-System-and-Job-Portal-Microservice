package com.project.ReviewMicroservice.dto.response;

import java.util.List;

public class CompanyRes {
    private String name;
    private String description;
    private List<JobRes> jobs;
    private List<ReviewRes> reviews;

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

    public List<JobRes> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobRes> jobs) {
        this.jobs = jobs;
    }

    public List<ReviewRes> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewRes> reviews) {
        this.reviews = reviews;
    }
}
