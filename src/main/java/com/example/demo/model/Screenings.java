package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    @OneToMany(mappedBy = "screening")
    private List<Seats> seats;
}
