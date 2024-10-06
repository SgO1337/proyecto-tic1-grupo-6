package com.example.demo.service;


import com.example.demo.model.BookingScreenings;
import com.example.demo.model.Screenings;
import com.example.demo.model.Seats;
import com.example.demo.model.Users;
import com.example.demo.repository.BookingScreeningRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingScreeningService {


    private final BookingScreeningRepository bookingScreeningRepository;

    public BookingScreeningService(BookingScreeningRepository bookingScreeningRepository) {
        this.bookingScreeningRepository = bookingScreeningRepository;
    }
@Transactional
    public BookingScreenings saveBookingScreening(BookingScreenings bookingScreenings){
        return  bookingScreeningRepository.save(bookingScreenings);

    }
@Transactional
    public void deleteBookingScreening(Long id){
        bookingScreeningRepository.deleteById(id);
    }

    public BookingScreenings getBookingScreeningById(Long id){
        return bookingScreeningRepository.findById(id).orElse(null);
    }

    public List<BookingScreenings> getAllBookingScreenings(){
        return bookingScreeningRepository.findAll();
    }


    public List<Seats> getAllBookingSeats(Long idBookingScreening){
        return bookingScreeningRepository.findAllBookingSeats(idBookingScreening);
    }

    public BookingScreenings createBookingScreening(long idBookingScreening, Screenings idScreening, Seats idSeats, Users idUsers, boolean isCancelled, LocalDateTime bookingTime){
//        if ( idScreening == null || idSeats == null || idUsers == null || bookingTime == null)
//            throw new IllegalArgumentException("All fields are required");

        BookingScreenings newBookingScreening = new BookingScreenings();
        newBookingScreening.setIdBookingScreening(idBookingScreening);
        newBookingScreening.setIdScreening(idScreening);
        newBookingScreening.setSeats(idSeats);
        newBookingScreening.setUser(idUsers);
        newBookingScreening.setCancelled(isCancelled);
        newBookingScreening.setBookingTime(bookingTime);


        bookingScreeningRepository.save(newBookingScreening);
        return newBookingScreening;
    }


}
