package com.example.demo.service;

import com.example.demo.model.Food;
import com.example.demo.repository.FoodRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {
    private final FoodRepository FoodRespository;

    public FoodService (FoodRepository foodRepository){this.FoodRespository = foodRepository;}

    public List<Food> getAllFood() {
        return FoodRespository.findAll();
    }
    // get Food by ID
    public Food getFoodById(Long id) {
        return FoodRespository.findById(id).orElse(null);
    }
    // save Food
    @Transactional
    public Food saveFood(Food food) {
        return FoodRespository.save(food);
    }
    // delete food
    @Transactional
    public void deleteFood(Long id) {
        //Check existence of the food before deletion
        Optional<Food> food = FoodRespository.findById(id);
        food.ifPresent(m -> FoodRespository.deleteById(id));
    }

}


