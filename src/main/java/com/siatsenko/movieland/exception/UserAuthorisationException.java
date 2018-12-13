package com.siatsenko.movieland.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UserAuthorisationException extends RuntimeException {

    public UserAuthorisationException(String message) {
        super(message);
    }

}
