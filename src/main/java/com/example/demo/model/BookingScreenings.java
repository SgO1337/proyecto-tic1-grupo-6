package com.example.demo.model;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;

@Entity
public class BookingScreenings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idBookingScreening;
    private long idScreening;
    private LocalDateTime bookingTime;
    private User user;
    private boolean isCancelled;

    @ManyToOne()
    @JoinColumn(name = "idSeats", nullable = false)
    private Seats seats;


    public long getIdBookingScreening() {
        return idBookingScreening;
    }

    public void setIdBookingScreening(long idBookingScreening) {
        this.idBookingScreening = idBookingScreening;
    }

    public long getIdScreening() {
        return idScreening;
    }

    public void setIdScreening(long idScreening) {
        this.idScreening = idScreening;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Seats getSeats() {
        return seats;
    }

    public void setSeats(Seats seats) {
        this.seats = seats;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }
}
