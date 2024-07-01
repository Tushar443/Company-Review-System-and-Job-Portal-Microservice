package com.project.ReviewMicroservice.repository;

import com.project.ReviewMicroservice.dao.beans.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review,Long> {
    List<Review> findAllByCompanyId(long companyId);

    Review findAllByIdAndCompanyId(long reviewId, long companyId);
}
