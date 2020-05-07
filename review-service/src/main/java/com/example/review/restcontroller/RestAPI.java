package com.example.review.restcontroller;

import com.example.review.dao.RemoveFromList;
import com.example.review.dao.Review;
import com.example.review.dao.User;
import com.example.review.repository.ReviewRepository;
import com.example.review.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RestAPI {
    @Autowired
    UserRepository repository;

    @Autowired
    ReviewRepository reviewRepository;


        @GetMapping(value = "/getreviews/{id}")
        @PreAuthorize("hasRole('USER')")
        public List<Review> getReviews(@PathVariable int id) {
           List<Review> reviews =  reviewRepository.findAllByMovieID(id);

           return reviews;


        }

        @PostMapping(value = "/remove")
        @PreAuthorize("hasRole('USER')")
        public String removeReviews(@RequestBody RemoveFromList removeFromList) {

            if(reviewRepository.findAllByMovieIDAndUserID(removeFromList.getMovieID(),removeFromList.getUserID()).isPresent()) {
               reviewRepository.delete(reviewRepository.findAllByMovieIDAndUserID(removeFromList.getMovieID(),removeFromList.getUserID()).get());

            }
            else {
              return "Not successful";
            }


            return "Success";




        }





}
