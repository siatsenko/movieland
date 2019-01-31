package com.siatsenko.movieland.web.controller.util;

import com.siatsenko.movieland.entity.common.Movie;
import com.siatsenko.movieland.entity.common.Session;
import com.siatsenko.movieland.web.dto.LoginUserDto;
import com.siatsenko.movieland.web.dto.MovieDetailDto;
import com.siatsenko.movieland.web.dto.MovieDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class DtoConverter {

    public MovieDto asMovieDto(Movie movie) {
        return new MovieDto(movie);
    }

    public List<MovieDto> asMovieDto(List<Movie> movies) {
        List<MovieDto> movieDtos = new ArrayList<>();
        for (Movie movie : movies) {
            movieDtos.add(asMovieDto(movie));
        }
        return movieDtos;
    }

    public MovieDetailDto asMovieDetailDto(Movie movie) {
        MovieDetailDto movieDetailDto = new MovieDetailDto(movie);
        movieDetailDto.setPrice(new BigDecimal(movie.getPrice()).setScale(2, RoundingMode.UP).doubleValue());
        return movieDetailDto;
    }

    public LoginUserDto asLoginUserDto(Session session) {
        String uuid = session.getToken();
        String nickname = session.getUser().getName();
        return new LoginUserDto(uuid, nickname);
    }

}
