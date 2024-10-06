package com.example.demo.model;

import jakarta.persistence.*;

import com.example.demo.model.*;

import java.time.LocalDateTime;

@Entity
public class BookingScreenings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBookingScreening;

    private LocalDateTime bookingTime;

    private boolean isCancelled;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idSeats", nullable = false)
    private Seats seats;

    @OneToOne()
    @JoinColumn(name = "idUsers", nullable = false)
    private Users user;

    @OneToOne()
    @JoinColumn(name = "idScreening", nullable = false)
    private Screenings idScreening;

    public long getIdBookingScreening() {
        return idBookingScreening;
    }

    public void setIdBookingScreening(long idBookingScreening) {
        this.idBookingScreening = idBookingScreening;
    }

    public Screenings getIdScreening() {
        return idScreening;
    }

    public void setIdScreening(Screenings idScreening) {
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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }
}
