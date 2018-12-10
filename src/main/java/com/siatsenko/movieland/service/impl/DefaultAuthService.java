package com.siatsenko.movieland.service.impl;

import com.siatsenko.movieland.entity.Session;
import com.siatsenko.movieland.entity.User;
import com.siatsenko.movieland.exception.UserAutorisationException;
import com.siatsenko.movieland.service.AuthService;
import com.siatsenko.movieland.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class DefaultAuthService implements AuthService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private UserService userService;
    private Long sessionTimeout;

    private volatile Map<String, Session> tokenSessions = new HashMap<>();

    @Override
    public Session login(String email, String password) {
        User user = userService.getByAuth(email, password);
        if (user == null) {
            logger.debug("login({},{}) : user not authorized", email, password);
            throw new UserAutorisationException("User not authorized");
        }
        Session session = getSession(user);
        if (session == null) {
            session = createSession(user);
        }
        return session;
    }

    @Override
    public void logout(String token) {
        tokenSessions.remove(token);
        logger.trace("logout({}) finished", token);
    }

    @PostConstruct
    @Scheduled(fixedDelayString = "${auth.session.checkTimeout: 60000}") // every minute by default
    private Map<String, Session> refresh() {
        logger.debug("refresh: start");
        Map<String, Session> result = new HashMap<>();

        for (Map.Entry<String, Session> tokenSessionEntry : result.entrySet()) {
            LocalDateTime expireDate = tokenSessionEntry.getValue().getExpireDate();
            if (expireDate.isAfter(LocalDateTime.now())) {
                result.put(tokenSessionEntry.getKey(), tokenSessionEntry.getValue());
            }
        }
        tokenSessions = result;
        logger.trace("refresh: finished and return result: ", result);
        return result;
    }

    private Session createSession(User user) {
        String token = UUID.randomUUID().toString();
        LocalDateTime expireDate = LocalDateTime.now().plusSeconds(sessionTimeout);
        Session session = new Session(token, user, expireDate);
        tokenSessions.put(token, session);
        logger.trace("createSession({}) finished and return session: ", user, session);
        return session;
    }

    private Session getSession(User user) {
        Session result = null;
        for (Map.Entry<String, Session> tokenSessionEntry : tokenSessions.entrySet()) {
            Session session = tokenSessionEntry.getValue();
            if (session.getUser().equals(user)) {
                result = session;
                break;
            }
        }
        logger.trace("getSession({}) finished and return session:", user, result);
        return result;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Value("${auth.session.timeout: 120}") // every 2 min by default
    public void setSessionTimeout(Long sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }
}
