package com.project.JobMicroservice.repository;

import com.project.JobMicroservice.dao.beans.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepo extends JpaRepository<Job,Long> {
}
