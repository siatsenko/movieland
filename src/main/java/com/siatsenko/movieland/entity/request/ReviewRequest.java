package com.siatsenko.movieland.entity.request;

public class ReviewRequest {
    int movieId;
    private String text;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ReviewRequest{" +
                "movieId=" + movieId +
                ", text='" + text + '\'' +
                '}';
    }
}
