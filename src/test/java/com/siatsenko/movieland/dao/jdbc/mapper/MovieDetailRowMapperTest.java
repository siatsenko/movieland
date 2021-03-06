package com.siatsenko.movieland.dao.jdbc.mapper;

import com.siatsenko.movieland.entity.common.Movie;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieDetailRowMapperTest {

    @Test
    public void mapRow() throws SQLException {

        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name_russian")).thenReturn("Побег из Шоушенка");
        when(resultSet.getString("name_native")).thenReturn("The Shawshank Redemption");
        when(resultSet.getString("year_of_release")).thenReturn("1994");
        when(resultSet.getString("description")).thenReturn("Успешный банкир Энди Дюфрейн обвинен в убийстве собственной жены и ее любовника.");

        when(resultSet.getDouble("rating")).thenReturn(8.9);
        when(resultSet.getDouble("price")).thenReturn(123.45);
        when(resultSet.getString("picture_path")).thenReturn("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg");

        MovieDetailRowMapper movieDetailRowMapper = new MovieDetailRowMapper();
        MovieRowMapper movieRowMapper = new MovieRowMapper();
        movieDetailRowMapper.setMovieRowMapper(movieRowMapper);

        Movie movie = movieDetailRowMapper.mapRow(resultSet, 0);
        assertEquals(1, movie.getId());
        assertEquals("Побег из Шоушенка", movie.getNameRussian());
        assertEquals("The Shawshank Redemption", movie.getNameNative());
        assertEquals("1994", movie.getYearOfRelease());
        assertEquals("Успешный банкир Энди Дюфрейн обвинен в убийстве собственной жены и ее любовника.", movie.getDescription());
        assertEquals(8.9, movie.getRating(), 0.000001);
        assertEquals(123.45, movie.getPrice(), 0.000001);
        assertEquals("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg", movie.getPicturePath());

    }
}