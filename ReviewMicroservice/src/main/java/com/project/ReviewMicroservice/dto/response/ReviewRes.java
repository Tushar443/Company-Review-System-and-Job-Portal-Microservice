package com.project.ReviewMicroservice.dto.response;

public class ReviewRes {
    private long id;
    private String title;
    private String description;
    private double rating;
    private CompanyRes companyRes;

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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public CompanyRes getCompanyRes() {
        return companyRes;
    }

    public void setCompanyRes(CompanyRes companyRes) {
        this.companyRes = companyRes;
    }
}
