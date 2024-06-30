package com.project.JobMicroservice.controller;

import com.project.JobMicroservice.dao.request.JobReq;
import com.project.JobMicroservice.service.IJobService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/jobs")
public class JobController {

    IJobService iJobService;

    public JobController(IJobService iJobService) {
        this.iJobService = iJobService;
    }

    @GetMapping
    public List<JobReq> getHello(){
        return iJobService.findAll();
    }

    @PostMapping
    public String addJob(@RequestBody List<JobReq> jobReq){
        iJobService.createJob(jobReq);
        return "Job add Successfully";
    }
    @GetMapping("/{id}")
    public JobReq getJobById(@PathVariable int id){
       return iJobService.getJobByID(id);
    }
//    @PutMapping
//    public String updateJob(@RequestBody JobReq jobReq){
//        Optional<JobReq> oldJob = arr.stream().filter(jr -> jobReq.getId() == jr.getId()).findAny();
//        int index = arr.indexOf(jobReq);
//        if(oldJob.isPresent()){
//               JobReq old = oldJob.get();
//               arr.add(index,jobReq);
//        }else{
//            throw new NoSuchElementException("Job not Found");
//        }
//        return "Job Updated Successfully";
//    }
}
