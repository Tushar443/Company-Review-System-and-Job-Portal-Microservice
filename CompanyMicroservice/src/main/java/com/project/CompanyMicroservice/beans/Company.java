package com.project.CompanyMicroservice.beans;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private String name;
    private String description;
    private Double companyRating;
    private List<Long> jobsId;
    private List<Long> reviewsId;
    public Company() {
        jobsId = new ArrayList<>();
        reviewsId = new ArrayList<>();
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

    public Double getCompanyRating() {
        return companyRating;
    }

    public void setCompanyRating(Double companyRating) {
        this.companyRating = companyRating;
    }
}
