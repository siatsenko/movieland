package com.siatsenko.movieland.web.controller;

import com.siatsenko.movieland.entity.LoginRequest;
import com.siatsenko.movieland.entity.Session;
import com.siatsenko.movieland.service.AuthService;
import com.siatsenko.movieland.web.controller.util.DtoConverter;
import com.siatsenko.movieland.web.dto.LoginUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private AuthService authService;
    private DtoConverter dtoConverter;

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LoginUserDto login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        Session session = authService.login(email, password);
        logger.debug("login returning session:", session);
        return dtoConverter.asLoginUserDto(session);
    }

    @DeleteMapping(path = "/logout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void logout(@RequestHeader(value = "uuid") String token) {
        authService.logout(token);
        logger.debug("logout({})", token);
    }

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setDtoConverter(DtoConverter dtoConverter) {
        this.dtoConverter = dtoConverter;
    }
}
