package com.example.demo.repository;
import com.example.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Users, Long> {

    // Método para buscar un usuario por su email
    Optional<Users> findByEmail(String email);
}
