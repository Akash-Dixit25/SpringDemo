package com.prototype.SpringPrototype.exceptionHandler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public String handleAuthenticationException(AuthenticationException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "login"; // Return the name of your Thymeleaf login template
    }

    // Add more exception handlers for other exceptions as needed
}

