package com.siatsenko.movieland.dao.jdbc;

import com.siatsenko.movieland.entity.common.Movie;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcMovieDaoTest {

    private JdbcMovieDao jdbcMovieDao;

    @Test
    public void getAll() {
        List<Movie> movies = jdbcMovieDao.getAll(null);

        assertEquals(4, movies.size());

        Movie movieFirst = movies.get(0);
        assertEquals(1, movieFirst.getId());
        assertEquals("Побег из Шоушенка", movieFirst.getNameRussian());
        assertEquals("The Shawshank Redemption", movieFirst.getNameNative());
        assertEquals("1994", movieFirst.getYearOfRelease());
        assertEquals(8.9, movieFirst.getRating(), 0.000001);
        assertEquals(123.45, movieFirst.getPrice(), 0.000001);
        assertEquals("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg", movieFirst.getPicturePath());

        Movie movieSecond = movies.get(1);
        assertEquals(2, movieSecond.getId());
        assertEquals("Зеленая миля", movieSecond.getNameRussian());
        assertEquals("The Green Mile", movieSecond.getNameNative());
        assertEquals("1999", movieSecond.getYearOfRelease());
        assertEquals(8.9, movieSecond.getRating(), 0.000001);
        assertEquals(134.67, movieSecond.getPrice(), 0.000001);
        assertEquals("https://images-na.ssl-images-amazon.com/images/M/MV5BMTUxMzQyNjA5MF5BMl5BanBnXkFtZTYwOTU2NTY3._V1._SY209_CR0,0,140,209_.jpg", movieSecond.getPicturePath());
    }

    @Test
    public void getRandom() {
        List<Movie> movies = jdbcMovieDao.getRandom();
        assertEquals(2, movies.size());
    }

    @Test
    public void getByGenreId() {
        List<Movie> movies1 = jdbcMovieDao.getByGenreId(1, null);
        assertEquals(4, movies1.size());

        List<Movie> movies2 = jdbcMovieDao.getByGenreId(2, null);
        assertEquals(2, movies2.size());
    }

    @Autowired
    public void setJdbcMovieDao(JdbcMovieDao jdbcMovieDao) {
        this.jdbcMovieDao = jdbcMovieDao;
    }
}