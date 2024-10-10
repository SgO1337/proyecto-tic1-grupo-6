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


    @PostMapping("/create")
    public ResponseEntity<String> createBookingScreening(@RequestBody BookingScreenings bookingScreenings) {
        bookingScreeningService.saveBookingScreening(bookingScreenings);
        return ResponseEntity.status(HttpStatus.CREATED).body("Booking Screening created successfully.");
    }
}


