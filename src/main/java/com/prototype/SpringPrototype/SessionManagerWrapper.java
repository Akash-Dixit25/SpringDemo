/*
package com.prototype.SpringPrototype;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SessionManagerWrapper implements Filter {
    private final HttpSessionManager sessionManager;
    public SessionManagerWrapper(HttpSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(false);
        if (session != null) {
// Do something with the session
        }

        // Do something before the request is processed
        chain.doFilter(request, response);
       
    }
@Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Do nothing
    }
    @Override
    public void destroy() {
        // Do nothing
    }

}
*/
