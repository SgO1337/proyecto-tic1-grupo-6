package com.example.demo.controller;

import com.example.demo.model.Users;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // Change to RestController for JSON responses
@RequestMapping("/api/users")  // Update the base URL to start with /api
@CrossOrigin(origins = "http://localhost:3000")  // Allow CORS for your React app
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // List all users
    @GetMapping
    public List<Users> listUsers() {
        return userService.getAllUsers();  // Return a list of users in JSON
    }

    // Create a new user
    @PostMapping("/create")
    public Users createUser(@RequestBody Users user) {
        return userService.saveUser(user);  // Create and return the new user as JSON
    }

    // View a specific user by ID
    @GetMapping("/view/{id}")
    public Users viewUser(@PathVariable Long id) {
        return userService.getUserById(id);  // Return the user as JSON
    }

    // Update an existing user
    @PutMapping("/update/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users updatedUser) {
        Users existingUser = userService.getUserById(id);
        if (existingUser == null) {
            return null;  // You can handle this with a 404 or an error message
        }
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());


        return userService.saveUser(existingUser);  // Save and return the updated user
    }

    // Delete a user
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);  // Delete the user by ID
    }
}
