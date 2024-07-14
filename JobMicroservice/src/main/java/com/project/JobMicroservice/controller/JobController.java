package com.project.JobMicroservice.controller;

import com.project.JobMicroservice.dto.request.JobReq;
import com.project.JobMicroservice.dto.response.JobCompanyReviewDTO;
import com.project.JobMicroservice.service.IJobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs/")
public class JobController {

    IJobService iJobService;

    public JobController(IJobService iJobService) {
        this.iJobService = iJobService;
    }

    @GetMapping
    public ResponseEntity<List<JobCompanyReviewDTO>> getAllJobs(){
        List<JobCompanyReviewDTO> list = iJobService.findAll();
        if(list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addJob(@RequestBody List<JobReq> jobReq){
         iJobService.createJob(jobReq);
         return new ResponseEntity<>("Job add Successfully",HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<JobCompanyReviewDTO> getJobById(@PathVariable int id){
       JobCompanyReviewDTO jobRes = iJobService.getJobByID(id);
       return new ResponseEntity<>(jobRes,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable int id ){
        boolean deleted = iJobService.deleteJobById(id);
        if(deleted) return new ResponseEntity<String>("Job Deleted", HttpStatus.OK);
        return new ResponseEntity<String>( HttpStatus.NOT_FOUND);
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
