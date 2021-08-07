package com.wof.wiki.wofwikiplatform.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 
 * @ClassName LogFilter
 * @Author admin
 * @date 2021.08.02 18:07
 */
public class LogFilter extends OncePerRequestFilter {
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Request Filter init...");
        filterChain.doFilter(request, response);
        System.out.println("Request Filter complete.");
    }
}
