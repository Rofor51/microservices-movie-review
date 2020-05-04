package com.io.movies.movieexample.Dao;

import javax.persistence.*;
import java.io.Serializable;


public class Movie implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private int movieID;

    private String name;

    private String description;

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
