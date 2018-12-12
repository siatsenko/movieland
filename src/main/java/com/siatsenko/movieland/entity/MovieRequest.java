package com.siatsenko.movieland.entity;

import java.util.Arrays;

public class MovieRequest {
    private String nameRussian;
    private String nameNative;
    private String yearOfRelease;
    private String description;
    private double price;
    private double rating;
    private String picturePath;
    int[] countries;
    int[] genres;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public int[] getCountries() {
        return countries;
    }

    public void setCountries(int[] countries) {
        this.countries = countries;
    }

    public int[] getGenres() {
        return genres;
    }

    public void setGenres(int[] genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "MovieRequest{" +
                "nameRussian='" + nameRussian + '\'' +
                ", nameNative='" + nameNative + '\'' +
                ", yearOfRelease='" + yearOfRelease + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", picturePath='" + picturePath + '\'' +
                ", countries=" + Arrays.toString(countries) +
                ", genres=" + Arrays.toString(genres) +
                '}';
    }
}
