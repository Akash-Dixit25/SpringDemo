package com.prototype.SpringPrototype.exceptionHandler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public String handleAuthenticationException(AuthenticationException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "login"; // Return the name of your Thymeleaf login template
    }
    @ExceptionHandler(LoginException.class)
    public ModelAndView handleLoginException(LoginException ex) {
        ModelAndView modelAndView = new ModelAndView("login"); // Specify the login page
        modelAndView.addObject("error", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(UserAccessException.class)
    public ModelAndView handleAccessException(UserAccessException ex) {
        ModelAndView modelAndView = new ModelAndView("error"); // Specify the login page
        modelAndView.addObject("error", ex.getMessage());
        return modelAndView;
    }

    // Add more exception handlers for other exceptions as needed
}

