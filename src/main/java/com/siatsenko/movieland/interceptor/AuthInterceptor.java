package com.siatsenko.movieland.interceptor;

import com.siatsenko.movieland.entity.common.User;
import com.siatsenko.movieland.exception.InsufficientPermissionsException;
import com.siatsenko.movieland.service.AuthService;
import com.siatsenko.movieland.web.UserHandler;
import com.siatsenko.movieland.web.annotation.ProtectedBy;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
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

        checkPermissions(handler, user);

        MDC.put("login", userLogin);

        UserHandler.setCurrentUser(user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        MDC.clear();
    }

    private void checkPermissions(Object handler, User user) {
        if (handler instanceof HandlerMethod) {
            Method method = ((HandlerMethod) handler).getMethod();
            ProtectedBy protectedBy = method.getAnnotation(ProtectedBy.class);
            if (protectedBy != null) {
                if (!authService.checkRoleLevel(user, protectedBy.acceptedRole())) {
                    throw new InsufficientPermissionsException("Insufficient permissions for " + user.getEmail());
                }
            }
        }
    }


    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }
}
