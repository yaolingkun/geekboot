package com.springbootplus.core.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 时间
 * @author admin
 *
 */
public class TimeFilter extends OncePerRequestFilter{
	private static final Logger logger = LoggerFactory.getLogger(TimeFilter.class);
//	/**
//     * 可以初始化Filter在web.xml里面配置的初始化参数
//     * filter对象只会创建一次，init方法也只会执行一次。
//     * @param filterConfig
//     * @throws ServletException
//     */
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        logger.info("我是过滤器的初始化方法！生活开始.........");
//    }
//	/**
//     * 主要的业务代码编写方法
//     * @param servletRequest
//     * @param servletResponse
//     * @param filterChain
//     * @throws IOException
//     * @throws ServletException
//     */
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        logger.info("我是过滤器的执行方法，客户端向Servlet发送的请求被我拦截到了");
//        filterChain.doFilter(servletRequest, servletResponse);
//        logger.info("我是过滤器的执行方法，Servlet向客户端发送的响应被我拦截到了");
//    }
	/**
     * 在销毁Filter时自动调用。
     */
    @Override
    public void destroy() {
        logger.info("我是TimeFilter过滤器的被销毁时调用的方法！，活不下去了................" );
    }
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		logger.info("我是TimeFilter过滤器的执行方法，客户端向Servlet发送的请求被我拦截到了");
        filterChain.doFilter(request, response);
        logger.info("我是TimeFilter过滤器的执行方法，Servlet向客户端发送的响应被我拦截到了");
		
	}



}
