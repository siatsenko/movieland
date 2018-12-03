package com.siatsenko.movieland.service.impl;

import com.siatsenko.movieland.dao.UserDao;
import com.siatsenko.movieland.entity.Review;
import com.siatsenko.movieland.entity.User;
import com.siatsenko.movieland.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DefaultUserService implements UserService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private UserDao userDao;

    @Override
    public List<Review> enrich(List<Review> reviews) {
        List<Integer> listIds = new ArrayList<>();
        for (Review review : reviews) {
            listIds.add(review.getId());
        }
        Map<Integer, User> map = userDao.getByReviewIds(listIds);

        for (Review review : reviews) {
            review.setUser(map.get(review.getId()));
        }
        logger.trace("enrich(count={}) finished and enrich reviews: {}", reviews.size(), reviews);
        return reviews;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
