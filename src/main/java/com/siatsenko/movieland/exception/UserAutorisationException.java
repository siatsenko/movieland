package com.siatsenko.movieland.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UserAutorisationException extends RuntimeException {

    public UserAutorisationException(String message) {
        super(message);
    }

}
