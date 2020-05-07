package com.movie.service.Api;

import com.movie.service.Dao.Movie;
import com.movie.service.Dao.UpdateResource;
import com.movie.service.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RestAPI {
    @Autowired
    MovieService movieService;

    @GetMapping("/movies")
    @PreAuthorize("hasRole('USER')")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }


    @PostMapping("/update")
    @PreAuthorize("hasRole('USER')")
    public void updateMoviePoints(@RequestBody UpdateResource updateResource) {
       Optional<Movie> movie = movieService.getMovie(updateResource.getMovieID());
       movie.get().setPoints(updateResource.getPoints());

       movieService.addMovies(movie.get());


    }

    @GetMapping(value = "movie/{id}")
    @PreAuthorize("hasRole('USER')")
    public Optional<Movie> getMovie(@PathVariable int id) {

        Optional<Movie> movie = movieService.getMovie(id);

        return movie;
    }





    @PostMapping(path = "/addmovie")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addMovies(@RequestBody Movie movie) {


        movieService.addMovies(movie);




        return "Movie has been successfully added.";


    }







}
