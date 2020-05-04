package com.io.movies.movieexample.Dao;

public class RemoveFromList {
    private String userID;
    private int movieID;

    public RemoveFromList(String userID, int movieID) {
        this.userID = userID;
        this.movieID = movieID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }
}
