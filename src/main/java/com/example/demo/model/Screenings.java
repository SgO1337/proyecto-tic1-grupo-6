package com.example.demo.model;

import jakarta.persistence.*;
import com.example.demo.model.Rooms;

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

    @OneToOne // Define the one-to-one relationship with Room
    @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false) // Specify the foreign key column in the screenings table
    private Rooms room; // Add a reference to the Room entity

    public Long getIdScreening() {
        return idScreening;
    }

    public void setIdScreening(Long idScreening) {
        this.idScreening = idScreening;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(String subtitles) {
        this.subtitles = subtitles;
    }

    public Movies getMovie() {
        return movie;
    }

    public void setMovie(Movies movie) {
        this.movie = movie;
    }
}
