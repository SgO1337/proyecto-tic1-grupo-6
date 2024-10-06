package com.example.demo.controller;

import com.example.demo.repository.AuthRepository;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "http://localhost:3000")

public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthRepository authRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) throws Exception {
        boolean isAuthenticated = authService.authenticate(request.getEmail(), request.getPassword());

        if (isAuthenticated) {
            return ResponseEntity.ok("Login exitoso.");
        } else {
            return ResponseEntity.status(401).body("Email o contraseña incorrectos.");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) throws Exception {
        if(authRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.status(400).body("El email ingresado ya ha sido registrado.");
        }
        authService.registerUser(request.getEmail(), request.getPassword(), request.getName(),  request.getCI(), request.getAge(), request.getSurname());
        return ResponseEntity.ok("Usuario registrado exitosamente.");
    }
}

class LoginRequest {
    private String email;
    private String password;

    // Getters y setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class RegisterRequest {
    private String email;
    private String password;
    private Integer CI;
    private Integer age;
    private String surname;
    private String name;

    // Getters y setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCI() {
        return CI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCI(Integer CI) {
        this.CI = CI;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}


