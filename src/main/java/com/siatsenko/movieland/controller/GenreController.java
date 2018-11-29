package com.siatsenko.movieland.controller;

import com.siatsenko.movieland.entity.Genre;
import com.siatsenko.movieland.service.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenreController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private GenreService genreService;

    @RequestMapping(path = "/genre", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
