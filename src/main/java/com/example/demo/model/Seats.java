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
    private int seatRow; // Renamed from `row` to `seatRow`
    private int seatCol; // Renamed from `row` to `seatRow`
    // Establishing the many-to-one relationship with Screening
    @ManyToOne
    @JoinColumn(name = "idScreening", nullable = false)
    private Screenings screening;

    //getters y  setters

    public Long getIdSeat() {
        return idSeat;
    }

    public void setIdSeat(Long idSeat) {
        this.idSeat = idSeat;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatCol() {
        return seatCol;
    }

    public void setSeatCol(int seatCol) {
        this.seatCol = seatCol;
    }

    public Screenings getScreening() {
        return screening;
    }

    public void setScreening(Screenings screening) {
        this.screening = screening;
    }
}

