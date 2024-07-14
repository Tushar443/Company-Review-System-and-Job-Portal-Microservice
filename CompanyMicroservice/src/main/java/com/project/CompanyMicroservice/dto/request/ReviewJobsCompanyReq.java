package com.project.CompanyMicroservice.dto.request;

import java.util.List;

public class ReviewJobsCompanyReq {
    private String name;
    private String companyDescription;
    private List<Long> jobsId;
    private List<Long> reviewsId;


    private String jobTitle;
    private String jobDescription;
    private String minSalary;
    private String maxSalary;
    private String location;


    private String title;
    private String reviewDescription;
    private double rating;


}
