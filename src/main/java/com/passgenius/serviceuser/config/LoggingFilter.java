package com.passgenius.serviceuser.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

public class LoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        logger.info("HTTP Request: {} users{}", request.getMethod(), request.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);
        logger.info("HTTP Response Status: {}", response.getStatus());
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
