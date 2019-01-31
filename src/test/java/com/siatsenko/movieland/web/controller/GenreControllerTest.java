package com.siatsenko.movieland.web.controller;

import com.siatsenko.movieland.entity.common.Genre;
import com.siatsenko.movieland.service.GenreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GenreControllerTest {

    GenreService genreService;
    GenreController genreController;

    @Test
    public void getAll() {
        List<Genre> genres = genreController.getAll();

        assertEquals(15, genres.size());

        assertEquals(1,genres.get(0).getId());
        assertEquals("драма",genres.get(0).getName());

        assertEquals(5,genres.get(4).getId());
        assertEquals("мелодрама",genres.get(4).getName());

        assertEquals(15,genres.get(14).getId());
        assertEquals("вестерн",genres.get(14).getName());

    }

    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }

    @Autowired
    public void setGenreController(GenreController genreController) {
        this.genreController = genreController;
    }
}