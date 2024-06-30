package com.project.CompanyMicroservice.dao.beans;

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
    private List<Long> jobs;

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

    public List<Long> getJobs() {
        return jobs;
    }

    public void setJobs(List<Long> jobs) {
        this.jobs = jobs;
    }
}
