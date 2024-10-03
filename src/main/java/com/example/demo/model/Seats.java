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
    private Long idSeat;
    private String seatNumber;
    private boolean isBooked;

    // Establishing the many-to-one relationship with Screening
    @ManyToOne
    @JoinColumn(name = "idScreening", nullable = false)
    private Screenings screening;

    public boolean isBooked() {
        return this.isBooked;
    }

    public void setBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    public Screenings getScreening() {
        return screening;
    }

    // This method sets the Screenings entity directly
    public void setScreening(Screenings screening) {
        this.screening = screening;
    }
}
