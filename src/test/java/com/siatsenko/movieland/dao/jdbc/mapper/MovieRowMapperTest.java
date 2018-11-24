package com.siatsenko.movieland.dao.jdbc.mapper;

import com.siatsenko.movieland.entity.Movie;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieRowMapperTest {

    @Test
    public void mapRow() throws SQLException {

        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name_russian")).thenReturn("Побег из Шоушенка");
        when(resultSet.getString("name_native")).thenReturn("The Shawshank Redemption");
        when(resultSet.getString("year_of_release")).thenReturn("1994");
        when(resultSet.getDouble("rating")).thenReturn(8.9);
        when(resultSet.getDouble("price")).thenReturn(123.45);
        when(resultSet.getString("picture_path")).thenReturn("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg");

        MovieRowMapper movieRowMapper = new MovieRowMapper();
        Movie movie = movieRowMapper.mapRow(resultSet, 0);
        assertEquals(movie.getId(), 1);
        assertEquals(movie.getNameRussian(), "Побег из Шоушенка");
        assertEquals(movie.getNameNative(), "The Shawshank Redemption");
        assertEquals(movie.getYearOfRelease(), "1994");
        assertEquals(movie.getRating(), 8.9, 0.000001);
        assertEquals(movie.getPrice(), 123.45, 0.000001);
        assertEquals(movie.getPicturePath(), "https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg");

    }
}