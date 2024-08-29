package com.example.demo.service;

import com.example.demo.model.Branches;
import com.example.demo.repository.BranchesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchesService {

    private final BranchesRepository branchesRepository;

    public BranchesService(BranchesRepository branchesRepository) {
        this.branchesRepository = branchesRepository;
    }

    public List<Branches> getAllBranches() {
        return branchesRepository.findAll();
    }

    public Branches getBranchById(Long id) {
        return branchesRepository.findById(id).orElse(null);
    }

    @Transactional
    public void saveBranch(Branches branch) {
        branchesRepository.save(branch);
    }

    @Transactional
    public void deleteBranch(Long id) {
        branchesRepository.deleteById(id);
    }
}
