package com.siatsenko.movieland.service.impl;

import com.siatsenko.movieland.dao.ReviewDao;
import com.siatsenko.movieland.entity.*;
import com.siatsenko.movieland.service.AuthService;
import com.siatsenko.movieland.service.ReviewService;
import com.siatsenko.movieland.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultReviewService implements ReviewService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ReviewDao reviewDao;
    private UserService userService;
    private AuthService authService;

    @Override
    public Movie enrich(Movie movie) {
        List<Review> reviews = reviewDao.getByMovieId(movie.getId());
        movie.setReviews(reviews);
        userService.enrich(reviews);
        logger.trace("enrich({}) finished and enrich reviews: {}", movie.getId(), reviews);
        return movie;
    }

    @Override
    public void add(ReviewRequest reviewRequest, User user) {

        int movieId = reviewRequest.getMovieId();
        String text = reviewRequest.getText();

        Review review = new Review();
        review.setUser(user);
        review.setText(text);

        reviewDao.add(movieId, review);
    }


    @Autowired
    public void setReviewDao(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }
}
