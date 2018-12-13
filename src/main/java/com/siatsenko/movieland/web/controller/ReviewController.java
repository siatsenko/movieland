package com.siatsenko.movieland.web.controller;

import com.siatsenko.movieland.entity.Review;
import com.siatsenko.movieland.entity.ReviewRequest;
import com.siatsenko.movieland.entity.Role;
import com.siatsenko.movieland.service.ReviewService;
import com.siatsenko.movieland.web.UserHandler;
import com.siatsenko.movieland.web.annotation.ProtectedBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ReviewService reviewService;

    @ProtectedBy(acceptedRole = Role.USER)
    @PostMapping(path = "/review", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void add(@RequestBody ReviewRequest reviewRequest) {
        reviewService.add(reviewRequest, UserHandler.getCurrentUser());
        logger.debug("add {}:", UserHandler.getCurrentUser());
    }

    @Autowired
    public void setReviewService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
}
