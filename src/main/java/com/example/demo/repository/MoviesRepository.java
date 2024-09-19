package com.example.demo.repository;
import com.example.demo.model.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MoviesRepository extends JpaRepository<Movies, Long> {

    @Query("SELECT m FROM Movies m WHERE m.isAvailable = true")
    List<Movies> getAllAvailableMovies();

}
