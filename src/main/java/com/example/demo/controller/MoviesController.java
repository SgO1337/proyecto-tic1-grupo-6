package com.example.demo.controller;

import com.example.demo.model.Movies;
import com.example.demo.service.MoviesService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
// @CrossOrigin(origins = "http://localhost:3000") // Uncomment for production
public class MoviesController {

    private final MoviesService moviesService;

    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    // List all movies
    @GetMapping
    public ResponseEntity<List<Movies>> listMovies() {
        List<Movies> movies = moviesService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    // List currently available movies
    @GetMapping("/currently-available")
    public ResponseEntity<List<Movies>> listAvailableMovies() {
        List<Movies> availableMovies = moviesService.getAllAvailableMovies();
        return ResponseEntity.ok(availableMovies);
    }

    // View a specific movie by ID
    @GetMapping("/view/{id}")
    public ResponseEntity<?> viewMovie(@PathVariable Long id) {
        Movies movie = moviesService.getMovieById(id);
        if (movie == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found.");
        }
        return ResponseEntity.ok(movie);
    }

    // Create a new movie
    @PostMapping("/create")
    public ResponseEntity<Movies> createMovie(@RequestBody Movies movie) {
        moviesService.saveMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movie);
    }

    // Update an existing movie
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateMovie(@PathVariable Long id, @RequestBody Movies updatedMovie) {
        Movies existingMovie = moviesService.getMovieById(id);
        if (existingMovie == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found.");
        }

        // Update movie details, or use a utility to map fields
        existingMovie.setTitle(updatedMovie.getTitle());
        existingMovie.setDirector(updatedMovie.getDirector());
        existingMovie.setReleaseDate(updatedMovie.getReleaseDate());
        // Add other fields as necessary

        moviesService.saveMovie(existingMovie);
        return ResponseEntity.ok("Movie updated successfully.");
    }

    // Delete a movie by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        Movies movie = moviesService.getMovieById(id);
        if (movie == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found.");
        }

        moviesService.deleteMovie(id);
        return ResponseEntity.ok("Movie deleted successfully.");
    }
}
