package com.siatsenko.movieland.web.dto;

import com.siatsenko.movieland.entity.Country;
import com.siatsenko.movieland.entity.Genre;
import com.siatsenko.movieland.entity.Movie;
import com.siatsenko.movieland.entity.Review;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class MovieDetailDto {
    private int id;
    private String nameRussian;
    private String nameNative;
    private String yearOfRelease;
    private String description;
    private double rating;
    private double price;
    private String picturePath;
    List<CountryDto> countries;
    List<GenreDto> genres;
    List<ReviewDto> reviews;

    public MovieDetailDto(Movie movie) {
        this.id = movie.getId();
        this.nameRussian = movie.getNameRussian();
        this.nameNative = movie.getNameNative();
        this.yearOfRelease = movie.getYearOfRelease();
        this.description = movie.getDescription();
        this.rating = movie.getRating();
        this.price = new BigDecimal(movie.getPrice()).setScale(2, RoundingMode.UP).doubleValue();
        this.picturePath = movie.getPicturePath();

        List<CountryDto> countryDtos = new ArrayList<>();
        for (Country country : movie.getCountries()) {
            CountryDto countryDto = new CountryDto(country);
            countryDtos.add(countryDto);
        }
        this.countries = countryDtos;

        List<GenreDto> genreDtos = new ArrayList<>();
        for (Genre genre : movie.getGenres()) {
            GenreDto genreDto = new GenreDto(genre);
            genreDtos.add(genreDto);
        }
        this.genres = genreDtos;

        List<ReviewDto> reviewDtos = new ArrayList<>();
        for (Review review : movie.getReviews()) {
            ReviewDto reviewDto = new ReviewDto(review);
            reviewDtos.add(reviewDto);
        }
        this.reviews = reviewDtos;

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

    public List<CountryDto> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryDto> countries) {
        this.countries = countries;
    }

    public List<GenreDto> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDto> genres) {
        this.genres = genres;
    }

    public List<ReviewDto> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDto> reviews) {
        this.reviews = reviews;
    }
}
