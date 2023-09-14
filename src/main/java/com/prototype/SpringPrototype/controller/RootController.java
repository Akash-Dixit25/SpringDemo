package com.prototype.SpringPrototype.controller;


import com.prototype.SpringPrototype.exceptionHandler.LoginException;
import com.prototype.SpringPrototype.exceptionHandler.UserAccessException;
import com.prototype.SpringPrototype.service.SessionTimeoutService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.prototype.SpringPrototype.session.UserSessionWrapper;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Optional;


@Controller
@Slf4j
public class RootController {


    private final UserSessionWrapper userSessionWrapper;
    private final SessionTimeoutService sessionTimeoutService;

    public RootController(UserSessionWrapper userSessionWrapper, SessionTimeoutService sessionTimeoutService) {
        this.userSessionWrapper = userSessionWrapper;
        this.sessionTimeoutService = sessionTimeoutService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "message", required = false) String message, Model model, HttpSession session) {
        if (message != null)
            model.addAttribute("message", message);
        return "login";
    }

    @PostMapping("/userLogin")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session) {
        log.info("Inside userLogin Controller");

        if ("user".equals(username) && "password".equals(password)) {
            // Redirect to user or admin page based on roles (you can extend this logic)
            String sessionId = userSessionWrapper.createSession(session, username);
            session.setAttribute("user", username);
            session.setAttribute("loginTime", System.currentTimeMillis());
            LocalDateTime lastLoginTime = LocalDateTime.now();
            userSessionWrapper.setLastLoginTime(username, lastLoginTime);
            log.info("sessionId: {} ", sessionId);

            return "redirect:/userPage";

        } else if ("admin".equals(username) && "admin123".equals(password)) {
            String sessionId = userSessionWrapper.createSession(session, username);
            session.setAttribute("user", username);
            session.setAttribute("loginTime", System.currentTimeMillis());
            LocalDateTime lastLoginTime = LocalDateTime.now();
            userSessionWrapper.setLastLoginTime(username, lastLoginTime);
            log.info("sessionId: {} ", sessionId);
            return "redirect:/adminPage";
        }
        else if ("user2".equals(username) && "password".equals(password)) {
            String sessionId = userSessionWrapper.createSession(session, username);
            session.setAttribute("user", username);
            session.setAttribute("loginTime", System.currentTimeMillis());
            LocalDateTime lastLoginTime = LocalDateTime.now();
            userSessionWrapper.setLastLoginTime(username, lastLoginTime);
            log.info("sessionId: {} ", sessionId);
            return "redirect:/userPage";
        }

        else {
            // Redirect to the login page with an error message
            log.info("Inside the error");
            throw new LoginException("Invalid username or password");

        }
    }

    @GetMapping("/logout")
    public String logout(@RequestParam(value = "message", required = false) String message, Model model, HttpSession session) {
        session.setAttribute("logged_in", false);
        if (message != null)
            model.addAttribute("message", message);
        userSessionWrapper.invalidateSession(session);
        return "redirect:/home";

    }

    @GetMapping("/home")
    public String homePage(HttpSession session) throws AuthenticationException {
        // Verify if the user is logged in
        if (session.getAttribute("user") == null) {
            return "redirect:/home";
        }
        Optional<String> username = userSessionWrapper.getUsername(session);
        log.info("LoggedIn user: {} {} ", session.getAttribute("user"),session.getAttribute("userData"));

        // Check if the user has been logged in for more than 15 minutes

        boolean isLoggedOut = sessionTimeoutService.isUserLoggedInForMoreThan15Minutes(userSessionWrapper.getLastLoginTime((String) session.getAttribute("user")));
        if (isLoggedOut) {
            // Logout the user
            session.invalidate();

            return "redirect:/login?timeout=true";
        }
        // Display the home page
        return "home";
    }

    @GetMapping("/userPage")
    public String userPage(HttpSession session) {
        // Verify if the user is logged in
        log.info("LoggedIn user userpage: {} ", session.getAttribute("user"));

        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        log.info("LoggedIn user: {} ", session.getAttribute("user"));

        // Check if the user has been logged in for more than 15 minutes

        boolean isLoggedOut = sessionTimeoutService.isUserLoggedInForMoreThan15Minutes(userSessionWrapper.getLastLoginTime((String) session.getAttribute("user")));
        if (isLoggedOut) {
            log.info("Session Timeout Occurred");
            session.invalidate();
            return "redirect:/login?timeout=true";
        }
        return "user";
    }

    @GetMapping("/adminPage")
    public String adminPage(HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }else if ("user".equals((session.getAttribute("user")))||"user2".equals((session.getAttribute("user")))) {
            throw new UserAccessException("Access Denied!");
        }
        // Check if the user has been logged in for more than 15 minutes
        Optional<String> username = userSessionWrapper.getUsername(session);
        log.info("LoggedIn user: {} ", session.getAttribute("user"));
        boolean isLoggedOut = sessionTimeoutService.isUserLoggedInForMoreThan15Minutes(userSessionWrapper.getLastLoginTime((String) session.getAttribute("user")));
        if (isLoggedOut) {
            session.invalidate();
            return "redirect:/login?timeout=true";
        }
        return "admin";
    }


    @GetMapping("/product")
    public String getProductManagementPage(HttpSession session) {
        log.info("LoggedIn user product page: {} ", session.getAttribute("user"));
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        if ("user".equals((session.getAttribute("user")))||"user2".equals((session.getAttribute("user")))) {
            throw new UserAccessException("Access Denied! Only Admin can view this page.");

        }
        return "product";
    }

    @GetMapping("/DMSI")
    public String DMSIguideline(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        return "dmsi";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

}
