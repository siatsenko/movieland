package com.siatsenko.movieland.web.dto;

import com.siatsenko.movieland.entity.Movie;

public class MovieDto {
    private int id;
    private String nameRussian;
    private String nameNative;
    private String yearOfRelease;
    private double rating;
    private double price;
    private String picturePath;

    public MovieDto(Movie movie) {
        this.id = movie.getId();
        this.nameRussian = movie.getNameRussian();
        this.nameNative = movie.getNameNative();
        this.yearOfRelease = movie.getYearOfRelease();
        this.rating = movie.getRating();
        this.price = movie.getPrice();
        this.picturePath = movie.getPicturePath();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameRussian() {
        return nameRussian;
    }

    public void setNameRussian(String nameRussian) {
        this.nameRussian = nameRussian;
    }

    public String getNameNative() {
        return nameNative;
    }

    public void setNameNative(String nameNative) {
        this.nameNative = nameNative;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
