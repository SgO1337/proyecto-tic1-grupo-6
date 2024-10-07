package com.example.demo.controller;

import com.example.demo.model.Screenings;
import com.example.demo.service.ScreeningService;
import com.example.demo.service.SeatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/screenings")
//@CrossOrigin(origins = "http://localhost:3000") //uncomment when going into prod
public class ScreeningsController {

    @Autowired
    private final ScreeningService screeningService;
    @Autowired
    private final SeatsService seatService;

    @Autowired
    public ScreeningsController(ScreeningService screeningService, SeatsService seatService) {
        this.screeningService = screeningService;
        this.seatService = seatService;
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

    @PostMapping("/create")
    public ResponseEntity<?> createScreening(@RequestBody Screenings screenings) {
        // Validate that both the requested Room and Movie exist in the database
        if (screenings.getRoom() == null || screenings.getRoom().getId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The requested room wasn't found.");
        }

        if (screenings.getMovie() == null || screenings.getMovie().getIdMovie() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The requested movie wasn't found.");
        }


        Screenings savedScreening = screeningService.saveScreening(screenings);

        seatService.createSeatsForScreening(savedScreening.getIdScreening());//Create seats for this screening by calling the service directly

        return ResponseEntity.status(HttpStatus.CREATED).body(savedScreening);
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
