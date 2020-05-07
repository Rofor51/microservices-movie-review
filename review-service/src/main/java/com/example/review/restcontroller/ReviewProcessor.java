package com.example.review.restcontroller;

import com.example.review.dao.Review;
import com.example.review.dao.UpdateResource;
import com.example.review.dao.User;
import com.example.review.repository.ReviewRepository;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Component
public class ReviewProcessor {
    private final ObjectMapper objectMapper;
    private final static Logger LOGGER = LoggerFactory.getLogger(ReviewProcessor.class);

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    public ReviewProcessor(ObjectMapper objectMapper) {
        super();
        this.objectMapper = objectMapper;
    }

    public void receiveMessage(String roomJson) {
        LOGGER.info("Message Received.");

        try {
            Review review = this.objectMapper.readValue(roomJson,Review.class);
            LOGGER.info("New review has been received and saved!");


            reviewRepository.save(review);



        }catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
