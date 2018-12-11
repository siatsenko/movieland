package com.siatsenko.movieland.interceptor;

import com.siatsenko.movieland.entity.User;
import com.siatsenko.movieland.service.AuthService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MDC.put("requestId", UUID.randomUUID().toString());

        String token = request.getHeader("uuid");
        User user = authService.getUser(token);
        String userLogin = user.getEmail();

        MDC.put("login", userLogin);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        MDC.clear();
    }

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }
}
