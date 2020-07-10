package com.springbootplus.core.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.springbootplus.core.logger.Logger;
import com.springbootplus.core.logger.LoggerFactory;

public class LoggerFilter extends OncePerRequestFilter{
	private static final Logger logger = LoggerFactory.getLogger();
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		logger.info(request);
		filterChain.doFilter(request, response);
		logger.info(request);
	}
	
}
