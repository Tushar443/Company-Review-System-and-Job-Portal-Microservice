//package com.project.JobMicroservice.dao.beans;
//
//import jakarta.persistence.*;
//import jdk.jfr.Enabled;
//
//@Entity
//@Table(name = "Job")
//public class Job {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long id ;
//    private String title;
//    private String description;
//    private String minSalary;
//    private String maxSalary;
//    private String location;
//
//    public Job() {
//    }
//
//    public Job(String description, String title, String minSalary, String maxSalary, String location) {
//        this.description = description;
//        this.title = title;
//        this.minSalary = minSalary;
//        this.maxSalary = maxSalary;
//        this.location = location;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getMinSalary() {
//        return minSalary;
//    }
//
//    public void setMinSalary(String minSalary) {
//        this.minSalary = minSalary;
//    }
//
//    public String getMaxSalary() {
//        return maxSalary;
//    }
//
//    public void setMaxSalary(String maxSalary) {
//        this.maxSalary = maxSalary;
//    }
//
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//}
