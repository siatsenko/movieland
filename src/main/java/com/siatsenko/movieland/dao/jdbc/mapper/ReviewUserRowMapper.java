package com.siatsenko.movieland.dao.jdbc.mapper;

import com.siatsenko.movieland.entity.Review;
import com.siatsenko.movieland.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ReviewUserRowMapper implements RowMapper<Review> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    UserRowMapper userRowMapper;

    @Override
    public Review mapRow(ResultSet resultSet, int i) throws SQLException {

        int reviewId = resultSet.getInt("review_id");
        User user = userRowMapper.mapRow(resultSet, i);

        Review review = new Review(reviewId, user);
        logger.trace("mapRow finished and return review: {}", review);
        return review;
    }

    @Autowired
    public void setUserRowMapper(UserRowMapper userRowMapper) {
        this.userRowMapper = userRowMapper;
    }
}
