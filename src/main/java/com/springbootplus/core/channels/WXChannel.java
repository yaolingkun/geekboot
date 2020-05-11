/**
 *  Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 *  @Package: com.svimer.core.template 
 *  @ClassName: WXChannel 
 *  @Description: TODO
 *  @author: Svimer 
 *  @date: 2019年9月19日 下午5:35:05 
 */
package com.springbootplus.core.channels;

import javax.servlet.http.HttpServletRequest;

import com.springbootplus.core.util.IPUtils;

/**
 * 
 *
 */
public class WXChannel {

	public static String getLogParam(HttpServletRequest request) {

		StringBuffer logParam = new StringBuffer("HTTP.WX");

		logParam.append(IPUtils.getIP(request));

		return logParam.toString();

	}
}
