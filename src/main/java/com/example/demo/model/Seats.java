package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Seats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSeat; //esto esta bien? o como hacemos
    private String seatNumber;
    private boolean isBooked;

    // Establishing the many-to-one relationship with Screening
    @ManyToOne
    @JoinColumn(name = "screening_id", nullable = false)
    private Screenings screening;

    public boolean isBooked() {
        return this.isBooked;
    }

    public void setBooked(boolean b) {
        this.isBooked = b;
    }
}
