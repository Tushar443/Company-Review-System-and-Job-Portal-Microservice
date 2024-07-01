package com.project.CompanyMicroservice.controller;

import com.project.CompanyMicroservice.dao.request.CompanyReq;
import com.project.CompanyMicroservice.dao.response.CompanyRes;
import com.project.CompanyMicroservice.service.ICompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
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
    @GetMapping("/{id}")
    public ResponseEntity<CompanyRes> getCompanyById(@PathVariable long id){
        CompanyRes companyRes = companyService.getCompanyById(id);
        if(companyRes != null){
            return  new ResponseEntity<>(companyRes,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //add
    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody List<CompanyReq> companyReq){
       companyService.addCompany(companyReq);
       return new ResponseEntity<>("Company Added",HttpStatus.OK);
    }
    //update
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable long id ,@RequestBody CompanyReq companyReq){
       boolean update = companyService.updateCompany(id,companyReq);
       if(update){
           return new ResponseEntity<>("Update Company Successfully",HttpStatus.OK);
       }
       return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }
    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id){
        boolean deleted = companyService.deleteCompany(id);
        if(deleted){
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }
}
