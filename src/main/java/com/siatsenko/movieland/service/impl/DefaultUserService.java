package com.siatsenko.movieland.service.impl;

import com.siatsenko.movieland.dao.UserDao;
import com.siatsenko.movieland.entity.common.Review;
import com.siatsenko.movieland.entity.common.User;
import com.siatsenko.movieland.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DefaultUserService implements UserService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private UserDao userDao;

    @Override
    public List<Review> enrich(List<Review> reviews) {
        List<Integer> userIds = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (Review review : reviews) {
            int reviewId = review.getId();
            int userId = review.getUser().getId();
            userIds.add(userId);
            map.put(userId, reviewId);
        }

        List<User> users = userDao.getByIds(userIds);
        for (User user : users) {
            int userId = user.getId();
            int reviewId = map.get(userId);
            for (Review review : reviews) {
                if (review.getId() == reviewId) {
                    review.setUser(user);
                    break;
                }
            }
        }
        logger.trace("enrich(count={}) finished and enrich reviews: {}", reviews.size(), reviews);
        return reviews;
    }

    @Override
    public User getByAuth(String email, String password) {
        User user = userDao.getByAuth(email, password);
        logger.debug("getByAuth({}) finished and return user:", email, user);
        return user;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
