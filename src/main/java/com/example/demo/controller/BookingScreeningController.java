package com.example.demo.controller;

import com.example.demo.model.BookingScreenings;
import com.example.demo.model.Seats;
import com.example.demo.service.BookingScreeningService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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
    public BookingScreenings getBookingScreeningById(@PathVariable Long id) {
        return bookingScreeningService.getBookingScreeningById(id);
    }

    @PutMapping("/update/{id}")
    public BookingScreenings updateBookingScreening(@PathVariable Long id, @RequestBody BookingScreenings UpdatedbookingScreenings) {
        BookingScreenings existingBookingScreening = bookingScreeningService.getBookingScreeningById(id);
        if (existingBookingScreening != null) {
            existingBookingScreening.setBookingTime(UpdatedbookingScreenings.getBookingTime());
            existingBookingScreening.setIdBookingScreening(UpdatedbookingScreenings.getIdBookingScreening());
            existingBookingScreening.setIdScreening(UpdatedbookingScreenings.getIdScreening());
            existingBookingScreening.setCancelled(UpdatedbookingScreenings.isCancelled());
            existingBookingScreening.setSeats(UpdatedbookingScreenings.getSeats());
            existingBookingScreening.setUser(UpdatedbookingScreenings.getUser());
            return bookingScreeningService.saveBookingScreening(existingBookingScreening);
        } else {
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBookingScreening(@PathVariable Long id) {
        bookingScreeningService.deleteBookingScreening(id);
    }

    @PostMapping("/create")
    public BookingScreenings createBookingScreening(@RequestBody BookingScreenings bookingScreenings) {
        return bookingScreeningService.saveBookingScreening(bookingScreenings);
    // The verification of the seats is done in the seats controller
    }

    @GetMapping("/seats")
    public List<Seats> getBookingSeats(@PathVariable Long idBookingScreening) {
        return bookingScreeningService.getAllBookingSeats(idBookingScreening);
    }


}


