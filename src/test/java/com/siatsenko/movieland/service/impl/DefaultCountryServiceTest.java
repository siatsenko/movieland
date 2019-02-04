package com.siatsenko.movieland.service.impl;

import com.siatsenko.movieland.entity.common.Country;
import com.siatsenko.movieland.entity.common.Movie;
import com.siatsenko.movieland.service.CountryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultCountryServiceTest {

    private CountryService countryService;

    @Test
    public void enrich() {
        Movie movie = new Movie();
        movie.setId(4);
        Movie enrichedMovie = countryService.enrich(movie);
        List<Country> countries = enrichedMovie.getCountries();

        assertEquals(2, countries.size());

        Country countryFirst = countries.get(0);
        assertEquals(1, countryFirst.getId());
        assertEquals("США", countryFirst.getName());

        Country countrySecond = countries.get(1);
        assertEquals(3, countrySecond.getId());
        assertEquals("Великобритания", countrySecond.getName());
    }

    @Test
    public void getAll() {
        List<Country> countries = countryService.getAll();
        assertEquals(6, countries.size());
    }

    @Test
    public void getByMovieId() {
        List<Country> countries =  countryService.getByMovieId(4);
        assertEquals(2, countries.size());
    }

    @Test
    @DirtiesContext
    public void editByMovieId() {
        countryService.editByMovieId(1, new int[]{3, 4});
        List<Country> countries = countryService.getByMovieId(1);
        assertEquals(2, countries.size());
    }

    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }
}