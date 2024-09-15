package com.example.demo.service;

import com.example.demo.model.Movies;
import com.example.demo.repository.MoviesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoviesService {
    
    private final MoviesRepository moviesRepository;

    public MoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }
    // get all movies
    public  List<Movies> getAllMovies() {
        return moviesRepository.findAll();
    }
    // get movie by ID
    public Movies getMovieById(Long id) {
        return moviesRepository.findById(id).orElse(null);
    }
    // save movie
    @Transactional
    public Movies saveMovie(Movies movie) {
        return moviesRepository.save(movie);
    }
    // delete movie
    @Transactional
    public void deleteMovie(Long id) {
        //Check existence of the movie before deletion
        Optional<Movies> movie = moviesRepository.findById(id);
        movie.ifPresent(m -> moviesRepository.deleteById(id));
    }

    @Transactional
    public List<Movies> getAllAvailableMovies() {
        List<Movies> moviesAvailable =  moviesRepository.getAllAvailableMovies();
        return moviesAvailable;
    }
}




