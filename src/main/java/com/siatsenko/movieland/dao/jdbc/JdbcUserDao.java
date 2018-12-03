package com.siatsenko.movieland.dao.jdbc;

import com.siatsenko.movieland.dao.UserDao;
import com.siatsenko.movieland.dao.jdbc.mapper.ReviewUserRowMapper;
import com.siatsenko.movieland.entity.Review;
import com.siatsenko.movieland.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcUserDao implements UserDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private JdbcTemplate jdbcTemplate;
    private String usersByReviewIdsSql;
    private ReviewUserRowMapper reviewUserRowMapper;

    @Override
    public Map<Integer, User> getByReviewIds(List<Integer> reviewIds) {
        int[] idList = reviewIds.stream().mapToInt(Integer::intValue).toArray();
        List<Review> reviews = jdbcTemplate.query(usersByReviewIdsSql, reviewUserRowMapper, idList);
        Map<Integer, User> map = new HashMap<>();
        for (Review review : reviews) {
            map.put(review.getId(), review.getUser());
        }
        logger.debug("getByReviewIds({}) finished and return users: {}", reviewIds, map);
        return map;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setUsersByReviewIdsSql(String usersByReviewIdsSql) {
        this.usersByReviewIdsSql = usersByReviewIdsSql;
    }

    @Autowired
    public void setReviewUserRowMapper(ReviewUserRowMapper reviewUserRowMapper) {
        this.reviewUserRowMapper = reviewUserRowMapper;
    }
}
