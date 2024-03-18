package com.example.demo.dao;

import com.example.demo.enteties.Review;
import com.example.demo.enteties.ReviewPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, ReviewPk> {
    @Query("select u from Review u where u.buyers.id= :id")
    public List<Review> getReviewByBuyers(@Param("id")Integer id);
}
