package com.project.CompanyMicroservice.controller;

import com.project.CompanyMicroservice.service.ICompanyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    ICompanyService companyService;

    public CompanyController(ICompanyService companyService) {
        this.companyService = companyService;
    }

    //get All

    //getById

    //add

    //update

    //delete

    //

}
