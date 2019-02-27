package com.siatsenko.movieland.service.impl;

import com.siatsenko.movieland.entity.common.Country;
import com.siatsenko.movieland.entity.common.Genre;
import com.siatsenko.movieland.entity.common.Movie;
import com.siatsenko.movieland.entity.common.Review;
import com.siatsenko.movieland.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
@Primary
public class ParallelEnrichmentService implements EnrichmentService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private GenreService genreService;
    private CountryService countryService;
    private ReviewService reviewService;


    private ExecutorService executorService;
    private SlowService slowService;

    @Value("${parallelEnrichment.timeout:5000}")
    private long timeoutMillis;

    @Override
    public Movie enrich(Movie movie) {

        int movieId = movie.getId();
        List<Runnable> tasks = Arrays.asList(
                () -> {
                    List<Country> countries = countryService.getByMovieId(movieId);
                    if (!Thread.currentThread().isInterrupted()) {
                        movie.setCountries(countries);
                    }
                }
                , () -> {
                    List<Genre> genres = genreService.getByMovieId(movieId);
                    if (!Thread.currentThread().isInterrupted()) {
                        movie.setGenres(genres);
                    }
                }
                , () -> {
                    List<Review> reviews = reviewService.getByMovieId(movieId);
                    if (!Thread.currentThread().isInterrupted()) {
                        movie.setReviews(reviews);
                    }
                }
        );
        try {
            List<Callable<Object>> callableList =
                    tasks.stream().map(r -> Executors.callable(r)).collect(Collectors.toList());
            executorService.invokeAll(callableList, timeoutMillis, TimeUnit.MILLISECONDS);

        } catch (InterruptedException e) {
            logger.debug("parallel enrich for movieId = {} interrupted {}", movieId, e);
        }
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

    @Autowired
    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Autowired
    public void setSlowService(SlowService slowService) {
        this.slowService = slowService;
    }
}
