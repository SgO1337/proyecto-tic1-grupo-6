package com.example.demo.repository;

import com.example.demo.model.Movies;
import com.example.demo.model.Screenings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screenings, Long> {

}
