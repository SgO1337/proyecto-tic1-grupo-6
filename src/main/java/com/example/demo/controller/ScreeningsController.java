package com.example.demo.controller;


import com.example.demo.model.Screenings;
import com.example.demo.service.ScreeningService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Cambio a RestController para respuestas en JSON
@RequestMapping("/api/screenings") // Cambia la URL base para empezar con /api
@CrossOrigin(origins = "http://localhost:3000") // Permitir CORS para tu aplicación React
public class ScreeningsController {

    private final ScreeningService screeningService;

    public ScreeningsController(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    // Listar todas las funciones
    @GetMapping
    public List<Screenings> listScreenings() {
        return screeningService.getAllScreenings(); // Retorna una lista de funciones en JSON
    }

    //ver función por ID
    @GetMapping("/view/{id}")
    public Screenings viewScreening(@PathVariable Long id) {
        return screeningService.getScreeningById(id); // Retorna la función como JSON
    }

    // Crear una nueva función
    @PostMapping("/create")
    public Screenings createScreening(@RequestBody Screenings screening) {
        return screeningService.saveScreening(screening); // Crea y retorna la nueva función como JSON
    }

    // Actualizar una función existente
    @PutMapping("/update/{id}")
    public Screenings updateScreening(@PathVariable Long id, @RequestBody Screenings updatedScreening) {
        Screenings existingScreening = screeningService.getScreeningById(id);
        if (existingScreening == null) {
            return null; // Puedes manejar esto con un 404 o un mensaje de error
        }
        existingScreening.setIdScreening(updatedScreening.getIdScreening());
        existingScreening.setDate(updatedScreening.getDate());
        existingScreening.setLanguage(updatedScreening.getLanguage());
        existingScreening.setSubtitles(updatedScreening.getSubtitles());
        existingScreening.setTime(updatedScreening.getTime());
        existingScreening.setMovie(updatedScreening.getMovie());

        return screeningService.saveScreening(existingScreening); // Actualiza y retorna la función actualizada como JSON
    }

    // Eliminar una función
    @DeleteMapping("/delete/{id}")
    public void deleteScreening(@PathVariable Long id) {
        screeningService.deleteScreening(id); // Elimina la función por ID
    }



}
