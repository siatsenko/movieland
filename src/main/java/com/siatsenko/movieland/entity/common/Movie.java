package com.siatsenko.movieland.entity.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Movie {
    private int id;
    private String nameRussian;
    private String nameNative;
    private String yearOfRelease;
    private String description;
    private double rating;
    private double price;
    private String picturePath;
    List<Country> countries;
    List<Genre> genres;
    List<Review> reviews;

    public Movie(){
    }

    public Movie(Movie movie) {
        this.id = movie.id;
        this.nameRussian=movie.nameRussian;
        this.nameNative=movie.nameNative;
        this.yearOfRelease=movie.yearOfRelease;
        this.description=movie.description;
        this.rating=movie.rating;
        this.price=movie.price;
        this.picturePath=movie.picturePath;
        this.countries  = (movie.getCountries() == null)? new ArrayList<>():movie.getCountries();
        this.genres = (movie.getGenres() == null)? new ArrayList<>():movie.getGenres();
        this.reviews = (movie.getReviews() == null)? new ArrayList<>():movie.getReviews();
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

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = (countries == null)? new ArrayList<>():countries;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = (genres == null)? new ArrayList<>():genres;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = (reviews == null)? new ArrayList<>():reviews;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", nameRussian='" + nameRussian + '\'' +
                ", nameNative='" + nameNative + '\'' +
                ", yearOfRelease='" + yearOfRelease + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                ", picturePath='" + picturePath + '\'' +
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
        return id == movie.id &&
                Double.compare(movie.rating, rating) == 0 &&
                Double.compare(movie.price, price) == 0 &&
                Objects.equals(nameRussian, movie.nameRussian) &&
                Objects.equals(nameNative, movie.nameNative) &&
                Objects.equals(yearOfRelease, movie.yearOfRelease) &&
                Objects.equals(description, movie.description) &&
                Objects.equals(picturePath, movie.picturePath) &&
                Objects.equals(countries, movie.countries) &&
                Objects.equals(genres, movie.genres) &&
                Objects.equals(reviews, movie.reviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameRussian, nameNative, yearOfRelease, description, rating, price, picturePath, countries, genres, reviews);
    }
}
