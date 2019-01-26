package com.siatsenko.movieland.service.impl;

import com.siatsenko.movieland.entity.common.Country;
import com.siatsenko.movieland.entity.common.Genre;
import com.siatsenko.movieland.entity.common.Movie;
import com.siatsenko.movieland.entity.common.Review;
import com.siatsenko.movieland.service.CountryService;
import com.siatsenko.movieland.service.EnrichmentService;
import com.siatsenko.movieland.service.GenreService;
import com.siatsenko.movieland.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

@Service
@Primary
public class ParallelEnrichmentService implements EnrichmentService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private GenreService genreService;
    private CountryService countryService;
    private ReviewService reviewService;

    private ExecutorService executorService;

    @Override
    public Movie enrich(Movie movie) {
        movie.setGenres(new ArrayList());
        movie.setCountries(new ArrayList());
        movie.setReviews(new ArrayList());

        int movieId = movie.getId();
        List<Callable<List<?>>> tasks = Arrays.asList(
                () -> {
                    return new ArrayList<>(genreService.getByMovieId(movieId));
                }
                , () -> {
                    return new ArrayList<>(countryService.getByMovieId(movieId));
                }
                , () -> {
                    return new ArrayList<>(reviewService.getByMovieId(movieId));
                }
        );
        try {
            List<Future<List<?>>> results = executorService.invokeAll(tasks, 4000, TimeUnit.MILLISECONDS);

            List<Genre> genres = (List<Genre>) results.get(0).get();
            List<Country> countries = (List<Country>) results.get(1).get();
            List<Review> reviews = (List<Review>) results.get(2).get();

            movie.setGenres(genres);
            movie.setCountries(countries);
            movie.setReviews(reviews);
        } catch (InterruptedException | ExecutionException e) {
            logger.debug("parallel enrich for movieId = {} failed {}", movieId, e);
        } catch (CancellationException e) {
            logger.debug("parallel enrich for movieId = {} cancel {}", movieId, e);
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
}
