package com.springbootplus.core.util;

public class StringUtils {
	/**
	 * 获取竖线分割字符串
	 * @return
	 */
	public static String getSingleVerticalLine(){
		return "|";
	}
	/**
	 * 判断字符串是否为不空
	 * @param arg
	 * @return
	 */
	public static boolean isNotEmpty(StringBuffer  arg){
		if( null == arg || arg.toString().isEmpty()){
			return false ;
		}
		return true;
	}
	
	/**
	 * 判断字符串是否为空
	 * @param arg
	 * @return
	 */
	public static boolean isEmpty(StringBuffer  arg){
		return !isNotEmpty(arg);
	}
	
	/**
	 * 判断字符串是否为不空
	 * @param arg
	 * @return
	 */
	public static boolean isNotEmpty(String  arg){
		if( null == arg || arg.toString().isEmpty()){
			return false ;
		}
		return true;
	}
	
	/**
	 * 判断字符串是否为空
	 * @param arg
	 * @return
	 */
	public static boolean isEmpty(String  arg){
		return !isNotEmpty(arg);
	}
	
	
	
	
	public static String argToLowerCase(String arg){
		if(isNotEmpty(arg))
		return arg.toLowerCase();
		return arg;
	}
	
	/**
	 * url为空、url包含过滤(不需要拦截)的字符串返回false
	 * @param url
	 * @return
	 */
	public static boolean importantURL(StringBuffer url){
		if(StringUtils.isEmpty(url)){
			return false;
		}
		String[] excludeField = {"swagger","error"};
		for(String item : excludeField){
			if(url.toString().contains(item)){
				return false;
			}
		}
		return true;
	}
}
