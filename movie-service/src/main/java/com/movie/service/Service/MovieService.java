package com.movie.service.Service;

import com.movie.service.Dao.Movie;
import com.movie.service.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }



    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovie(int id) {
        return movieRepository.findById(id);
    }

    public void addMovies(Movie movie) {
        movieRepository.save(movie);
    }


}
