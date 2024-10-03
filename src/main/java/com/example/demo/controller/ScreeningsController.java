package com.example.demo.controller;

import com.example.demo.model.Screenings;
import com.example.demo.service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/screenings")
//@CrossOrigin(origins = "http://localhost:3000")
public class ScreeningsController {

    private final ScreeningService screeningService;
    private final RestTemplate restTemplate;

    // Inject ScreeningService and RestTemplate
    @Autowired
    public ScreeningsController(ScreeningService screeningService, RestTemplate restTemplate) {
        this.screeningService = screeningService;
        this.restTemplate = restTemplate;
    }

    // List all screenings
    @GetMapping
    public List<Screenings> listScreenings() {
        return screeningService.getAllScreenings();
    }

    // View screening by ID
    @GetMapping("/view/{id}")
    public Screenings viewScreening(@PathVariable Long id) {
        return screeningService.getScreeningById(id);
    }

    // Create a new screening and trigger seat creation
    @PostMapping("/create")
    public Screenings createScreening(@RequestBody Screenings screening) {
        // Save the screening first
        Screenings savedScreening = screeningService.saveScreening(screening);

        // Create 150 seats for this screening by calling SeatsController
        String seatCreationUrl = "http://localhost:8080/api/seats/create?screeningId=" + savedScreening.getIdScreening();
        restTemplate.postForObject(seatCreationUrl, null, String.class); // Call the endpoint to create seats

        return savedScreening;
    }

    // Update an existing screening
    @PutMapping("/update/{id}")
    public Screenings updateScreening(@PathVariable Long id, @RequestBody Screenings updatedScreening) {
        Screenings existingScreening = screeningService.getScreeningById(id);
        if (existingScreening == null) {
            return null; // You can handle this with a 404 error or a custom message
        }
        existingScreening.setIdScreening(updatedScreening.getIdScreening());
        existingScreening.setDate(updatedScreening.getDate());
        existingScreening.setLanguage(updatedScreening.getLanguage());
        existingScreening.setSubtitles(updatedScreening.getSubtitles());
        existingScreening.setTime(updatedScreening.getTime());
        existingScreening.setMovie(updatedScreening.getMovie());

        return screeningService.saveScreening(existingScreening);
    }

    // Delete a screening
    @DeleteMapping("/delete/{id}")
    public void deleteScreening(@PathVariable Long id) {
        screeningService.deleteScreening(id);
    }
}
