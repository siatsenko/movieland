package com.siatsenko.movieland.dao.jdbc;

import com.siatsenko.movieland.entity.common.Genre;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcGenreDaoTest {

    private JdbcGenreDao jdbcGenreDao;

    @Test
    public void getAll() {
        List<Genre> genres = jdbcGenreDao.getAll();

        assertEquals(15, genres.size());

        Genre genreFirst = genres.get(0);
        assertEquals(1, genreFirst.getId());
        assertEquals("драма", genreFirst.getName());

        Genre genreSecond = genres.get(1);
        assertEquals(2, genreSecond.getId());
        assertEquals("криминал", genreSecond.getName());
    }

    @Autowired
    public void setJdbcGenreDao(JdbcGenreDao jdbcGenreDao) {
        this.jdbcGenreDao = jdbcGenreDao;
    }
}