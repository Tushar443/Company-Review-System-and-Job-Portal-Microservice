package com.project.CompanyMicroservice.repository;

import com.project.CompanyMicroservice.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends JpaRepository<Company,Long> {
    Company findCompanyByName(String name);
}
