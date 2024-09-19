package com.example.demo.repository;

import com.example.demo.model.Food;
import com.example.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
    // Custom queries (if needed) can be added here
}
