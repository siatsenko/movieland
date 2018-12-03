package com.siatsenko.movieland.service.impl;

import com.siatsenko.movieland.entity.Movie;
import com.siatsenko.movieland.entity.dto.CommonDto;
import com.siatsenko.movieland.entity.dto.MovieDetailDto;
import com.siatsenko.movieland.entity.dto.MovieDto;
import com.siatsenko.movieland.service.DtoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultDtoService implements DtoService {

    @Override
    public CommonDto asMovieDto(Movie movie) {
        MovieDto movieDto = new MovieDto(movie);
        return movieDto;
    }

    @Override
    public List<CommonDto> asMovieDto(List<Movie> movies) {
        List<CommonDto> commonDtos = new ArrayList<>();
        for (Movie movie : movies) {
            commonDtos.add(asMovieDto(movie));
        }
        return commonDtos;
    }

    @Override
    public CommonDto asMovieDetailDto(Movie movie) {
        MovieDetailDto movieDetailDto = new MovieDetailDto(movie);
        return movieDetailDto;
    }

}
