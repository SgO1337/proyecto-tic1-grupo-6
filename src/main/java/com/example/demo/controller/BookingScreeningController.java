package com.example.demo.controller;

import com.example.demo.model.BookingScreenings;
import com.example.demo.model.Seats;
import com.example.demo.service.BookingScreeningService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking-screening")
public class BookingScreeningController {

    private final BookingScreeningService bookingScreeningService;

    public BookingScreeningController(BookingScreeningService bookingScreeningService) {
        this.bookingScreeningService = bookingScreeningService;
    }

    @GetMapping
    public List<BookingScreenings> getBookingScreening() {
        return bookingScreeningService.getAllBookingScreenings();
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<BookingScreenings> getBookingScreeningById(@PathVariable Long id) {
        BookingScreenings bookingScreening = bookingScreeningService.getBookingScreeningById(id);
        if (bookingScreening == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(bookingScreening);
    }

    // Actualizar una reserva existente
    @PutMapping("/update/{id}")
    public ResponseEntity<BookingScreenings> updateBookingScreening(@PathVariable Long id, @RequestBody BookingScreenings updatedBookingScreenings) {
        BookingScreenings existingBookingScreening = bookingScreeningService.getBookingScreeningById(id);
        if (existingBookingScreening == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Actualizar detalles de la reserva
        existingBookingScreening.setBookingTime(updatedBookingScreenings.getBookingTime());
        existingBookingScreening.setIdBookingScreening(updatedBookingScreenings.getIdBookingScreening());
        existingBookingScreening.setIdScreening(updatedBookingScreenings.getIdScreening());
        existingBookingScreening.setCancelled(updatedBookingScreenings.isCancelled());
        existingBookingScreening.setSeats(updatedBookingScreenings.getSeats());
        existingBookingScreening.setUser(updatedBookingScreenings.getUser());

        bookingScreeningService.saveBookingScreening(existingBookingScreening);
        return ResponseEntity.ok(existingBookingScreening);
    }


    // Eliminar una reserva por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBookingScreening(@PathVariable Long id) {
        BookingScreenings bookingScreening = bookingScreeningService.getBookingScreeningById(id);
        if (bookingScreening == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking Screening not found.");
        }

        bookingScreeningService.deleteBookingScreening(id);
        return ResponseEntity.ok("Booking Screening deleted successfully.");
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBookingScreening(@RequestBody BookingScreenings bookingScreenings) {
        bookingScreeningService.saveBookingScreening(bookingScreenings);
        return ResponseEntity.status(HttpStatus.CREATED).body("Booking Screening created successfully.");
       // return bookingScreeningService.createBookingScreening(bookingScreenings.getIdBookingScreening(), bookingScreenings.getIdScreening(), bookingScreenings.getSeats(), bookingScreenings.getUser(), bookingScreenings.isCancelled(), bookingScreenings.getBookingTime());
    // The verification of the seats is done in the seats controller
    }

    @GetMapping("/seats/{idBookingScreening}")
    public ResponseEntity<List<Seats>> getBookingSeats(@PathVariable Long idBookingScreening) {
        List<Seats> seats = bookingScreeningService.getAllBookingSeats(idBookingScreening);
        if (seats == null || seats.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(seats);
    }


}


