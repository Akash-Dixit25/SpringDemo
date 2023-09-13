package com.prototype.SpringPrototype;

import com.prototype.SpringPrototype.exceptionHandler.SessionExpiredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class UserSessionWrapper {


    private final SessionRepository<?> sessionRepository;

    private Map<String, LocalDateTime> userSessionData = new HashMap<>();
    public static final String USER_ATTRIBUTE = "user";


    @Autowired
    public UserSessionWrapper(SessionRepository<?> sessionRepository) {
        this.sessionRepository = sessionRepository;
    }


    public String createSession(HttpSession httpSession, String username) {
        Session session = sessionRepository.createSession();
        session.setAttribute("username", username);
        httpSession.setAttribute("sessionId", session.getId());
        return session.getId();
    }

    public Optional<String> getUsername(HttpSession httpSession) {
        String sessionId = (String) httpSession.getAttribute("sessionId");
        if (sessionId != null) {
            Session session = sessionRepository.findById(sessionId);
            if (session != null) {
                return Optional.ofNullable((String) session.getAttribute("user"));
            }
        }
        return Optional.empty();
    }

    public void invalidateSession(HttpSession httpSession) {
        String sessionId = (String) httpSession.getAttribute("sessionId");
        if (sessionId != null) {
            sessionRepository.deleteById(sessionId);
            httpSession.invalidate();
        }
    }

    public static void validateSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(USER_ATTRIBUTE) == null) {
            throw new SessionExpiredException("Session has expired or user is not logged in.");
        }
    }
    public void setLastLoginTime(String username, LocalDateTime lastLoginTime) {
        userSessionData.put(username, lastLoginTime);
    }

    public LocalDateTime getLastLoginTime(String username) {
        return userSessionData.get(username);
    }
}

