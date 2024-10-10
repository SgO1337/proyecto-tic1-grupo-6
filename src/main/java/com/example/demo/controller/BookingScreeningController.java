package com.example.demo.controller;

import com.example.demo.model.BookingScreenings;
import com.example.demo.model.Seats;
import com.example.demo.service.BookingScreeningService;
import com.example.demo.service.SeatsService;
import jakarta.transaction.Transactional;
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

    private final SeatsService seatService;

    public BookingScreeningController(BookingScreeningService bookingScreeningService, SeatsService seatService) {
        this.bookingScreeningService = bookingScreeningService;
        this.seatService = seatService;
    }

    @GetMapping
    public List<BookingScreenings> getBookingScreening() {
        return bookingScreeningService.getAllBookingScreenings();
    }




    @PostMapping("/create")
    @Transactional
    public ResponseEntity<?> createBookingScreening(@RequestBody BookingScreenings bookingScreenings) {
        // Save the booking first
        bookingScreeningService.saveBookingScreening(bookingScreenings);
        int counter = 0;
        List<Seats> seats = bookingScreenings.getSeats();

        if (seats != null && !seats.isEmpty()) {
            for (Seats seat : seats) {
                // Check if the seat is already booked
                Seats existingSeat = seatService.findSeatByRowAndCol(seat.getSeatRow(), seat.getSeatCol());
                if (existingSeat != null && !existingSeat.isBooked()) {
                    // Proceed to book the seat
                    seatService.createAndBookSeat(bookingScreenings, seat.getSeatRow(), seat.getSeatCol(), bookingScreenings.getScreening().getIdScreening());
                    counter++;
                } else {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Seat " + seat.getSeatRow() + "-" + seat.getSeatCol() + " is already booked.");
                }
            }
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Booking Screening created successfully: counter: " + counter);
    }




}


