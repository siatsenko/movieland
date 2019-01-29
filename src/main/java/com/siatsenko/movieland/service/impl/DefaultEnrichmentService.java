package com.siatsenko.movieland.service.impl;

import com.siatsenko.movieland.entity.common.Movie;
import com.siatsenko.movieland.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
//@Primary
public class DefaultEnrichmentService implements EnrichmentService {
    private GenreService genreService;
    private CountryService countryService;
    private ReviewService reviewService;

    @Override
    public Movie enrich(Movie movie) {
        genreService.enrich(movie);
        countryService.enrich(movie);
        reviewService.enrich(movie);
        return movie;
    }

    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }

    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    @Autowired
    public void setReviewService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

}
