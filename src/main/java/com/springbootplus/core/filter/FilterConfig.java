package com.springbootplus.core.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
	@Value("${jsonp-filter-urlPatterns:/*}")
    private String patterns;
	
	@Bean
    public FilterRegistrationBean<LoggerFilter> setLoggerFilter() {
        FilterRegistrationBean<LoggerFilter> filterRegistrationBean = new FilterRegistrationBean<LoggerFilter>();
        filterRegistrationBean.setFilter(new LoggerFilter());//注册的是哪个过滤器
        filterRegistrationBean.addUrlPatterns("/*");// 过滤哪些请求
        filterRegistrationBean.setName("LoggerFilter"); // 过滤器名字
        filterRegistrationBean.setOrder(10);   //order的数值越小，在所有的filter中优先级越高
        return filterRegistrationBean;
    }
	
	
	
}
