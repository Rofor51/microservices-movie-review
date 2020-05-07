package com.example.review.dao;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @Column(name = "userID")
    private String userID;

    @Column(name = "movieID")
    private int movieID;

    @Column(name = "description")
    private String description;

    @Column(name = "points")
    private double points;

    public Review() {

    }

    public Review(String userID, int movieID, String description, double points) {
        this.userID = userID;
        this.movieID = movieID;
        this.description = description;
        this.points = points;
    }

    public String getUserID() {
        return userID;
    }

    public int getMovieID() {
        return movieID;
    }

    public String getDescription() {
        return description;
    }

    public double getPoints() {
        return points;
    }
}
