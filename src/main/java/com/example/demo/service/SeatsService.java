package com.example.demo.service;

import com.example.demo.model.Seats;
import com.example.demo.repository.SeatsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeatsService {

    @Autowired
    private SeatsRepository seatsRepository;

    // Function to return all seats and their states for a given screening
    public List<Seats> getAllSeatsByScreeningId(Long screeningId) {
        // Assuming you want to return both booked and unbooked seats
        List<Seats> bookedSeats = seatsRepository.getAllBookedSeatsByScreeningId(screeningId);
        List<Seats> unbookedSeats = seatsRepository.getAllUnbookedSeatsByScreeningId(screeningId);
        List<Seats> allSeats = new ArrayList<>(bookedSeats);
        allSeats.addAll(unbookedSeats);
        return allSeats;
    }

    // Function to book (occupy) a seat
    public boolean bookSeat(Long screeningId, Long seatId) {
        Optional<Seats> seatOptional = seatsRepository.findSeatByScreeningIdAndSeatId(screeningId, seatId);
        if (seatOptional.isPresent()) {
            Seats seat = seatOptional.get();
            if (!seat.isBooked()) {
                seat.setBooked(true); // Mark the seat as booked
                seatsRepository.save(seat); // Save the updated seat state
                return true; // Booking successful
            } else {
                return false; // Seat is already booked
            }
        }
        return false; // Seat not found
    }

    // Function to unbook (free up) a seat
    public boolean unbookSeat(Long screeningId, Long seatId) {
        Optional<Seats> seatOptional = seatsRepository.findSeatByScreeningIdAndSeatId(screeningId, seatId);
        if (seatOptional.isPresent()) {
            Seats seat = seatOptional.get();
            if (seat.isBooked()) {
                seat.setBooked(false); // Mark the seat as unbooked
                seatsRepository.save(seat); // Save the updated seat state
                return true; // Unbooking successful
            } else {
                return false; // Seat is already unbooked
            }
        }
        return false; // Seat not found
    }
}
