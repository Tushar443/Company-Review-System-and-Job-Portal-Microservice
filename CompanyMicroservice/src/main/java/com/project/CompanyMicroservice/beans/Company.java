package com.project.CompanyMicroservice.beans;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private String name;
    private String description;
    private List<Long> jobId;
    private List<Long> reviewId;
    public Company() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public List<Long> getJobId() {
        return jobId;
    }

    public void setJobId(List<Long> jobId) {
        this.jobId = jobId;
    }

    public List<Long> getReviewId() {
        return reviewId;
    }

    public void setReviewId(List<Long> reviewId) {
        this.reviewId = reviewId;
    }
}
