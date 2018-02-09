package com.helperok.chat.filter;

import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 2017/9/27.
 *
 * @author LiSijie
 */
public class CorsFilter implements Filter {

    /**
     * Returns {@code true} if the request is a valid CORS one.
     */
    private static boolean isCorsRequest(HttpServletRequest request) {
        return (request.getHeader("Origin") != null);
    }

    /**
     * Returns {@code true} if the request is a valid CORS pre-flight one.
     */
    private static boolean isPreFlightRequest(HttpServletRequest request) {
        return (isCorsRequest(request) &&
                HttpMethod.OPTIONS == HttpMethod.valueOf(request.getMethod().toUpperCase()) &&
                request.getHeader("Access-Control-Request-Method") != null);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "*");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

