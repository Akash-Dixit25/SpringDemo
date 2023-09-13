package com.prototype.SpringPrototype.session;

import com.prototype.SpringPrototype.exceptionHandler.SessionExpiredException;
import com.prototype.SpringPrototype.session.UserSessionWrapper;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            UserSessionWrapper.validateSession((HttpServletRequest) request);
            chain.doFilter(request, response);
        } catch (SessionExpiredException e) {
            // Handle the session expiration, e.g., redirect to a login page
            request.getRequestDispatcher("/login").forward(request, response);
        }
    }

    // Implement other Filter methods if needed
}
