package com.siatsenko.movieland.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class GlobalDefaultExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad request")
    @ExceptionHandler(Exception.class)
    public void handleException(Exception e) {
        logger.error("Exception handler executed {}", e);
    }
}
