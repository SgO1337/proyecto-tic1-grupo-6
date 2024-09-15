package com.example.demo.controller;

import com.example.demo.model.Movies;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import com.example.demo.model.Movies;
import com.example.demo.service.MoviesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
    @RestController  // Cambio a RestController para respuestas en JSON
    @RequestMapping("/api/movies")  // Cambia la URL base para empezar con /api
    @CrossOrigin(origins = "http://localhost:3000")  // Permitir CORS para tu aplicación React
    public class MoviesController {

        private final MoviesService moviesService;

        public MoviesController(MoviesService moviesService) {
            this.moviesService = moviesService;
        }

        // Listar todas las películas
        @GetMapping
        public List<Movies> listMovies() {
            return moviesService.getAllMovies();  // Retorna una lista de películas en JSON
        }

        // Listar todas las películas
        @GetMapping("/currently-available")
        public List<Movies> listAvailableMovies() {
            return moviesService.getAllAvailableMovies();  // Retorna una lista de todas las películas en cartelera en JSON
        }

        // Crear una nueva película
        @PostMapping("/create")
        public Movies createMovie(@RequestBody Movies movie) {
            return moviesService.saveMovie(movie);  // Crea y retorna la nueva película como JSON
        }

        // Ver una película específica por ID
        @GetMapping("/view/{id}")
        public Movies viewMovie(@PathVariable Long id) {
            return moviesService.getMovieById(id);  // Retorna la película como JSON
        }

        // Actualizar una película existente
        @PutMapping("/update/{id}")
        public Movies updateMovie(@PathVariable Long id, @RequestBody Movies updatedMovie) {
            Movies existingMovie = moviesService.getMovieById(id);
            if (existingMovie == null) {
                return null;  // Puedes manejar esto con un 404 o un mensaje de error
            }

            // Actualizar los campos de la película
            existingMovie.setTitle(updatedMovie.getTitle());
            existingMovie.setDirector(updatedMovie.getDirector());
            existingMovie.setReleaseDate(updatedMovie.getReleaseDate());

            return moviesService.saveMovie(existingMovie);  // Guarda y retorna la película actualizada
        }

        // Eliminar una película
        @DeleteMapping("/delete/{id}")
        public void deleteMovie(@PathVariable Long id) {
            moviesService.deleteMovie(id);  // Elimina la película por ID
        }
    }

