package com.io.movies.movieexample.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.io.movies.movieexample.Dao.RemoveFromList;
import com.io.movies.movieexample.Dao.Review;
import com.io.movies.movieexample.Dao.UpdateMovieResource;
import com.io.movies.movieexample.config.AccessConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;


import java.util.Arrays;
import java.util.List;


@Controller
@SessionAttributes({"reviewObject","updateReviews"})
public class ReviewController {




    @Value("${amqp.queue.name}")
    private String queueName;

    private RestTemplate restTemplate;
    private final RabbitTemplate rabbitTemplate;
    private final ConfigurableApplicationContext context;
    private ObjectMapper objectMapper;

    @Autowired
    public ReviewController(RabbitTemplate rabbitTemplate, ConfigurableApplicationContext context, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.context = context;
        this.objectMapper = objectMapper;
        this.restTemplate = new RestTemplate();
    }




    @GetMapping(value = "/review/{id}")
    public String review(@PathVariable int id, Model model) {
        Review review = new Review();
        review.setMovieID(id);

        model.addAttribute("reviewObject",review);



        return "review";
    }

    @PostMapping(value = "/review")
    public String addReview(@ModelAttribute("reviewObject") Review review) throws JsonProcessingException {
            review.setUserID(AccessConfiguration.getAuthenticatedUser());
            String jsonString = objectMapper.writeValueAsString(review);


            rabbitTemplate.convertAndSend(queueName,jsonString);


        return "review";
    }

    @GetMapping(value = "/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateReview(@PathVariable int id, Model model) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessConfiguration.getAccessToken());


        HttpEntity<Review> request = new HttpEntity<>(httpHeaders);

        ResponseEntity<Review[]> response = restTemplate
                .exchange("http://localhost:8282/getreviews/" + id, HttpMethod.GET, request, Review[].class);

        List<Review> reviews = Arrays.asList(response.getBody());

        model.addAttribute("updateReviews",reviews);


        return "admin-review-update";
    }

    @PostMapping(value = "/remove")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView removeFromList(String username, int id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessConfiguration.getAccessToken());
        RemoveFromList removeFromList = new RemoveFromList(username,id);


        HttpEntity<RemoveFromList> request = new HttpEntity<>(removeFromList, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("http://localhost:8282/remove",request,String.class);






        return new ModelAndView("redirect:/update/" + id);

    }



    @PostMapping(value = "/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView updateReviewsForMovie(@ModelAttribute("updateReviews") List<Review> reviews) {

        int movieID = reviews.get(0).getMovieID();

        double avg = reviews.stream().mapToDouble(i -> i.getPoints()).average().orElse(0.0);


       UpdateMovieResource movieResource = new UpdateMovieResource(movieID,avg);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", AccessConfiguration.getAccessToken());

        HttpEntity<UpdateMovieResource> request = new HttpEntity<>(movieResource, headers);

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.postForObject("http://localhost:8181/update",request,String.class);

        return new ModelAndView("redirect:/update/" + movieID);
    }
}
