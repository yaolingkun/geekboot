package com.springbootplus.core.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
	@Bean
    public FilterRegistrationBean<OptionsRequestFilter> setOptionsRequestFilter() {
        FilterRegistrationBean<OptionsRequestFilter> filterRegistrationBean = new FilterRegistrationBean<OptionsRequestFilter>();
        filterRegistrationBean.setFilter(new OptionsRequestFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(10);   //order的数值越小，在所有的filter中优先级越高
        return filterRegistrationBean;
    }
	
	
	@Bean
    public FilterRegistrationBean<TimeFilter> setTimeFilter() {
        FilterRegistrationBean<TimeFilter> filterRegistrationBean = new FilterRegistrationBean<TimeFilter>();
        filterRegistrationBean.setFilter(new TimeFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(100);   //order的数值越小，在所有的filter中优先级越高
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<SensitiveWordsFilter> setSensitiveWordsFilter(){
        FilterRegistrationBean<SensitiveWordsFilter> filterRegistrationBean = new FilterRegistrationBean<SensitiveWordsFilter>();
        filterRegistrationBean.setFilter(new SensitiveWordsFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(200);   //order的数值越小，在所有的filter中优先级越高
        return filterRegistrationBean;
    }
	
	
}
