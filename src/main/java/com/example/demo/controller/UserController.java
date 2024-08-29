package com.example.demo.controller;

import com.example.demo.model.Users;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")  // This makes the "/users" path the base for all routes
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // List all users
    @GetMapping
    public String listUsers(Model model) {
        List<Users> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "index"; // Ensure that "index.html" exists and lists the users
    }

    // Show the form to create a new user
    @GetMapping("/create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new Users());  // Add a new user object for form binding
        return "create"; // Ensure that "create.html" is for user creation
    }

    // Save a new user or updated user
    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") Users user) {
        userService.saveUser(user);
        return "redirect:/users"; // Redirect back to the list after saving
    }

    // View a specific user by ID
    @GetMapping("/view/{id}")
    public String viewUser(@PathVariable Long id, Model model) {
        Users user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/users";  // Handle user not found, redirect to the list
        }
        model.addAttribute("user", user);
        return "view"; // Ensure that "view.html" exists to show user details
    }

    // Show the form to update an existing user
    @GetMapping("/update/{id}")
    public String updateUserForm(@PathVariable Long id, Model model) {
        Users user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/users";  // Handle user not found, redirect to the list
        }
        model.addAttribute("user", user);
        return "update"; // Ensure that "update.html" is for editing the user
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute Users user) {
        Users existingUser = userService.getUserById(user.getId());
        if (existingUser == null) {
            return "redirect:/users";  // Handle user not found, redirect to the list
        }

        // Update the existing user's fields
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());

        // Save the updated user
        userService.saveUser(existingUser);

        return "redirect:/users"; // Redirect to the users list after update
    }

    // Delete a user
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users"; // Redirect back to the list after deletion
    }
}
