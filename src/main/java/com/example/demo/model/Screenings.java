package com.example.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Screenings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idScreening;
    private String language;
    private String date;
    private String time;
    private String subtitles;


    // One Screening has many Seats
    @ManyToOne()
    @JoinColumn(name = "idMovie", nullable = false)
    private Movies movie;
}
