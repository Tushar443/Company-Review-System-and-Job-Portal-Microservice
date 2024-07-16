package com.project.ReviewMicroservice.dto.response;

import java.util.List;

public class JobRes {
    private long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private CompanyRes companyRes;
    private List<ReviewRes> reviewRes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }

    public CompanyRes getCompanyRes() {
        return companyRes;
    }

    public void setCompanyRes(CompanyRes companyRes) {
        this.companyRes = companyRes;
    }

    public List<ReviewRes> getReviewRes() {
        return reviewRes;
    }

    public void setReviewRes(List<ReviewRes> reviewRes) {
        this.reviewRes = reviewRes;
    }
}
