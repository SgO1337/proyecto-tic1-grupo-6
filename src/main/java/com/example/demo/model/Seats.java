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
    //private String seatNumber; //esto lo agregamos despues, es por ejemplo R5C4 //(Row 5, Column 4)
    private boolean isBooked;
    private int seatRow;
    private int seatCol;

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

