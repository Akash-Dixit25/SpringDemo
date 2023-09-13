package com.prototype.SpringPrototype.service;

import com.prototype.SpringPrototype.session.UserSessionWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@Slf4j

public class SessionTimeoutService {

    private final UserSessionWrapper userSessionWrapper;

    public SessionTimeoutService(UserSessionWrapper userSessionWrapper) {
        this.userSessionWrapper = userSessionWrapper;
    }

    public boolean isUserLoggedInForMoreThan15Minutes( LocalDateTime lastLoginTime) {
        if (lastLoginTime != null) {
            LocalDateTime currentTime = LocalDateTime.now();
            Duration sessionDuration = Duration.between(lastLoginTime, currentTime);
            // Check if the session duration is greater than 15 minutes
            return sessionDuration.toMinutes() > 15;
        }
        // Return false if the user's last login time is not available
        return false;
    }

}

