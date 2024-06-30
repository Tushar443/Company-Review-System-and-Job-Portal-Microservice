package com.project.CompanyMicroservice.dao.response;

import java.util.List;

public class CompanyRes {
    private String name;
    private String description;
    private List<Long> jobs;

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

    public List<Long> getJobs() {
        return jobs;
    }

    public void setJobs(List<Long> jobs) {
        this.jobs = jobs;
    }
}
