package com.project.job_microservice.repository;

import com.project.job_microservice.dao.beans.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepo extends JpaRepository<Job,Long> {
}
