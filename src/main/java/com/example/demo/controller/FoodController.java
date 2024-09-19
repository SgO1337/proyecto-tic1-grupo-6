package com.example.demo.controller;

import com.example.demo.model.Food;
import com.example.demo.service.FoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // Cambio a RestController para respuestas en JSON
@RequestMapping("/api/food")  // Cambia la URL base para empezar con /api
@CrossOrigin(origins = "http://localhost:3000")  // Permitir CORS para tu aplicación React
public class FoodController {

    private final FoodService foodService;

    // Constructor que inyecta el servicio FoodService
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    // Listar todos los alimentos
    @GetMapping
    public String listFood(Model model) {
        List<Food> foodList = foodService.getAllFood();
        model.addAttribute("food", foodList);
        return "food/index"; // Asegúrate de que "food/index.html" existe y lista los alimentos
    }

    // Mostrar el formulario para crear un nuevo alimento
    @GetMapping("/create")
    public String createFoodForm(Model model) {
        model.addAttribute("food", new Food());  // Añade un nuevo objeto Food para el formulario
        return "food/create"; // Asegúrate de que "food/create.html" existe para la creación de alimentos
    }

    // Guardar un nuevo alimento o actualizar uno existente
    @PostMapping("/save")
    public String saveFood(@ModelAttribute("food") Food food) {
        foodService.saveFood(food);
        return "redirect:/food"; // Redirige a la lista de alimentos después de guardar
    }

    // Ver un alimento específico por su ID
    @GetMapping("/view/{id}")
    public String viewFood(@PathVariable Long id, Model model) {
        Food food = foodService.getFoodById(id);
        if (food == null) {
            return "redirect:/food";  // Manejar cuando no se encuentra el alimento, redirigir a la lista
        }
        model.addAttribute("food", food);
        return "food/view"; // Asegúrate de que "food/view.html" existe para mostrar los detalles del alimento
    }

    // Mostrar el formulario para actualizar un alimento existente
    @GetMapping("/update/{id}")
    public String updateFoodForm(@PathVariable Long id, Model model) {
        Food food = foodService.getFoodById(id);
        if (food == null) {
            return "redirect:/food";  // Manejar cuando no se encuentra el alimento, redirigir a la lista
        }
        model.addAttribute("food", food);
        return "food/update"; // Asegúrate de que "food/update.html" existe para editar el alimento
    }

    @PostMapping("/update")
    public String updateFood(@ModelAttribute Food food) {
        Food existingFood = foodService.getFoodById(food.getIdFood());
        if (existingFood == null) {
            return "redirect:/food";  // Manejar cuando no se encuentra el alimento, redirigir a la lista
        }

        // Actualizar los campos del alimento existente
        existingFood.setName(food.getName());
        existingFood.setPrice(food.getPrice());


        // Guardar el alimento actualizado
        foodService.saveFood(existingFood);

        return "redirect:/food"; // Redirigir a la lista de alimentos después de actualizar
    }

    // Eliminar un alimento
    @GetMapping("/delete/{id}")
    public String deleteFood(@PathVariable Long id) {
        foodService.deleteFood(id);
        return "redirect:/food"; // Redirigir a la lista después de eliminar
    }
}
