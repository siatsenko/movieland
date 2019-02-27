package com.siatsenko.movieland.service.impl;

import com.siatsenko.movieland.dao.GenreDao;
import com.siatsenko.movieland.entity.common.Genre;
import com.siatsenko.movieland.entity.common.Movie;
import com.siatsenko.movieland.service.GenreService;
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
public class DefaultGenreServiceTest {

    private GenreService genreService;

    @Test
    public void getAll() {
        List<Genre> genres = genreService.getAll();
        assertEquals(15, genres.size());
    }

    @Test
    public void getByMovieId() {
        List<Genre> genres = genreService.getByMovieId(1);
        assertEquals(2, genres.size());
    }

    @Test
    public void enrich() {

        Movie movie = new Movie();
        movie.setId(1);
        Movie enrichedMovie = genreService.enrich(movie);
        List<Genre> genres = enrichedMovie.getGenres();

        assertEquals(2, genres.size());

        Genre genreFirst = genres.get(0);
        assertEquals(1, genreFirst.getId());
        assertEquals("драма", genreFirst.getName());

        Genre genreSecond = genres.get(1);
        assertEquals(2, genreSecond.getId());
        assertEquals("криминал", genreSecond.getName());
    }

    @Test
    @DirtiesContext
    public void editByMovieId() {
        genreService.editByMovieId(1, new int[]{2, 3, 5});
        List<Genre> genres = genreService.getByMovieId(1);
        assertEquals(3, genres.size());
    }

    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }
}