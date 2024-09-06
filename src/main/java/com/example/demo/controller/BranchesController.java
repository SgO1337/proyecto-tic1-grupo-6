package com.example.demo.controller;

import com.example.demo.model.Branches;
import com.example.demo.service.BranchesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/branches")  // This makes the "/branches" path the base for all routes
public class BranchesController {

    private final BranchesService branchesService;

    public BranchesController(BranchesService branchService) {
        this.branchesService = branchService;
    }

    // List all branches
    @GetMapping
    public String listBranches(Model model) {
        List<Branches> branches = branchesService.getAllBranches();
        model.addAttribute("branches", branches);
        return "branch/index"; // Ensure that "branch/home.html" exists and lists the branches
    }

    // Show the form to create a new branch
    @GetMapping("/create")
    public String createBranchForm(Model model) {
        model.addAttribute("branch", new Branches());  // Add a new branch object for form binding
        return "branch/create"; // Ensure that "branch/create.html" is for branch creation
    }

    // Save a new branch or updated branch
    @PostMapping("/save")
    public String saveBranch(@ModelAttribute("branch") Branches branch) {
        branchesService.saveBranch(branch);
        return "redirect:/branches"; // Redirect back to the list after saving
    }

    // View a specific branch by ID
    @GetMapping("/view/{id}")
    public String viewBranch(@PathVariable Long id, Model model) {
        Branches branch = branchesService.getBranchById(id);
        if (branch == null) {
            return "redirect:/branches";  // Handle branch not found, redirect to the list
        }
        model.addAttribute("branch", branch);
        return "branch/view"; // Ensure that "branch/view.html" exists to show branch details
    }

    // Show the form to update an existing branch
    @GetMapping("/update/{id}")
    public String updateBranchForm(@PathVariable Long id, Model model) {
        Branches branch = branchesService.getBranchById(id);
        if (branch == null) {
            return "redirect:/branches";  // Handle branch not found, redirect to the list
        }
        model.addAttribute("branch", branch);
        return "branch/update"; // Ensure that "branch/update.html" is for editing the branch
    }

    @PostMapping("/update")
    public String updateBranch(@ModelAttribute Branches branch) {
        Branches existingBranch = branchesService.getBranchById(branch.getIdBranch());
        if (existingBranch == null) {
            return "redirect:/branches";  // Handle branch not found, redirect to the list
        }

        // Update the existing branch's fields
        existingBranch.setLocation(branch.getLocation());

        // Save the updated branch
        branchesService.saveBranch(existingBranch);

        return "redirect:/branches"; // Redirect to the branches list after update
    }

    // Delete a branch
    @GetMapping("/delete/{id}")
    public String deleteBranch(@PathVariable Long id) {
        branchesService.deleteBranch(id);
        return "redirect:/branches"; // Redirect back to the list after deletion
    }
}
