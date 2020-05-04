package com.io.movies.movieexample.Dao;

public class UpdateMovieResource {
    private int movieID;
    private double points;

    public UpdateMovieResource(int movieID, double points) {
        this.movieID = movieID;
        this.points = points;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }
}
