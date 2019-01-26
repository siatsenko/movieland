package com.siatsenko.movieland.service.impl;

import com.siatsenko.movieland.entity.common.Role;
import com.siatsenko.movieland.entity.common.Session;
import com.siatsenko.movieland.entity.common.User;
import com.siatsenko.movieland.exception.UserAuthorisationException;
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
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DefaultAuthService implements AuthService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final User GUEST_USER = new User("guest", "guest", Role.GUEST);

    private UserService userService;
    private Long sessionTimeout;

    private Map<String, Session> tokenSessions = new ConcurrentHashMap<>();

    @Override
    public Session login(String email, String password) {
        User user = userService.getByAuth(email, password);
        if (user == null) {
            logger.debug("login({}) : user not authorized", email);
            throw new UserAuthorisationException("User not authorized");
        }
        Session session = getSession(user);
        if (session == null) {
            session = createSession(user);
        }
        return session;
    }

    @Override
    public User getUser(String token) {
        User user = null;
        if (token != null && tokenSessions.containsKey(token)) {
            user = tokenSessions.get(token).getUser();
        } else {
            user = GUEST_USER;
        }
        logger.trace("getUser({}) finished and return user:{}", token, user);
        return user;
    }

    @Override
    public void logout(String token) {
        tokenSessions.remove(token);
        logger.trace("logout({}) finished", token);
    }

//    @Override
//    public void checkRoleLevel(String token, Role role) {
//        User user = getUser(token);
//        if (user.getRole().ordinal() < role.ordinal()) {
//            throw new InsufficientPermissionsException("Insufficient permissions");
//        }
//    }

    @Override
    public boolean checkRoleLevel(User user, Role role) {
        return user.getRole().ordinal() >= role.ordinal();
    }

    @PostConstruct
    @Scheduled(fixedDelayString = "${auth.session.checkTimeout: 60000}", initialDelayString = "${auth.session.checkTimeout: 60000}") // every minute by default
    private Map<String, Session> refresh() {
        logger.debug("refresh: start");

        Iterator<Map.Entry<String, Session>> iterator = tokenSessions.entrySet().iterator();

        while (iterator.hasNext()) {
            LocalDateTime expireDate = iterator.next().getValue().getExpireDate();
            if (expireDate.isBefore(LocalDateTime.now())) {
                iterator.remove();
            }
        }

        logger.trace("refresh: finished and return result: ", tokenSessions);
        return tokenSessions;
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
