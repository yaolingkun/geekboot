package com.springbootplus.core.aspect;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.springbootplus.core.channels.ChannelTemplate;
import com.springbootplus.core.model.Context;
import com.springbootplus.core.model.Result;

@Aspect
@Component
public class RequestMappingAspect {
	private static final Logger logger = LoggerFactory.getLogger(RequestMappingAspect.class);

	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void advice() {
	}
	@Before("advice()")
	public void before(JoinPoint joinPoint) {
//		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//		HttpServletRequest request = attributes.getRequest();
		
		// 请求类型：get  post   put   delete
//		logger.warn("Method={}", request.getMethod());
		Signature signature = joinPoint.getSignature();
		
//		logger.info(signature.getDeclaringType().toString());//class com.springbootplus.controller.DemoController
		// Class路径
//		String classPath = signature.getDeclaringTypeName();
//		logger.warn("classPath={}", classPath);
		// 类方法名
		String  classMethodName = signature.getName();
//		logger.warn("classMethodName={}", classMethodName);
//		String classMethodPath = classPath + "." + classMethodName+ "()";
//		logger.warn("classMethodPath={}", classMethodPath);

		if(!"execute".equals(classMethodName.trim())){
			throw new RuntimeException("请求绑定方法异常，请使用ActionTemplate的execute(Context context)方法！！！");
		}
		
	}
	@Around("advice()")
	public Object around(ProceedingJoinPoint pj) throws Throwable {
		Thread th=Thread.currentThread();

		logger.info("Tread name:"+th.getName());

		th.setName("我的测试线程：");

		logger.info("Tread name:"+th.getName());
		
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		// --------------------------trans参数------------------------------------------
		// 修改请求参数
		Map<String, String[]> parameterMap = request.getParameterMap();
		// 将请求参数存入context中
		Context context = new Context();
		context.setAllDate(parameterMap);
		// 将context放在切点中
		Object[] args = {context};
		// --------------------------trans日志开始------------------------------------------
		String logParam = ChannelTemplate.getLogParam(request);
		logger.info("trans start: "+logParam);
		// 获取交易执行结果
		Object object = null;
		try{
			object = pj.proceed(args);
			if(!(object instanceof Result)){
				throw new RuntimeException("请求绑定方法返回值类型异常，请使用返回值为com.springbootplus.core.model.Result 的ActionTemplate类的execute(Context context)方法！！！");
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			throw new RuntimeException("请求绑定方法参数异常，请使用ActionTemplate的execute(Context context)方法！！！");
		}
		

		// --------------------------trans日志结束------------------------------------------
		logger.info("trans end: "+logParam);
		return object;
	}
}
