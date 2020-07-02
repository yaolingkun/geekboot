package com.springbootplus.core.channels;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.springbootplus.core.util.IdWorkerUtils;
import com.springbootplus.core.util.StringUtils;

public class ChannelTemplate {

//	private static final Logger logger = LoggerFactory.getLogger(ChannelTemplate.class);

	/**
	 * @param request 
	 * @return
	 */
	public static String getLogParam(HttpServletRequest request) {
		StringBuffer logParam = commonParam(request);
		
		// URL
				String url = request.getRequestURL().toString();
//				logger.warn("URL={}", url);
				// http--.do,H5--hl,client--cl,微信--wx
				if (url.endsWith(".do")) {
					logParam.append(StringUtils.getSingleVerticalLine());
					logParam = logParam.append(HttpChannel.getLogParam(request));
					
				}
				if (url.endsWith(".hl")) {
					logParam.append(StringUtils.getSingleVerticalLine());
					logParam = logParam.append(H5Channel.getLogParam(request));
				}
				if (url.endsWith(".cl")) {
					logParam.append(StringUtils.getSingleVerticalLine());
					logParam = logParam.append(ClientChannel.getLogParam(request));
				}
				if (url.endsWith(".wx")) {
					logParam.append(StringUtils.getSingleVerticalLine());
					logParam = logParam.append(WXChannel.getLogParam(request));
				}
		return logParam.toString();
	}
	
	private static StringBuffer commonParam(HttpServletRequest request){
		StringBuffer commonParam = new StringBuffer();
		//全局流水号   
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		commonParam.append("SessionId:"+sessionId);
		commonParam.append(StringUtils.getSingleVerticalLine());
		
		Long globalId =  (Long) session.getAttribute("GlobalId");
		if(null==globalId){
			IdWorkerUtils id = new IdWorkerUtils(0,1);
			globalId = id.nextId();
			session.setAttribute("GlobalId", globalId);
		}
		
		commonParam.append("GlobalId:"+globalId);
		return commonParam;
	}
	

}
