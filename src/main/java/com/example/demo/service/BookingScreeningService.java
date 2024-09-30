package com.example.demo.service;


import com.example.demo.model.BookingScreenings;
import com.example.demo.model.Seats;
import com.example.demo.repository.BookingScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingScreeningService {

    @Autowired
    private final BookingScreeningRepository bookingScreeningRepository;

    public BookingScreeningService(BookingScreeningRepository bookingScreeningRepository) {
        this.bookingScreeningRepository = bookingScreeningRepository;
    }

    public BookingScreenings saveBookingScreening(BookingScreenings bookingScreenings){
        return  bookingScreeningRepository.save(bookingScreenings);

    }

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


}
