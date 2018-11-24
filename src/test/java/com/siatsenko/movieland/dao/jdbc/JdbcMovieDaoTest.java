package com.siatsenko.movieland.dao.jdbc;

import com.siatsenko.movieland.dao.jdbc.mapper.MovieRowMapper;
import com.siatsenko.movieland.entity.Movie;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.io.*;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;

public class JdbcMovieDaoTest {
    private static final String TEST_ALL_SQL = "SELECT * FROM v_movies;";

    // Create dataSource (DB in memory)
    private EmbeddedDatabase createDataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        builder.generateUniqueName(true);
        builder.setType(HSQL);
        builder.setScriptEncoding("UTF-8");
        builder.ignoreFailedDrops(true);
        builder.addScript("db/create_db.sql");
        builder.addScript("db/fill_db.sql");
        EmbeddedDatabase dataSource = builder.build();
        return dataSource;
    }

    @Test
    public void getAll() throws IOException {
        MovieRowMapper movieRowMapper = new MovieRowMapper();
        JdbcMovieDao jdbcMovieDao = new JdbcMovieDao();
        jdbcMovieDao.setMovieRowMapper(movieRowMapper);
        jdbcMovieDao.setGetAllSql(TEST_ALL_SQL);

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        EmbeddedDatabase dataSource = createDataSource();
        try {
            jdbcTemplate.setDataSource(dataSource);
            jdbcMovieDao.setJdbcTemplate(jdbcTemplate);

            List<Movie> movies = jdbcMovieDao.getAll();

            assertEquals(movies.size(), 2);
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

        } finally {
            dataSource.shutdown();
        }
    }

}