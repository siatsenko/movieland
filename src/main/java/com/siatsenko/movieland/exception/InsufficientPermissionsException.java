package com.siatsenko.movieland.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class InsufficientPermissionsException extends RuntimeException {

    public InsufficientPermissionsException(String message) {
        super(message);
    }

}
