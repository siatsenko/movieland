package com.siatsenko.movieland.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@ControllerAdvice
class GlobalDefaultExceptionHandler {
    public static final String ERROR_VIEW = "/error";
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = Exception.class)
    public ModelAndView
    defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        logger.error("Error from request : "+ request);
        if (AnnotationUtils.findAnnotation
                (e.getClass(), ResponseStatus.class) != null)
            throw e;

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("timestamp", LocalDateTime.now());
        modelAndView.addObject("path", request.getRequestURL());
        modelAndView.addObject("status", HttpStatus.BAD_REQUEST);
        modelAndView.addObject("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        modelAndView.addObject("message", e);
        modelAndView.setViewName(ERROR_VIEW);
        return modelAndView;
    }
}
