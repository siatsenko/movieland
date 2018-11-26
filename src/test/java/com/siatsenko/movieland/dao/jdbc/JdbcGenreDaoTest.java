package com.siatsenko.movieland.dao.jdbc;

import com.siatsenko.movieland.entity.Genre;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class JdbcGenreDaoTest {
    private static final ApplicationContext CONTEXT = new ClassPathXmlApplicationContext("spring/test-context.xml");
    private static final JdbcGenreDao JDBC_GENRE_DAO = (JdbcGenreDao) CONTEXT.getBean("jdbcGenreDao");

    @Test
    public void getAll() {
        List<Genre> genres = JDBC_GENRE_DAO.getAll();

        assertEquals(genres.size(), 15);

        Genre genreFirst = genres.get(0);
        assertEquals(genreFirst.getId(), 1);
        assertEquals(genreFirst.getName(), "драма");

        Genre genreSecond = genres.get(1);
        assertEquals(genreSecond.getId(), 2);
        assertEquals(genreSecond.getName(), "криминал");
    }
}