package com.siatsenko.movieland.dao.jdbc.mapper;

import com.siatsenko.movieland.entity.Review;
import com.siatsenko.movieland.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ReviewRowMapper implements RowMapper<Review> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Review mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        User user = new User(resultSet.getInt("user_id"));
        String text = resultSet.getString("text");

        Review review = new Review(id, user, text);

        logger.trace("mapRow finished and return review: {}", review);
        return review;
    }

}
