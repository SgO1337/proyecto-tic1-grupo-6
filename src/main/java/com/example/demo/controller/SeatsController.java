package com.example.demo.controller;

import com.example.demo.model.Seats;
import com.example.demo.service.SeatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // Cambio a RestController para respuestas en JSON
@RequestMapping("/api/seats")  // Cambia la URL base para empezar con /api
//@CrossOrigin(origins = "http://localhost:3000")  // Permitir CORS para tu aplicación React
//ahora que hago un bean desde screenings permitir peticiones del backend??

//seats requiere:
/*
-una funcion para retornar todos los asientos (y sus respectivos estados) para una screening
-una funcion para "ocupar" asientos
-una para desocupar asientos
*/
public class SeatsController {

    @Autowired
    private SeatsService seatService;

    // Get all seats for a screening
    @GetMapping("/all")
    public ResponseEntity<List<Seats>> getAllSeats(@RequestParam Long screeningId) {
        List<Seats> seats = seatService.getAllSeatsByScreeningId(screeningId);
        return ResponseEntity.ok(seats);
    }

    // Book a seat
    @PutMapping("/book")
    public ResponseEntity<String> bookSeat(@RequestParam Long screeningId, @RequestParam Long seatId) {
        boolean isBooked = seatService.bookSeat(screeningId, seatId);
        if (isBooked) {
            return ResponseEntity.ok("Seat booked successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Seat is already booked or not found.");
        }
    }

    @PostMapping("/createSeatsForScreening")
    public ResponseEntity<String> createSeatsForScreening(@RequestParam Long screeningId) {
        seatService.createSeatsForScreening(screeningId);
        return ResponseEntity.ok("Seats created successfully.");
    }

    // Unbook a seat
    @PutMapping("/unbook")
    public ResponseEntity<String> unbookSeat(@RequestParam Long screeningId, @RequestParam Long seatId) {
        boolean isUnbooked = seatService.unbookSeat(screeningId, seatId);
        if (isUnbooked) {
            return ResponseEntity.ok("Seat unbooked successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Seat is already unbooked or not found.");
        }
    }
}


