package com.example.demo.controller;

import com.example.demo.model.Branches;
import com.example.demo.service.BranchesService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // Cambio a RestController para respuestas en JSON
@RequestMapping("/api/branches")  // Cambia la URL base para empezar con /api
//@CrossOrigin(origins = "http://localhost:3000")  // Permitir CORS para tu aplicación React
public class BranchesController {

    private final BranchesService branchesService;

    public BranchesController(BranchesService branchService) {
        this.branchesService = branchService;
    }

    @GetMapping
    public ResponseEntity<List<Branches>> listBranches() {
        List<Branches> branches = branchesService.getAllBranches();
        return ResponseEntity.ok(branches); // Return the list of branches as JSON
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<?> viewBranch(@PathVariable Long id) {
        Branches branch = branchesService.getBranchById(id);
        if (branch == null) {
            return ResponseEntity.status(404).body("Branch not found");
        }
        return ResponseEntity.ok(branch); // Return the branch details as JSON
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody BranchCreationRequest request) throws Exception {
        branchesService.createBranch(request.getLocation());
        return ResponseEntity.ok("test");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBranch(@PathVariable Long id, @RequestBody Branches updatedBranch) {
        // Fetch the existing branch
        Branches branch = branchesService.getBranchById(id);
        if (branch == null) {
            return ResponseEntity.status(400).body("Branch not found");
        }

        // Update the branch details
        branch.setLocation(updatedBranch.getLocation());

        // Save the updated branch
        branchesService.saveBranch(branch);

        return ResponseEntity.ok("Branch updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBranch(@PathVariable Long id) {
        // Fetch the existing branch
        if (branchesService.getBranchById(id) == null) {
            return ResponseEntity.status(400).body("Branch not found");
        }

        branchesService.deleteBranch(id);

        return ResponseEntity.ok("Branch deleted successfully");
    }
}

class BranchCreationRequest {
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
