package com.io.movies.movieexample.controller;


import com.io.movies.movieexample.Dao.Movie;
import com.io.movies.movieexample.config.AccessConfiguration;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;

@Controller
public class MovieController {

    @Autowired
    RestTemplate restTemplate;


    @GetMapping(value = "/movies")
    public String movies(Model model) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessConfiguration.getAccessToken());

        HttpEntity<Movie> request = new HttpEntity<>(httpHeaders);

        ResponseEntity<Movie[]> response = restTemplate
                .exchange("http://localhost:8181/movies", HttpMethod.GET, request, Movie[].class);


        Movie [] movies = response.getBody();



        model.addAttribute("movies",movies);


        return "movies";
    }

    @GetMapping(value = "movie/{id}")
    public String getMovies(Model model, @PathVariable int id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessConfiguration.getAccessToken());


        HttpEntity<Movie> request = new HttpEntity<>(httpHeaders);

        ResponseEntity<Movie> response = restTemplate
                .exchange("http://localhost:8181/movie/" + id, HttpMethod.GET, request, Movie.class);

        Movie movie = response.getBody();


        model.addAttribute("movie",movie);

        return "movie";

    }

    @GetMapping(value = "/add-movie")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addMovie(Model model) {

        Movie movie = new Movie();
        model.addAttribute("movie",movie);
        return "add-movie";
    }


    @PostMapping(value = "/add-movie")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveMovie(Movie movie) throws JSONException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", AccessConfiguration.getAccessToken());

        HttpEntity<Movie> request = new HttpEntity<>(movie, headers);

        RestTemplate restTemplate = new RestTemplate();


        restTemplate.postForObject("http://localhost:8181/addmovie",request,String.class);






        return "add-movie";
    }







    }

