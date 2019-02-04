package com.siatsenko.movieland.dao.jdbc;

import com.siatsenko.movieland.entity.common.Genre;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcGenreDaoTest {

    @Qualifier("jdbcGenreDao")
    private JdbcGenreDao jdbcGenreDao;

    @Test
    public void getByMovieId() {
        List<Genre> genres = jdbcGenreDao.getByMovieId(1);

        assertEquals(2, genres.size());

        Genre genreFirst = genres.get(0);
        assertEquals(1, genreFirst.getId());
        assertEquals("драма", genreFirst.getName());

        Genre genreSecond = genres.get(1);
        assertEquals(2, genreSecond.getId());
        assertEquals("криминал", genreSecond.getName());
    }

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

    @Test
    @DirtiesContext
    public void editByMovieId() {
        jdbcGenreDao.editByMovieId(1, new int[]{2, 3, 5});
        List<Genre> genres = jdbcGenreDao.getByMovieId(1);
        assertEquals(3, genres.size());
    }

    @Test
    @DirtiesContext
    public void deleteByMovieId() {
        jdbcGenreDao.deleteByMovieId(1);
        List<Genre> genres = jdbcGenreDao.getByMovieId(1);
        assertEquals(0, genres.size());
    }

    @Test
    @DirtiesContext
    public void insertByMovieId() {
        jdbcGenreDao.insertByMovieId(1, new int[]{3, 5});
        List<Genre> genres = jdbcGenreDao.getByMovieId(1);
        assertEquals(4, genres.size());
    }

    @Autowired
    public void setJdbcGenreDao(JdbcGenreDao jdbcGenreDao) {
        this.jdbcGenreDao = jdbcGenreDao;
    }

}