package com.hotel.core.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //根据请求头设置响应头
        String allowOrigin = request.getHeader("Origin");
        String allowHeader = request.getHeader("Access-Control-Request-Headers");
        response.setHeader("Access-Control-Allow-Origin", allowOrigin);
        //设置允许带cookie的请求
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "OPTIONS, POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", allowHeader);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}

}
