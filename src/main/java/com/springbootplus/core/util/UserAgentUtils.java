/**
 *  Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 *  @Package: com.svimer.core.utils 
 *  @ClassName: UserAgentUtil 
 *  @Description: TODO
 *  @author: Svimer 
 *  @date: 2019年9月19日 下午9:33:07 
 */
package com.springbootplus.core.util;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;


/**
 * 
 *
 */
public class UserAgentUtils {

//private static Logger logger = LoggerFactory.getLogger(UserAgentUtils.class);
	
	/**
	 * 根据http获取userAgent信息
	 * @param request
	 * @return
	 */
	public static String getUserAgent(HttpServletRequest request) {
		String userAgent=request.getHeader("User-Agent");
		return userAgent;
	}
	
	/**
	 * 根据request获取userAgent，然后解析出osVersion
	 * @param request
	 * @return
	 */
	public static String getOsVersion(HttpServletRequest request) {
		String userAgent = getUserAgent(request);
		return getOsVersion(userAgent);
	}
	
	/**
	 * 根据userAgent解析出osVersion
	 * @param userAgent
	 * @return
	 */
	public static String getOsVersion(String userAgent) {
		String osVersion = "";
		if(StringUtils.isBlank(userAgent)) 
			return osVersion;
		String[] strArr = userAgent.substring(userAgent.indexOf("(")+1,
				userAgent.indexOf(")")).split(";");
		if(null == strArr || strArr.length == 0) 
			return osVersion;
		
		osVersion = strArr[1];
//		logger.info("osVersion is:{}", osVersion);
		return osVersion;
	}
	
	/**
	 * 获取操作系统对象
	 * @param request
	 * @return
	 */
	private static OperatingSystem getOperatingSystem(String userAgent) {
		UserAgent agent = UserAgent.parseUserAgentString(userAgent);
		OperatingSystem operatingSystem = agent.getOperatingSystem();
		return operatingSystem;
	}
	

	
	/**
	 * 获取os:Windows/ios/Android
	 * @param request
	 * @return
	 */
	public static String getOs(HttpServletRequest request) {
		String userAgent = getUserAgent(request);
		return getOs(userAgent);
	}
	
	/**
	 * 获取os:Windows/ios/Android
	 * @param request
	 * @return
	 */
	public static String getOs(String userAgent) {
		OperatingSystem operatingSystem =  getOperatingSystem(userAgent);
		String os = operatingSystem.getGroup().getName();
//		logger.info("os is:{}", os);
		return os;
	}
	
	
	/**
	 * 获取deviceType
	 * @param request
	 * @return
	 */
	public static String getDevicetype(HttpServletRequest request) {
		String userAgent = getUserAgent(request);
		return getDevicetype(userAgent);
	}
	
	/**
	 * 获取deviceType
	 * @param request
	 * @return
	 */
	public static String getDevicetype(String userAgent) {
		OperatingSystem operatingSystem =  getOperatingSystem(userAgent);
		String deviceType = operatingSystem.getDeviceType().toString();
//		logger.info("deviceType is:{}", deviceType);
		return deviceType;
	}

	/**
	 * 获取操作系统的名字
	 * @param request
	 * @return
	 */
	public static String getOsName(HttpServletRequest request) {
		String userAgent = getUserAgent(request);
		return getOsName(userAgent);
	}
	
	/**
	 * 获取操作系统的名字
	 * @param request
	 * @return
	 */
	public static String getOsName(String userAgent) {
		OperatingSystem operatingSystem =  getOperatingSystem(userAgent);
		String osName = operatingSystem.getName();
//		logger.info("osName is:{}", osName);
		return osName;
	}
	
	
	/**
	 * 获取device的生产厂家
	 * @param request
	 */
	public static String getDeviceManufacturer(HttpServletRequest request) {
		String userAgent = getUserAgent(request);
		return getDeviceManufacturer(userAgent);
	}
	
	/**
	 * 获取device的生产厂家
	 * @param request
	 */
	public static String getDeviceManufacturer(String userAgent) {
		OperatingSystem operatingSystem =  getOperatingSystem(userAgent);
		String deviceManufacturer = operatingSystem.getManufacturer().toString();
//		logger.info("deviceManufacturer is:{}", deviceManufacturer);
		return deviceManufacturer;
	}
	
	/**
	 * 获取浏览器对象
	 * @param request
	 * @return
	 */
	public static Browser getBrowser(String agent) {
		UserAgent userAgent = UserAgent.parseUserAgentString(agent);
		Browser browser = userAgent.getBrowser();
		return browser;
	}

	
	/**
	 * 获取browser name
	 * @param request
	 * @return
	 */
	public static String getBorderName(HttpServletRequest request) {
		String userAgent = getUserAgent(request);
		return getBorderName(userAgent);
	}
	
	/**
	 * 获取browser name
	 * @param request
	 * @return
	 */
	public static String getBorderName(String userAgent) {
		Browser browser =  getBrowser(userAgent);
		String borderName = browser.getName();
//		logger.info("borderName is:{}", borderName);
		return borderName;
	}
	
	
	/**
	 * 获取浏览器的类型
	 * @param request
	 * @return
	 */
	public static String getBorderType(HttpServletRequest request) {
		String userAgent = getUserAgent(request);
		return getBorderType(userAgent);
	}
	
