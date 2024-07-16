package com.project.CompanyMicroservice.controller;

import com.project.CompanyMicroservice.dto.request.CompanyReq;
import com.project.CompanyMicroservice.dto.response.CompanyRes;
import com.project.CompanyMicroservice.service.ICompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company/")
public class CompanyController {

    ICompanyService companyService;

    public CompanyController(ICompanyService companyService) {
        this.companyService = companyService;
    }

    //get All company
    @GetMapping
    public ResponseEntity<List<CompanyRes>> getAllCompany(){
        List<CompanyRes> companyRes = companyService.findAll();
        if(companyRes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(companyRes,HttpStatus.OK);
    }
    //get company By Id
    @GetMapping("{id}")
    public CompanyRes getCompanyById(@PathVariable long id){
        CompanyRes companyRes = companyService.getCompanyById(id);
        if(companyRes.getName() != null){
            return companyRes;
        }
        return null;
    }
    //add
    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody CompanyReq companyReq){
        boolean isAdded = companyService.addCompany(companyReq);
        if (isAdded){
            return new ResponseEntity<>("Company Added",HttpStatus.OK);
        }
        return new ResponseEntity<>("Company Existed ",HttpStatus.CONFLICT);
    }
    //update
    @PutMapping("{id}")
    public boolean updateCompany(@PathVariable long id ,@RequestBody CompanyReq companyReq){
       return companyService.updateCompany(id,companyReq);
    }

    @PutMapping("/updateJobs")
    boolean updateCompanyJobId(@RequestParam long companyId, @RequestParam long jobId){
       return companyService.updateCompanyJobId(companyId,jobId);
    }

    @PutMapping("/company/updateReviews")
    boolean updateCompanyReviewId(@RequestParam long companyId, @RequestParam long reviewId){
        return companyService.updateCompanyReviewId(companyId,reviewId);
    }

    //delete
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id){
        boolean deleted = companyService.deleteCompany(id);
        if(deleted){
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }
}
