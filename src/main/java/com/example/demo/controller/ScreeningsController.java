package com.example.demo.controller;

import com.example.demo.model.Screenings;
import com.example.demo.service.ScreeningService;
import com.example.demo.service.SeatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/screenings")
//@CrossOrigin(origins = "http://localhost:3000") // Uncomment if needed
public class ScreeningsController {

    @Autowired
    private final ScreeningService screeningService;
    @Autowired
    private final SeatsService seatService;
    //private final RestTemplate restTemplate;

    // Inject ScreeningService and RestTemplate
    @Autowired
    public ScreeningsController(ScreeningService screeningService, SeatsService seatService) {
        this.screeningService = screeningService;
        this.seatService = seatService;
        //this.restTemplate = restTemplate;
    }

    // List all screenings
    @GetMapping
    public ResponseEntity<List<Screenings>> listScreenings() {
        List<Screenings> screenings = screeningService.getAllScreenings();
        return ResponseEntity.ok(screenings);
    }

    // View screening by ID
    @GetMapping("/view/{id}")
    public ResponseEntity<?> viewScreening(@PathVariable Long id) {
        Screenings screening = screeningService.getScreeningById(id);
        if (screening == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Screening not found.");
        }
        return ResponseEntity.ok(screening);
    }

    // Create a new screening and trigger seat creation
    @PostMapping("/create")
    public ResponseEntity<String> createScreening(@RequestBody Screenings screening) {
        // Check if the movie is available
        screening.getMovie().setAvailable(true);
        if (!screening.getMovie().isAvailable()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Movie is not available.");
        }


        // Save the screening first
        Screenings savedScreening = screeningService.saveScreening(screening);

        // Create seats for this screening by calling the service directly
        seatService.createSeatsForScreening(savedScreening.getIdScreening());

        return ResponseEntity.status(HttpStatus.CREATED).body("Screening created successfully.");
    }


    // Update an existing screening
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateScreening(@PathVariable Long id, @RequestBody Screenings updatedScreening) {
        Screenings existingScreening = screeningService.getScreeningById(id);
        if (existingScreening == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Screening not found.");
        }

        // Update screening details
        existingScreening.setDate(updatedScreening.getDate());
        existingScreening.setLanguage(updatedScreening.getLanguage());
        existingScreening.setSubtitles(updatedScreening.getSubtitles());
        existingScreening.setTime(updatedScreening.getTime());
        existingScreening.setMovie(updatedScreening.getMovie());

        Screenings updated = screeningService.saveScreening(existingScreening);
        return ResponseEntity.ok(updated);
    }

    // Delete a screening
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteScreening(@PathVariable Long id) {
        Screenings screening = screeningService.getScreeningById(id);
        if (screening == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Screening not found.");
        }

        screeningService.deleteScreening(id);
        return ResponseEntity.ok("Screening deleted successfully.");
    }
}
