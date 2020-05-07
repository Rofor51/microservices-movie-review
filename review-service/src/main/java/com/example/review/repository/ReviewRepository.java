package com.example.review.repository;

import com.example.review.dao.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {
    List<Review> findAllByMovieID(int id);

    Optional<Review> findAllByMovieIDAndUserID(int id, String user);


}