	/**
	 * 获取浏览器的类型
	 * @param request
	 * @return
	 */
	public static String getBorderType(String userAgent) {
		Browser browser =  getBrowser(userAgent);
		String borderType = browser.getBrowserType().getName();
//		logger.info("borderType is:{}", borderType);
		return borderType;
	}
	
	/**
	 * 获取浏览器组: CHROME、IE
	 * @param request
	 * @return
	 */
	public static String getBorderGroup(HttpServletRequest request) {
		String userAgent = getUserAgent(request);
		return getBorderGroup(userAgent);
	}
	
	/**
	 * 获取浏览器组: CHROME、IE
	 * @param request
	 * @return
	 */
	public static String getBorderGroup(String userAgent) {
		Browser browser =  getBrowser(userAgent);
		String browerGroup = browser.getGroup().getName();
//		logger.info("browerGroup is:{}", browerGroup);
		return browerGroup;
	}
	
	/**
	 * 获取浏览器的生产厂商
	 * @param request
	 * @return
	 */
	public static String getBrowserManufacturer(HttpServletRequest request) {
		String userAgent = getUserAgent(request);
		return getBrowserManufacturer(userAgent);
	}
	
	
	/**
	 * 获取浏览器的生产厂商
	 * @param request
	 * @return
	 */
	public static String getBrowserManufacturer(String userAgent) {
		Browser browser =  getBrowser(userAgent);
		String browserManufacturer = browser.getManufacturer().getName();
//		logger.info("browserManufacturer is:{}", browserManufacturer);
		return browserManufacturer;
	}
	
	
	/**
	 * 获取浏览器使用的渲染引擎
	 * @param request
	 * @return
	 */
	public static String getBorderRenderingEngine(HttpServletRequest request) {
		String userAgent = getUserAgent(request);
		return getBorderRenderingEngine(userAgent);
	}
	
	/**
	 * 获取浏览器使用的渲染引擎
	 * @param request
	 * @return
	 */
	public static String getBorderRenderingEngine(String userAgent) {
		Browser browser =  getBrowser(userAgent);
		String renderingEngine = browser.getRenderingEngine().name();
//		logger.info("renderingEngine is:{}", renderingEngine);
		return renderingEngine;
	}
	
	
	/**
	 * 获取浏览器版本
	 * @param request
	 * @return
	 */
	public static String getBrowserVersion(HttpServletRequest request) {
		String userAgent = getUserAgent(request);
		return getBrowserVersion(userAgent);
	}
	
	/**
	 * 获取浏览器版本
	 * @param request
	 * @return
	 */
	public static String getBrowserVersion(String userAgent) {
		Browser browser =  getBrowser(userAgent);
		String borderVersion = browser.getVersion( userAgent).toString();
		return borderVersion;
	}
	
	/**
	 * 
	 */
	public static StringBuffer getUserAgentInfo(HttpServletRequest request){
		StringBuffer userAgentInfo = new StringBuffer();
		String userAgent = getUserAgent(request);
		
		userAgentInfo.append("浏览器组:"+getBorderGroup(userAgent));
		userAgentInfo.append(com.springbootplus.core.util.StringUtils.getSingleVerticalLine());
		userAgentInfo.append("浏览器名字:"+getBorderName(userAgent));
		userAgentInfo.append(com.springbootplus.core.util.StringUtils.getSingleVerticalLine());
		userAgentInfo.append("浏览器类型:"+getBorderType(userAgent));
		userAgentInfo.append(com.springbootplus.core.util.StringUtils.getSingleVerticalLine());
		userAgentInfo.append("浏览器生产商:"+getBrowserManufacturer(userAgent));
		userAgentInfo.append(com.springbootplus.core.util.StringUtils.getSingleVerticalLine());
		userAgentInfo.append("浏览器版本:"+getBrowserVersion(userAgent));
		userAgentInfo.append(com.springbootplus.core.util.StringUtils.getSingleVerticalLine());
		userAgentInfo.append("设备生产厂商:"+getDeviceManufacturer(userAgent));
		userAgentInfo.append(com.springbootplus.core.util.StringUtils.getSingleVerticalLine());
		userAgentInfo.append("设备类型:"+getDevicetype(userAgent));
		userAgentInfo.append(com.springbootplus.core.util.StringUtils.getSingleVerticalLine());
		userAgentInfo.append("设备操作系统:"+getOs(userAgent));
		userAgentInfo.append(com.springbootplus.core.util.StringUtils.getSingleVerticalLine());
		userAgentInfo.append("操作系统的名字:"+getOsName(userAgent));
		userAgentInfo.append(com.springbootplus.core.util.StringUtils.getSingleVerticalLine());
		userAgentInfo.append("操作系统的版本号:"+getOsVersion(userAgent));
		userAgentInfo.append(com.springbootplus.core.util.StringUtils.getSingleVerticalLine());
		userAgentInfo.append("操作系统浏览器的渲染引擎:"+getBorderRenderingEngine(userAgent));
		userAgentInfo.append(com.springbootplus.core.util.StringUtils.getSingleVerticalLine());
		
		
		return userAgentInfo;
	}


}
