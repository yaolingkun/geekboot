package com.springbootplus.core.exception;

import java.io.FileNotFoundException;

import javax.lang.model.type.UnknownTypeException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public Object errorHandler(HttpServletRequest request,Exception e){
		logger.info("-----捕获到异常-----");
		//ajax请求异常返回
//		if(null != request.getHeader("X-Requested-With") && "XMLHttpRequest".equals( request.getHeader("X-Requested-With").toString())){
//			if (e instanceof NullPointerException) {
//				return PageReturnUtils.formatNormal("", PageReturnUtils.MSG_CODE_111111, PageReturnUtils.MSG_STATE_FALSE, "数据参数传入错误");
//			} else if (e instanceof FileNotFoundException) {
//				return PageReturnUtils.formatNormal("", PageReturnUtils.MSG_CODE_111111, PageReturnUtils.MSG_STATE_FALSE, "数据文件上传出错");
//			} else if (e instanceof ClassNotFoundException) {
//				return PageReturnUtils.formatNormal("", PageReturnUtils.MSG_CODE_111111, PageReturnUtils.MSG_STATE_FALSE, "系统未找到该jar包");
//			} else if (e instanceof UnknownTypeException) {
//				return PageReturnUtils.formatNormal("", PageReturnUtils.MSG_CODE_111111, PageReturnUtils.MSG_STATE_FALSE, "未知错误");
//			} else{
//				return PageReturnUtils.formatNormal("", PageReturnUtils.MSG_CODE_111111, PageReturnUtils.MSG_STATE_FALSE, e.toString());
//			}
//		}
//		return PageReturnUtils.formatNormal("", PageReturnUtils.MSG_CODE_111111, PageReturnUtils.MSG_STATE_FALSE, e.toString());
		return null;
	}
}
