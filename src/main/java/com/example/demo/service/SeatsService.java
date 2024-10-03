package com.example.demo.service;

import com.example.demo.model.Seats;
import com.example.demo.repository.SeatsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.Screenings;
import com.example.demo.repository.ScreeningRepository; //esto se puede? preguntarle a rolo (estamos ens eats usando un repo de scr)

@Service
public class SeatsService {

    @Autowired
    private SeatsRepository seatsRepository;
    private ScreeningRepository ScreeningRepository; // Inject the repository instance


    @Transactional
    public void createSeatsForScreening(Long screeningId) {
        Optional<Screenings> screeningOpt = ScreeningRepository.findById(screeningId); // You need access to Screenings
        if (screeningOpt.isPresent()) {
            Screenings screening = screeningOpt.get();

            for (int row = 1; row <= 15; row++) {
                for (int col = 1; col <= 10; col++) {
                    Seats seat = new Seats();
                    //seat.setSeatNumber("R" + row + "C" + col);
                    seat.setBooked(false);
                    seat.setScreening(screening);
                    seatsRepository.save(seat);
                }
            }
        }
    }

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
