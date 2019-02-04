package com.siatsenko.movieland.service.impl;

import com.siatsenko.movieland.entity.common.Movie;
import com.siatsenko.movieland.entity.common.User;
import com.siatsenko.movieland.entity.request.MovieRequest;
import com.siatsenko.movieland.entity.request.RequestParameters;
import com.siatsenko.movieland.service.CurrencyService;
import com.siatsenko.movieland.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.lang.ref.SoftReference;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Primary
public class CachedMovieService implements MovieService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Qualifier("DefaultMovieService")
    private MovieService baseMovieService;
    private CurrencyService currencyService;

    private ConcurrentHashMap<Integer, SoftReference<Movie>> cachedMovies = new ConcurrentHashMap<>();

    @Override
    public List<Movie> getAll(RequestParameters requestParameters) {
        return baseMovieService.getAll(requestParameters);
    }

    @Override
    public List<Movie> getRandom() {
        return baseMovieService.getRandom();
    }

    @Override
    public List<Movie> getByGenreId(int genreId, RequestParameters requestParameters) {
        return baseMovieService.getByGenreId(genreId, requestParameters);
    }

    @Override
    public Movie upsert(MovieRequest movieRequest, User user) {
        Movie movie = baseMovieService.upsert(movieRequest, user);
        if (cachedMovies.containsKey(movie.getId())) {
            putToCache(movie);
        }
        return movie;
    }

    @Override
    public Movie getById(int id, String currencyCode) {
        Movie movie = getById(id);
        currencyService.enrich(movie, currencyCode);

        logger.debug("getById({},{}) finished and return movies: {}", id, currencyCode, movie);
        return movie;
    }

    @Override
    public Movie getById(int id) {
        SoftReference<Movie> softMovie = cachedMovies.computeIfAbsent(id, k -> {
                Movie originalMovie = baseMovieService.getById(k);
                return new SoftReference(originalMovie);
        });
        Movie movie = softMovie.get();
        logger.debug("getById({}) finished and return CACHED movies: {}", id, movie);
        return movie;
    }

    Movie getFromCache(int id) {
        if (cachedMovies.containsKey(id)) {
            SoftReference<Movie> softMovie = cachedMovies.get(id);
            return softMovie.get();
        }
        return null;
    }

    SoftReference<Movie> putToCache(Movie movie) {
        SoftReference<Movie> softMovie = new SoftReference(movie);
        return cachedMovies.put(movie.getId(), softMovie);
    }

    void clearCache() {
        cachedMovies.clear();
    }

    @Autowired
    public void setBaseMovieService(MovieService baseMovieService) {
        this.baseMovieService = baseMovieService;
    }

    @Autowired
    public void setCurrencyService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

}
