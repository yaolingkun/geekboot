/**
 *  Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 *  @Package: com.svimer.core.template 
 *  @ClassName: HttpChannel 
 *  @Description: TODO
 *  @author: Svimer 
 *  @date: 2019年9月8日 下午10:55:32 
 */
package com.springbootplus.core.channels;

import javax.servlet.http.HttpServletRequest;

import com.springbootplus.core.util.IPUtils;
import com.springbootplus.core.util.StringUtils;

/**
 * 
 *
 */
public class HttpChannel {

	public static String getLogParam(HttpServletRequest request) {
		StringBuffer logParam = new StringBuffer();
//		logParam.append(UserAgentUtils.getUserAgentInfo(request));
		
		
		logParam.append("IP:"+IPUtils.getIP(request));
		logParam.append(StringUtils.getSingleVerticalLine());
		logParam.append("Channel:HTTP.WAP");
		return logParam.toString();
		
		
	}
}
