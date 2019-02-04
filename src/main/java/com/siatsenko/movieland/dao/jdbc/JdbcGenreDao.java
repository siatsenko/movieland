package com.siatsenko.movieland.dao.jdbc;

import com.siatsenko.movieland.dao.GenreDao;
import com.siatsenko.movieland.dao.jdbc.mapper.GenreRowMapper;
import com.siatsenko.movieland.entity.common.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcGenreDao implements GenreDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private JdbcTemplate jdbcTemplate;
    private GenreRowMapper genreRowMapper;

    @Value("${queries.genres.allGenresSql}")
    private String allGenresSql;
    @Value("${queries.genres.genresByMovieIdSql}")
    private String genresByMovieIdSql;
    @Value("${queries.genres.deleteGenresByMovieIdSql}")
    private String deleteGenresByMovieIdSql;
    @Value("${queries.genres.insertGenresByMovieIdSql}")
    private String insertGenresByMovieIdSql;

    @Override
    public List<Genre> getAll() {
        List<Genre> genres = jdbcTemplate.query(allGenresSql, genreRowMapper);
        logger.trace("getAll finished and return genres: {}", genres);
        return genres;
    }

    @Override
    public List<Genre> getByMovieId(int movieId) {
        List<Genre> genres = jdbcTemplate.query(genresByMovieIdSql, genreRowMapper, movieId);
        logger.trace("getByMovieId({}) finished and return genres: {}", movieId, genres);
        return genres;
    }

    @Override
    public void editByMovieId(int movieId, int[] genreIds) {
        deleteByMovieId(movieId);
        insertByMovieId(movieId, genreIds);
        logger.trace("editByMovieId({},{}) finished", movieId, genreIds);
    }

    void deleteByMovieId(int movieId) {
        jdbcTemplate.update(deleteGenresByMovieIdSql, movieId);
        logger.trace("deleteByMovieId({}) finished", movieId);
    }

    void insertByMovieId(int movieId, int[] genreIds){
        jdbcTemplate.batchUpdate(insertGenresByMovieIdSql, new BatchPreparedStatementSetter() {

            public void setValues(PreparedStatement ps, int i)
                    throws SQLException {
                ps.setInt(1, movieId);
                ps.setInt(2, genreIds[i]);
            }

            public int getBatchSize() {
                return genreIds.length;
            }
        });

        logger.trace("insertByMovieId({},{}) finished", movieId, genreIds);
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setGenreRowMapper(GenreRowMapper genreRowMapper) {
        this.genreRowMapper = genreRowMapper;
    }

}
