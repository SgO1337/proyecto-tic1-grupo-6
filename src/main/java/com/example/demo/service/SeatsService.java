package com.example.demo.service;

import com.example.demo.model.BookingScreenings;
import com.example.demo.model.Seats;
import com.example.demo.model.Screenings;
import com.example.demo.repository.SeatsRepository;
import com.example.demo.repository.ScreeningRepository; // Import for the screening repository
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // Function to return all seats and their states for a given screening
    public List<Seats> getAllSeatsByidScreening(Long idScreening) {
        List<Seats> bookedSeats = seatsRepository.getAllBookedSeatsByidScreening(idScreening);
        List<Seats> unbookedSeats = seatsRepository.getAllUnbookedSeatsByidScreening(idScreening);
        List<Seats> allSeats = new ArrayList<>(bookedSeats);
        allSeats.addAll(unbookedSeats);
        return allSeats;
    }

    public void createAndBookSeat(BookingScreenings bookingScreenings, int seatRow, int seatCol, Long screeningId) {
        Seats seat = new Seats();
        seat.setSeatRow(seatRow);
        seat.setSeatCol(seatCol);
        seat.setScreening(bookingScreenings.getScreening()); // Associate with screening
        seat.setBookingScreening(bookingScreenings); // Associate with BookingScreenings

        seatsRepository.save(seat);
    }

    public Seats findSeatByRowAndCol(int seatRow, int seatCol) {
        // Find the seat using row and column only
        return seatsRepository.findBySeatRowAndSeatCol(seatRow, seatCol);
    }

    // You can also implement the method to find by screening ID if needed
    public Seats findSeatByRowAndColAndScreeningId(int seatRow, int seatCol, Long screeningId) {
        return seatsRepository.findBySeatRowAndSeatColAndScreeningId(seatRow, seatCol, screeningId);
    }

    /*
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
    }*/
}
