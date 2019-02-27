package com.siatsenko.movieland.service.impl;

import com.siatsenko.movieland.entity.common.Movie;
import com.siatsenko.movieland.entity.common.User;
import com.siatsenko.movieland.entity.request.MovieRequest;
import com.siatsenko.movieland.entity.request.RequestParameters;
import com.siatsenko.movieland.service.CurrencyService;
import com.siatsenko.movieland.service.EnrichmentService;
import com.siatsenko.movieland.service.MovieService;
import com.siatsenko.movieland.web.dto.CacheMovieDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Primary
public class CachedMovieService implements MovieService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Qualifier("DefaultMovieService")
    private MovieService baseMovieService;
    private CurrencyService currencyService;
    private EnrichmentService enrichmentService;

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
            enrichmentService.enrich(movie);
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

        SoftReference<Movie> softMovie = cachedMovies.compute(id, (Integer k, SoftReference<Movie> v) -> {
            logger.debug("cachedMovies.compute({}, (Integer {}, SoftReference<Movie> {})", id, k, v);
            if (v != null && v.get() != null) {
                logger.debug("cachedMovies.compute return CACHE ({})", v);
                return v;
            }
            Movie originalMovie = baseMovieService.getById(k);
            SoftReference<Movie> newValue = new SoftReference<>(originalMovie);
            logger.debug("cachedMovies.compute return NEW ({})", newValue);
            return newValue;
        });
        Movie movie = softMovie.get();

        logger.debug("cacheStateLog() = {}", cacheStateLog());
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
        SoftReference<Movie> softMovie = new SoftReference<>(movie);
        return cachedMovies.put(movie.getId(), softMovie);
    }

    void clearCache() {
        cachedMovies.clear();
    }

    public List<CacheMovieDto> cacheStateWeb() {
        List<CacheMovieDto> cacheMovieDtos = new ArrayList<>();
        cachedMovies.forEach((Integer k, SoftReference<Movie> v) -> {
            Movie movie = v.get();
            if (movie != null) {
                cacheMovieDtos.add(new CacheMovieDto(k, movie));
            } else {
                cacheMovieDtos.add(new CacheMovieDto(k, null));
            }
        });
        return cacheMovieDtos;
    }

    public String cacheStateLog() {
        String border = "\n cachedMovies -----------------------------------";
        StringJoiner stringJoiner = new StringJoiner(" : ", border, border);
        cachedMovies.forEach((Integer k, SoftReference<Movie> v) -> {
            stringJoiner.add("\n" + k);
            if (v == null) {
                stringJoiner.add("null : null");
            } else {
                Movie m = v.get();
                stringJoiner.add(String.valueOf(m));
            }
        });
        return stringJoiner.toString();
    }

    @Autowired
    public void setBaseMovieService(MovieService baseMovieService) {
        this.baseMovieService = baseMovieService;
    }

    @Autowired
    public void setCurrencyService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Autowired
    public void setEnrichmentService(EnrichmentService enrichmentService) {
        this.enrichmentService = enrichmentService;
    }
}
