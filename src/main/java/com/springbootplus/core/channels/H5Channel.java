/**
 *  Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 *  @Package: com.svimer.core.template 
 *  @ClassName: H5Channel 
 *  @Description: TODO
 *  @author: Svimer 
 *  @date: 2019年9月19日 下午5:11:00 
 */
package com.springbootplus.core.channels;

import javax.servlet.http.HttpServletRequest;

import com.springbootplus.core.util.IPUtils;

/**
 * 
 *
 */
public class H5Channel {

	public static String getLogParam(HttpServletRequest request) {

		StringBuffer logParam = new StringBuffer("HTTP.H5");

		logParam.append(IPUtils.getIP(request));

		return logParam.toString();

	}
}
