package com.movie.service.Dao;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "movie")
public class Movie implements Serializable {
    @Id
    @Column(name = "movieID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int movieID;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "points")
    private double points;


    public Movie() {

    }

    public Movie(String name, String description) {
        this.name = name;
        this.description = description;

    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }
}
