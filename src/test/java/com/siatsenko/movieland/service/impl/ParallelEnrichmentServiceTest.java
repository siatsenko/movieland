package com.siatsenko.movieland.service.impl;

import com.siatsenko.movieland.entity.common.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParallelEnrichmentServiceTest {

    ParallelEnrichmentService parallelEnrichmentService;

    @Test
    public void enrich() {
        Movie movie = new Movie();
        movie.setId(2);
        parallelEnrichmentService.enrich(movie);

        assertEquals(1,movie.getCountries().size());
        assertEquals(4,movie.getGenres().size());
        assertEquals(1,movie.getReviews().size());
    }

    @Autowired
    public void setParallelEnrichmentService(ParallelEnrichmentService parallelEnrichmentService) {
        this.parallelEnrichmentService = parallelEnrichmentService;
    }
}