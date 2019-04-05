package com.siatsenko.movieland.entity.common;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Movie {
    @Value("@{debug.cache.movieWeight:1}")
    private int bufferWeight;

    private byte[] buffer = new byte[bufferWeight];
    private int id;
    private String nameRussian;
    private String nameNative;
    private String yearOfRelease;
    private String description;
    private double rating;
    private double price;
    private String picturePath;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
    List<Country> countries;
    List<Genre> genres;
    List<Review> reviews;

    public Movie() {
    }

    public Movie(Movie movie) {
        this.id = movie.id;
        this.nameRussian = movie.nameRussian;
        this.nameNative = movie.nameNative;
        this.yearOfRelease = movie.yearOfRelease;
        this.description = movie.description;
        this.rating = movie.rating;
        this.price = movie.price;
        this.picturePath = movie.picturePath;
        this.createdDate = movie.createdDate;
        this.modifiedDate = movie.modifiedDate;
        this.countries = movie.getCountries(); //(movie.getCountries() == null)? new ArrayList<>():movie.getCountries();
        this.genres = movie.getGenres(); //(movie.getGenres() == null)? new ArrayList<>():movie.getGenres();
        this.reviews = movie.getReviews(); //(movie.getReviews() == null)? new ArrayList<>():movie.getReviews();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "bufferWeight=" + bufferWeight +
                ", buffer=" + Arrays.toString(buffer) +
                ", id=" + id +
                ", nameRussian='" + nameRussian + '\'' +
                ", nameNative='" + nameNative + '\'' +
                ", yearOfRelease='" + yearOfRelease + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                ", picturePath='" + picturePath + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", countries=" + countries +
                ", genres=" + genres +
                ", reviews=" + reviews +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return bufferWeight == movie.bufferWeight &&
                id == movie.id &&
                Double.compare(movie.rating, rating) == 0 &&
                Double.compare(movie.price, price) == 0 &&
                Arrays.equals(buffer, movie.buffer) &&
                Objects.equals(nameRussian, movie.nameRussian) &&
                Objects.equals(nameNative, movie.nameNative) &&
                Objects.equals(yearOfRelease, movie.yearOfRelease) &&
                Objects.equals(description, movie.description) &&
                Objects.equals(picturePath, movie.picturePath) &&
                Objects.equals(createdDate, movie.createdDate) &&
                Objects.equals(modifiedDate, movie.modifiedDate) &&
                Objects.equals(countries, movie.countries) &&
                Objects.equals(genres, movie.genres) &&
                Objects.equals(reviews, movie.reviews);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(bufferWeight, id, nameRussian, nameNative, yearOfRelease, description, rating, price, picturePath, createdDate, modifiedDate, countries, genres, reviews);
        result = 31 * result + Arrays.hashCode(buffer);
        return result;
    }
}
