package com.example.demo.repository;

import com.example.demo.model.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomsRepository extends JpaRepository<Rooms, Long> {
    // Custom queries (if needed) can be added here
}
