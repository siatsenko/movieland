package com.siatsenko.movieland.controller;

import com.siatsenko.movieland.service.MovieService;
import com.siatsenko.movieland.service.RequestParamsService;

import com.siatsenko.movieland.web.controller.util.DtoConverter;
import com.siatsenko.movieland.web.dto.MovieDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "/spring/test-context.xml")
public class MovieControllerTest {

    MovieController movieController;
    DtoConverter dtoConverter;
    RequestParamsService requestParamsService;
    MovieService movieService;

    @Test
    public void getAll() {

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("rating", "desc");
        queryMap.put("price", "asc");

        List<MovieDto> movieDtos = movieController.getAll(queryMap);

        assertEquals(4, movieDtos.size());

        MovieDto movieDtoFirst = movieDtos.get(0);
        assertEquals(4, movieDtoFirst.getId());
        assertEquals("Танцующий с волками", movieDtoFirst.getNameRussian());
        assertEquals("Dances with Wolves", movieDtoFirst.getNameNative());
        assertEquals("1990", movieDtoFirst.getYearOfRelease());
        assertEquals(8.00, movieDtoFirst.getRating(), 0.000001);
        assertEquals(120.55, movieDtoFirst.getPrice(), 0.000001);
        assertEquals("https://images-na.ssl-images-amazon.com/images/M/MV5BMTY3OTI5NDczN15BMl5BanBnXkFtZTcwNDA0NDY3Mw@@._V1._SX140_CR0,0,140,209_.jpg", movieDtoFirst.getPicturePath());

        MovieDto movieDtoSecond = movieDtos.get(1);
        assertEquals(1, movieDtoSecond.getId());
        assertEquals("Побег из Шоушенка", movieDtoSecond.getNameRussian());
        assertEquals("The Shawshank Redemption", movieDtoSecond.getNameNative());
        assertEquals("1994", movieDtoSecond.getYearOfRelease());
        assertEquals(8.9, movieDtoSecond.getRating(), 0.000001);
        assertEquals(123.45, movieDtoSecond.getPrice(), 0.000001);
        assertEquals("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg", movieDtoSecond.getPicturePath());

    }

    @Test
    public void getRandom() {

        List<MovieDto> movieDtos = movieController.getRandom();

        assertEquals(2, movieDtos.size());

    }

    @Test
    public void getByGenreId() {

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("rating", "asc");

        List<MovieDto> movieDtos = movieController.getByGenreId(2,queryMap);

        assertEquals(2, movieDtos.size());

        MovieDto movieDtoFirst = movieDtos.get(0);
        assertEquals(1, movieDtoFirst.getId());
        assertEquals("Побег из Шоушенка", movieDtoFirst.getNameRussian());
        assertEquals("The Shawshank Redemption", movieDtoFirst.getNameNative());
        assertEquals("1994", movieDtoFirst.getYearOfRelease());
        assertEquals(8.9, movieDtoFirst.getRating(), 0.000001);
        assertEquals(123.45, movieDtoFirst.getPrice(), 0.000001);
        assertEquals("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg", movieDtoFirst.getPicturePath());

        MovieDto movieDtoSecond = movieDtos.get(1);
        assertEquals(2, movieDtoSecond.getId());
        assertEquals("Зеленая миля", movieDtoSecond.getNameRussian());
        assertEquals("The Green Mile", movieDtoSecond.getNameNative());
        assertEquals("1999", movieDtoSecond.getYearOfRelease());
        assertEquals(8.9, movieDtoSecond.getRating(), 0.000001);
        assertEquals(134.67, movieDtoSecond.getPrice(), 0.000001);
        assertEquals("https://images-na.ssl-images-amazon.com/images/M/MV5BMTUxMzQyNjA5MF5BMl5BanBnXkFtZTYwOTU2NTY3._V1._SY209_CR0,0,140,209_.jpg", movieDtoSecond.getPicturePath());

    }

    @Test
    public void getById() {

//        MovieDetailDto movieDetailDto = movieController.getById(1);
//
//        assertEquals(1, movieDetailDto.getId());
//        assertEquals("Побег из Шоушенка", movieDetailDto.getNameRussian());
//        assertEquals("The Shawshank Redemption", movieDetailDto.getNameNative());
//        assertEquals("1994", movieDetailDto.getYearOfRelease());
//        assertEquals("Успешный банкир Энди Дюфрейн обвинен в убийстве собственной жены и ее любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решетки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, вооруженный живым умом и доброй душой, отказывается мириться с приговором судьбы и начинает разрабатывать невероятно дерзкий план своего освобождения.", movieDetailDto.getDescription());
//        assertEquals(8.9, movieDetailDto.getRating(), 0.000001);
//        assertEquals(123.45, movieDetailDto.getPrice(), 0.000001);
//        assertEquals("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg", movieDetailDto.getPicturePath());

    }

    @Autowired
    public void setMovieController(MovieController movieController) {
        this.movieController = movieController;
    }

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    @Autowired
    public void setDtoConverter(DtoConverter dtoConverter) {
        this.dtoConverter = dtoConverter;
    }

    @Autowired
    public void setRequestParamsService(RequestParamsService requestParamsService) {
        this.requestParamsService = requestParamsService;
    }

}