package com.prototype.SpringPrototype.service;

import com.prototype.SpringPrototype.UserSessionWrapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class SessionTimeoutService {

    private final UserSessionWrapper userSessionWrapper;

    public SessionTimeoutService(UserSessionWrapper userSessionWrapper) {
        this.userSessionWrapper = userSessionWrapper;
    }

    public boolean isUserLoggedInForMoreThan15Minutes( LocalDateTime lastLoginTime) {
        // Get the user's last login time from your user session or database
       // LocalDateTime lastLoginTime = getUserLastLoginTime();

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

