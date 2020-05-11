package com.springbootplus.core.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.OncePerRequestFilter;
/**
 * OptionsRequest
 * @author admin
 *
 */
public class OptionsRequestFilter extends OncePerRequestFilter{
	private static final Logger logger = LoggerFactory.getLogger(OptionsRequestFilter.class);
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 指定允许其他域名访问
		response.setHeader("Access-Control-Allow-Origin", "*");
		// 响应类型
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, OPTIONS, DELETE");
		// 响应头设置
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with, X-Custom-Header, HaiYi-Access-Token");
		
		String method = request.getMethod();
		logger.debug("method = "+method);
        //如果是 OPTIONS 请求，则放行
       if (HttpMethod.OPTIONS.toString().equals(method)){
    	   filterChain.doFilter(request, response);
         return;
       }
       filterChain.doFilter(request, response);
	}

}
