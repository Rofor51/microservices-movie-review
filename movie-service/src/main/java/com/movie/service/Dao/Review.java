package com.movie.service.Dao;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "review")
public class Review implements Serializable {
    @Id
    @Column(name = "movieID")
    private int movieID;

    @Column(name = "userID")
    private int userID;


    private String description;
    private double points;

    public Review(int movieID, int userID, String description, double points) {
        this.movieID = movieID;
        this.userID = userID;
        this.description = description;
        this.points = points;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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
