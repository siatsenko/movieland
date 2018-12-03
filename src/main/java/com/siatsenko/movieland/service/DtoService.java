package com.siatsenko.movieland.service;

import com.siatsenko.movieland.entity.Movie;
import com.siatsenko.movieland.entity.dto.CommonDto;

import java.util.List;

public interface DtoService {

    CommonDto asMovieDto(Movie movie);

    List<CommonDto> asMovieDto(List<Movie> movies);

    CommonDto asMovieDetailDto(Movie movie);

}
