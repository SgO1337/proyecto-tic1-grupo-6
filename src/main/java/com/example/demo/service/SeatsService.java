package com.example.demo.service;

import com.example.demo.model.Seats;
import com.example.demo.model.Screenings;
import com.example.demo.repository.SeatsRepository;
import com.example.demo.repository.ScreeningRepository; // Import for the screening repository
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

    @Autowired
    private ScreeningRepository screeningRepository; // Inject the repository instance

    @Transactional
    public void createSeatsForScreening(Long idScreening) {
        Optional<Screenings> screeningOpt = screeningRepository.findById(idScreening); // Correctly use screeningRepository
        if (screeningOpt.isPresent()) {
            Screenings screening = screeningOpt.get();

            for (int row = 1; row <= 15; row++) {
                for (int col = 1; col <= 10; col++) {
                    Seats seat = new Seats();
                    seat.setBooked(false);
                    seat.setSeatRow(row);
                    seat.setSeatCol(col);
                    seat.setScreening(screening);
                    seatsRepository.save(seat);
                }
            }
        }
    }

    // Function to return all seats and their states for a given screening
    public List<Seats> getAllSeatsByidScreening(Long idScreening) {
        List<Seats> bookedSeats = seatsRepository.getAllBookedSeatsByidScreening(idScreening);
        List<Seats> unbookedSeats = seatsRepository.getAllUnbookedSeatsByidScreening(idScreening);
        List<Seats> allSeats = new ArrayList<>(bookedSeats);
        allSeats.addAll(unbookedSeats);
        return allSeats;
    }

    // Function to book (occupy) a seat
    public boolean bookSeat(Long idScreening, Long seatId) {
        Optional<Seats> seatOptional = seatsRepository.findSeatByidScreeningAndSeatId(idScreening, seatId);
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
    public boolean unbookSeat(Long idScreening, Long seatId) {
        Optional<Seats> seatOptional = seatsRepository.findSeatByidScreeningAndSeatId(idScreening, seatId);
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

    public Screenings getScreeningById(Long idScreening) {
        return screeningRepository.findById(idScreening).orElse(null); // Use screeningRepository to find screening
    }
}
