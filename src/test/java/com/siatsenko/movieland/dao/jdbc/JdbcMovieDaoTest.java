package com.siatsenko.movieland.dao.jdbc;

import com.siatsenko.movieland.entity.Movie;
import org.junit.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.io.*;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JdbcMovieDaoTest {
    private static final ApplicationContext CONTEXT = new  ClassPathXmlApplicationContext("spring/test-context.xml");
    private static final JdbcMovieDao JDBC_MOVIE_DAO = (JdbcMovieDao) CONTEXT.getBean("jdbcMovieDao");

    @Test
    public void getAll() {
            List<Movie> movies = JDBC_MOVIE_DAO.getAll();

            assertEquals(movies.size(), 4);
            Movie movieFirst = movies.get(0);

            assertEquals(movieFirst.getId(), 1);
            assertEquals(movieFirst.getNameRussian(), "Побег из Шоушенка");
            assertEquals(movieFirst.getNameNative(), "The Shawshank Redemption");
            assertEquals(movieFirst.getYearOfRelease(), "1994");
            assertEquals(movieFirst.getRating(), 8.9, 0.000001);
            assertEquals(movieFirst.getPrice(), 123.45, 0.000001);
            assertEquals(movieFirst.getPicturePath(), "https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg");

            Movie movieSecond = movies.get(1);
            assertEquals(movieSecond.getId(), 2);
            assertEquals(movieSecond.getNameRussian(), "Зеленая миля");
            assertEquals(movieSecond.getNameNative(), "The Green Mile");
            assertEquals(movieSecond.getYearOfRelease(), "1999");
            assertEquals(movieSecond.getRating(), 8.9, 0.000001);
            assertEquals(movieSecond.getPrice(), 134.67, 0.000001);
            assertEquals(movieSecond.getPicturePath(), "https://images-na.ssl-images-amazon.com/images/M/MV5BMTUxMzQyNjA5MF5BMl5BanBnXkFtZTYwOTU2NTY3._V1._SY209_CR0,0,140,209_.jpg");
    }

        @Test
        public void getRandom() {
                List<Movie> movies = JDBC_MOVIE_DAO.getRandom();

                assertEquals(movies.size(), 2);
        }
}