package com.siatsenko.movieland.dao.jdbc;

import com.siatsenko.movieland.dao.ReviewDao;
import com.siatsenko.movieland.dao.jdbc.mapper.ReviewRowMapper;
import com.siatsenko.movieland.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcReviewDao implements ReviewDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private JdbcTemplate jdbcTemplate;
    private String reviewsByMovieIdSql;
    private String addReviewSql;
    private ReviewRowMapper reviewRowMapper;

    @Override
    public List<Review> getByMovieId(int movieId) {
        List<Review> reviews = jdbcTemplate.query(reviewsByMovieIdSql, reviewRowMapper, movieId);
        logger.trace("getByMovieId({}) finished and return reviews: {}", movieId, reviews);
        return reviews;
    }

    @Override
    public void add(int movieId, Review review) {
        jdbcTemplate.update(addReviewSql, movieId, review.getUser().getId(), review.getText());
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setReviewsByMovieIdSql(String reviewsByMovieIdSql) {
        this.reviewsByMovieIdSql = reviewsByMovieIdSql;
    }

    @Autowired
    public void setReviewRowMapper(ReviewRowMapper reviewRowMapper) {
        this.reviewRowMapper = reviewRowMapper;
    }

    @Autowired
    public void setAddReviewSql(String addReviewSql) {
        this.addReviewSql = addReviewSql;
    }
}
