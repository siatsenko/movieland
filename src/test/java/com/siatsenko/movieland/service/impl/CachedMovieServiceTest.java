package com.siatsenko.movieland.service.impl;

import com.siatsenko.movieland.entity.common.Movie;
import com.siatsenko.movieland.entity.common.Role;
import com.siatsenko.movieland.entity.common.User;
import com.siatsenko.movieland.entity.request.MovieRequest;
import com.siatsenko.movieland.entity.request.RequestParameters;
import com.siatsenko.movieland.service.RequestParamsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CachedMovieServiceTest {

    @Autowired
    CachedMovieService cachedMovieService;

    @Autowired
    DefaultMovieService defaultMovieService;

    @Autowired
    RequestParamsService requestParamsService;

    @Before
    public void before() {
        cachedMovieService.clearCache();
    }

    @Test
    public void getAll() {
        RequestParameters requestParameters = requestParamsService.setSortings(new HashMap<String, String>());

        List<Movie> originalMovies = defaultMovieService.getAll(requestParameters);
        List<Movie> cachedMovies = cachedMovieService.getAll(requestParameters);
        assertEquals(originalMovies,cachedMovies);
    }

    @Test
    public void getRandom() {
        List<Movie> originalMovies = defaultMovieService.getRandom();
        List<Movie> cachedMovies = cachedMovieService.getRandom();
        assertEquals(originalMovies.size(),cachedMovies.size());
    }

    @Test
    public void getByGenreId() {
        RequestParameters requestParameters = requestParamsService.setSortings(new HashMap<String, String>());

        List<Movie> originalMovies = defaultMovieService.getByGenreId(2,requestParameters);
        List<Movie> cachedMovies = cachedMovieService.getByGenreId(2,requestParameters);
        assertEquals(originalMovies,cachedMovies);
    }

    @Test
    public void getById() {
        Movie originalMovie = defaultMovieService.getById(2);
        Movie cachedMovie = cachedMovieService.getById(2);
        assertEquals(originalMovie,cachedMovie);
    }

    @Test
    @DirtiesContext
    public void upsert() {
        Movie firstCachedMovie = cachedMovieService.getFromCache(2);
        assertNull(firstCachedMovie);

        Movie originalMovie = cachedMovieService.getById(2);
        assertEquals("Зеленая миля",originalMovie.getNameRussian());
        assertEquals(4,originalMovie.getGenres().size());

        Movie secondCachedMovie = cachedMovieService.getFromCache(2);
        assertNotNull(secondCachedMovie);
        assertEquals("Зеленая миля",secondCachedMovie.getNameRussian());
        assertEquals(4,secondCachedMovie.getGenres().size());

        MovieRequest movieRequest = mock(MovieRequest.class);
        when(movieRequest.getId()).thenReturn(2);
        when(movieRequest.getNameNative()).thenReturn("Test Name Native");
        when(movieRequest.getNameRussian()).thenReturn("Тест Наименование на русском");

        cachedMovieService.upsert(movieRequest,new User("admin", "admin", Role.ADMIN));

        Movie thirdCachedMovie = cachedMovieService.getFromCache(2);
        assertEquals("Test Name Native",thirdCachedMovie.getNameNative());
        assertEquals("Тест Наименование на русском",thirdCachedMovie.getNameRussian());
        assertNull(thirdCachedMovie.getGenres());
    }

    @Test
    @DirtiesContext
    public void getFromCache() {
        Movie firstCachedMovie = cachedMovieService.getFromCache(2);
        assertNull(firstCachedMovie);

        Movie originalMovie = cachedMovieService.getById(2);
        assertEquals("Зеленая миля",originalMovie.getNameRussian());
        assertEquals(4,originalMovie.getGenres().size());

        Movie secondCachedMovie = cachedMovieService.getFromCache(2);
        assertNotNull(secondCachedMovie);
        assertEquals("Зеленая миля",secondCachedMovie.getNameRussian());
        assertEquals(4,secondCachedMovie.getGenres().size());
    }

    @Test
    @DirtiesContext
    public void putToCache() {
        Movie firstCachedMovie = cachedMovieService.getFromCache(2);
        assertNull(firstCachedMovie);

        Movie originalMovie = defaultMovieService.getById(2);
        cachedMovieService.putToCache(originalMovie);

        Movie secondCachedMovie = cachedMovieService.getFromCache(2);
        assertNotNull(secondCachedMovie);
        assertEquals("Зеленая миля",secondCachedMovie.getNameRussian());
        assertEquals(4,secondCachedMovie.getGenres().size());
    }
}