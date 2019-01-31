package com.siatsenko.movieland.web.controller;

import com.siatsenko.movieland.entity.common.Genre;
import com.siatsenko.movieland.service.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenreController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private GenreService genreService;

    @GetMapping(path = "/genre", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Genre> getAll() {
        logger.info("Sending request to get all genres");
        List<Genre> genres = genreService.getAll();
        logger.debug("Returning {} genres", genres.size());
        return genres;
    }

    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }

}
