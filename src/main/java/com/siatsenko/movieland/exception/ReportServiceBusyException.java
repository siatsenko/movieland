package com.siatsenko.movieland.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE)
public class ReportServiceBusyException extends RuntimeException {
    public ReportServiceBusyException(String message) {
        super(message);
    }

}
