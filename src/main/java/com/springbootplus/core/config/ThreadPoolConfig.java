package com.springbootplus.core.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import com.springbootplus.core.model.VisiableThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ThreadPoolConfig {

	@Bean
	public Executor asyncServiceExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new VisiableThreadPoolTaskExecutor();
		// 核心线程数
		threadPoolTaskExecutor.setCorePoolSize(5);
		// allowCoreThreadTimeOut为true
		// 该值为true，则线程池数量最后销毁到0个。
		// allowCoreThreadTimeOut为false
		// 销毁机制：超过核心线程数时，而且（超过最大值或者timeout过），就会销毁。
		threadPoolTaskExecutor.setAllowCoreThreadTimeOut(false);
		// 最大线程数
		threadPoolTaskExecutor.setMaxPoolSize(5);
		// 配置队列大小
		threadPoolTaskExecutor.setQueueCapacity(50);
		// 配置线程池前缀
		threadPoolTaskExecutor.setThreadNamePrefix("async-service-");
		// 拒绝策略
		// rejection-policy：当pool已经达到max size的时候，如何处理新任务
		// 第一种AbortPolicy:不执行新任务，直接抛出异常，提示线程池已满
		// 第二种DisCardPolicy:不执行新任务，也不抛出异常
		// 第三种DisCardOldSetPolicy:将消息队列中的第一个任务替换为当前新进来的任务执行
		// 第四种CallerRunsPolicy:直接调用execute来执行当前任务,被拒绝的任务在主线程中运行，所以主线程就被阻塞了，别的任务只能在被拒绝的任务执行完之后才会继续被提交到线程池执行。
		threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
		// threadPoolTaskExecutor.setRejectedExecutionHandler(new
		// PrintingPolicy());
		threadPoolTaskExecutor.initialize();
		return threadPoolTaskExecutor;
	}

	@Bean
	public Executor customServiceExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		// 线程核心数目
		threadPoolTaskExecutor.setCorePoolSize(10);
		threadPoolTaskExecutor.setAllowCoreThreadTimeOut(false);
		// 最大线程数
		threadPoolTaskExecutor.setMaxPoolSize(10);
		// 配置队列大小
		threadPoolTaskExecutor.setQueueCapacity(50);
		// 配置线程池前缀
		threadPoolTaskExecutor.setThreadNamePrefix("custom-service-");
		// 配置拒绝策略
		threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
		// 数据初始化
		threadPoolTaskExecutor.initialize();
		return threadPoolTaskExecutor;
	}
	//springboot开启定时任务启动输出异常，但功能正常 NoSuchBeanDefinitionException: No qualifying bean of type 'java.util.concurrent.ScheduledExecutorService' available
	//第一种是眼不见为净法，简单粗暴地修改日志级别就好。
	//第二种，既然提示的异常是注册的bean中找不到TaskScheduler，那么我们就注册TaskScheduler。
	@Bean
    public TaskScheduler scheduledExecutorService() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(8);
        scheduler.setThreadNamePrefix("scheduled-thread-");
        return scheduler;
    }
}
