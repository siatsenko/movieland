package com.siatsenko.movieland.dao.jdbc;

import com.siatsenko.movieland.entity.Genre;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "/spring/test-context.xml")
public class JdbcGenreDaoTest {

    private JdbcGenreDao jdbcGenreDao;

    @Test
    public void getAll() {
        List<Genre> genres = jdbcGenreDao.getAll();

        assertEquals(genres.size(), 15);

        Genre genreFirst = genres.get(0);
        assertEquals(genreFirst.getId(), 1);
        assertEquals(genreFirst.getName(), "драма");

        Genre genreSecond = genres.get(1);
        assertEquals(genreSecond.getId(), 2);
        assertEquals(genreSecond.getName(), "криминал");
    }

    @Autowired
    public void setJdbcGenreDao(JdbcGenreDao jdbcGenreDao) {
        this.jdbcGenreDao = jdbcGenreDao;
    }
}