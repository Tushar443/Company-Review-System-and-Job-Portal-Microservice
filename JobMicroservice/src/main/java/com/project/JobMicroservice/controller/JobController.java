package com.project.JobMicroservice.controller;

import com.project.JobMicroservice.dto.request.JobReq;
import com.project.JobMicroservice.dto.response.JobRes;
import com.project.JobMicroservice.service.IJobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/jobs/")
public class JobController {

    IJobService iJobService;

    public JobController(IJobService iJobService) {
        this.iJobService = iJobService;
    }

    @GetMapping
    public ResponseEntity<List<JobRes>> getAllJobs(){
        List<JobRes> list = iJobService.findAll();
        if(list.isEmpty()){
            return new ResponseEntity<>(list,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @PostMapping
    public Long addJob(@RequestBody JobReq jobReq){
        return iJobService.createJob(jobReq);
    }
    @GetMapping("{id}")
    public ResponseEntity<JobRes> getJobById(@PathVariable Long id){
       JobRes jobRes = iJobService.getJobByID(id);
       return new ResponseEntity<>(jobRes,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public boolean deleteJobById(@PathVariable long id ){
       return iJobService.deleteJobById(id);
    }
    @PutMapping("{id}")
    public ResponseEntity<String> updateJob(@PathVariable int id ,@RequestBody JobReq jobReq){
        boolean updated =iJobService.updateJob(id ,jobReq);
        if(updated){
            return new ResponseEntity<>("Updated",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
