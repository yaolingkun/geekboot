/**
 *  Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 *  @Package: com.svimer.core.template 
 *  @ClassName: ClientChannel 
 *  @Description: TODO
 *  @author: Svimer 
 *  @date: 2019年9月19日 下午5:34:28 
 */
package com.springbootplus.core.channels;

import javax.servlet.http.HttpServletRequest;

import com.springbootplus.core.util.IPUtils;

/**
 * 
 *
 */
public class ClientChannel {

	public static String getLogParam(HttpServletRequest request) {
		StringBuffer logParam = new StringBuffer("HTTP.CLIENT");

		logParam.append(IPUtils.getIP(request));

		return logParam.toString();

	}
}
