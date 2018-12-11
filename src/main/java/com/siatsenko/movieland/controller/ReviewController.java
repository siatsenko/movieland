package com.siatsenko.movieland.controller;

import com.siatsenko.movieland.entity.ReviewRequest;
import com.siatsenko.movieland.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ReviewService reviewService;

    @PostMapping(path = "/review", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void add(@RequestHeader(value = "uuid") String token, @RequestBody ReviewRequest reviewRequest) {
        reviewService.add(reviewRequest, token);
        logger.debug("add {}:", token);
    }

    @Autowired
    public void setReviewService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
}
