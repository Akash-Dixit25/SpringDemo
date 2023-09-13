package com.prototype.SpringPrototype.controller;



import com.prototype.SpringPrototype.exceptionHandler.CustomAuthenticationEntryPoint;
import com.prototype.SpringPrototype.service.SessionTimeoutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.prototype.SpringPrototype.UserSessionWrapper;

import javax.security.sasl.AuthenticationException;
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
	public String login(@RequestParam(value = "message", required = false) String message, Model model, HttpSession session ) {
		if(message != null)
			model.addAttribute("message", message);
		return "login";
	}
	
	@PostMapping("/userLogin")
	public String login(@RequestParam("username") String username,
	                        @RequestParam("password") String password,
	                        HttpSession session) {
		log.info("Inside the method");
		log.info("Username is : " +username);
		log.info("password is : " +password);

	        // Verify if the user is already logged in  commenting because for other users its not throwing error
	      /*  if (session.getAttribute("user") == null) {
				log.info("Inside the home");
	            return "redirect:/home";
	        }*/
		String sessionId = userSessionWrapper.createSession(session, username);
		log.info("sessionId: {} ", sessionId);

		if ("user".equals(username) && "password".equals(password)) {
			// Redirect to user or admin page based on roles (you can extend this logic)
			log.info("user login");
			session.setAttribute("user", username);
			session.setAttribute("loginTime", System.currentTimeMillis());
			LocalDateTime lastLoginTime = LocalDateTime.now();
			userSessionWrapper.setLastLoginTime(username, lastLoginTime);
			return "redirect:/userPage";
		} else if ("admin".equals(username) && "admin123".equals(password)) {
			session.setAttribute("user", username);
			session.setAttribute("loginTime", System.currentTimeMillis());
			LocalDateTime lastLoginTime = LocalDateTime.now();
			userSessionWrapper.setLastLoginTime(username, lastLoginTime);
			return "redirect:/adminPage";
		}
          else {
	            // Redirect to the login page with an error message
			
			log.info("Inside the error");
	            return "redirect:/login?error=true";
	        }
	    }
	@GetMapping("/logout")
	public String logout(@RequestParam(value = "message", required = false) String message, Model model, HttpSession session) {
		session.setAttribute("logged_in", false);
	if(message != null)
		model.addAttribute("message", message);
		userSessionWrapper.invalidateSession(session);
	return "redirect:/home";
	
	}
	    @GetMapping("/home")
	    public String homePage(HttpSession session) {
	        // Verify if the user is logged in
	        if (session.getAttribute("user") == null) {
	            return "redirect:/home";
	        }
			Optional<String> username = userSessionWrapper.getUsername(session);
			log.info("LoggedIn user: {} ", session.getAttribute("user"));

	        // Check if the user has been logged in for more than 15 minutes

			boolean isLoggedOut = sessionTimeoutService.isUserLoggedInForMoreThan15Minutes( userSessionWrapper.getLastLoginTime((String) session.getAttribute("user")));
	        if (isLoggedOut) {
	            // Logout the user
	            session.invalidate();
	            return "redirect:/login?timeout=true";
	        }
	        // Display the home page
	        return "login";
	    }

	@GetMapping("/userPage")
	public String userPage(HttpSession session) {
		// Verify if the user is logged in
		if (session.getAttribute("user") == null) {
			return "redirect:/userPage";
		}
		Optional<String> username = userSessionWrapper.getUsername(session);
		log.info("LoggedIn user: {} ", session.getAttribute("user"));

		// Check if the user has been logged in for more than 15 minutes

		boolean isLoggedOut = sessionTimeoutService.isUserLoggedInForMoreThan15Minutes( userSessionWrapper.getLastLoginTime((String) session.getAttribute("user")));
		if (isLoggedOut) {
			// Logout the user
			session.invalidate();
			return "redirect:/login?timeout=true";
		}
		// Display the home page
		return "user";
	}

	@GetMapping("/adminPage")
	public String adminPage(HttpSession session) {
		// Verify if the admin is logged in
		if (session.getAttribute("user") == null) {
			return "redirect:/userPage";
		}
		Optional<String> username = userSessionWrapper.getUsername(session);
		log.info("LoggedIn user: {} ", session.getAttribute("user"));

		// Check if the user has been logged in for more than 15 minutes

		boolean isLoggedOut = sessionTimeoutService.isUserLoggedInForMoreThan15Minutes( userSessionWrapper.getLastLoginTime((String) session.getAttribute("user")));
		if (isLoggedOut) {
			// Logout the user
			session.invalidate();
			return "redirect:/login?timeout=true";
		}
		// Display the home page
		return "admin";
	}



}
