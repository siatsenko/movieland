package com.siatsenko.movieland.dao.jdbc;

import com.siatsenko.movieland.dao.CountryDao;
import com.siatsenko.movieland.dao.jdbc.mapper.CountryRowMapper;
import com.siatsenko.movieland.entity.common.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

@Repository
public class JdbcCountryDao implements CountryDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private JdbcTemplate jdbcTemplate;
    private CountryRowMapper countryRowMapper;

    @Value("${queries.countries.countriesByMovieIdSql}")
    private String countriesByMovieIdSql;
    @Value("${queries.countries.allCountriesSql}")
    private String allCountriesSql;
    @Value("${queries.countries.deleteCountriesByMovieIdSql}")
    private String deleteCountriesByMovieIdSql;
    @Value("${queries.countries.insertCountriesByMovieIdSql}")
    private String insertCountriesByMovieIdSql;

    @Override
    public List<Country> getByMovieId(int movieId) {
        List<Country> countries = jdbcTemplate.query(countriesByMovieIdSql, countryRowMapper, movieId);
        logger.trace("getByMovieId({}) finished and return countries: {}", movieId, countries);
        return countries;
    }

    @Override
    public List<Country> getAll() {
        List<Country> countries = jdbcTemplate.query(allCountriesSql, countryRowMapper);
        logger.trace("getAll finished and return countries: {}", countries);
        return countries;
    }

    @Override
    public void editByMovieId(int movieId, int[] countryIds) {
        deleteByMovieId(movieId);
        insertByMovieId(movieId, countryIds);
        logger.trace("editByMovieId({},{}) finished", movieId, countryIds);
    }

    void deleteByMovieId(int movieId) {
        jdbcTemplate.update(deleteCountriesByMovieIdSql, movieId);
        logger.trace("deleteByMovieId({}) finished", movieId);
    }

    void insertByMovieId(int movieId, int[] countryIds) {
        jdbcTemplate.batchUpdate(insertCountriesByMovieIdSql, new BatchPreparedStatementSetter() {

            public void setValues(PreparedStatement ps, int i)
                    throws SQLException {
                ps.setInt(1, movieId);
                ps.setInt(2, countryIds[i]);
            }

            public int getBatchSize() {
                return countryIds.length;
            }
        });

        logger.trace("insertByMovieId({},{}) finished", movieId, countryIds);
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setCountryRowMapper(CountryRowMapper countryRowMapper) {
        this.countryRowMapper = countryRowMapper;
    }

}
