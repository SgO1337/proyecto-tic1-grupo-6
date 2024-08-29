package com.example.demo.repository;

import com.example.demo.model.Branches;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchesRepository extends JpaRepository<Branches, Long> {
    // Custom queries (if needed) can be added here
}
