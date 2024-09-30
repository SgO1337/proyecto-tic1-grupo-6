package com.example.demo.model;
import jakarta.persistence.*;

import java.util.*;

@Entity
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovie;
    private String title;
    private String description;
    private String genre;
    private String duration;
    private String director;
    private String cast;
    private String releaseDate;
    @ElementCollection
    private List<String> LanguagesAvailable;
    private String rating;
    private boolean isAvailable;
    private String distributer;
    private String dimension;
    private String posterV;
    private String posterH;

    @OneToMany(mappedBy = "movie")
    private List<Screenings> screenings;

    public Long getIdMovie() {
        return idMovie;
    }

    public String getPosterV() {
        return posterV;
    }

    public void setPosterV(String posterV) {
        this.posterV = posterV;
    }

    public String getPosterH() {
        return posterH;
    }

    public void setPosterH(String posterH) {
        this.posterH = posterH;
    }

    public void setScreenings(List<Screenings> screenings) {
        this.screenings = screenings;
    }

    public void setIdMovie(Long idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<String> getLanguagesAvailable() {
        return LanguagesAvailable;
    }

    public void setLanguagesAvailable(List<String> languagesAvailable) {
        LanguagesAvailable = languagesAvailable;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getDistributer() {
        return distributer;
    }

    public void setDistributer(String distributer) {
        this.distributer = distributer;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public List<Screenings> getScreenings() {
        return screenings;
    }

    public void setScreenings(Screenings screenings) {
        this.screenings.add(screenings);
    }
}
