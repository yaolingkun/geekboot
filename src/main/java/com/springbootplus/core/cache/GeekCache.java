package com.springbootplus.core.cache;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.springbootplus.core.util.PropertiesUtils;

public class GeekCache {

	//缓存总大小
	private static int size = Integer.parseInt(PropertiesUtils.getExtPropertiesValue("geekcache.size"));
	//已使用大小
	private static int usedSize = 0;
	//土包子缓存map
	private static volatile Map<String,Object> geekCacheMap = new ConcurrentHashMap<String, Object>(size);
	//失效时间map
	private static volatile Map<String,Long> expireTimeMap = new ConcurrentHashMap<String,Long>(size);
	//失效缓存
	private static volatile List<String> list = new LinkedList<>();
	//锁
	private static Object lock = new Object();
	
	static{
		cleanCache();
	}
	/**
	 * 初始化、更新时调用
	 * @param key
	 * @param obj
	 * @param expireTime
	 */
	public static void updateGeekCache(String key,Object obj,long expireTime){
		synchronized (lock) {
			if(size>usedSize){
				geekCacheMap.put(key, obj);
				long time = (new Date()).getTime();
				expireTimeMap.put(key, time+expireTime);
				usedSize = usedSize+1;
			}else{
				//TODO 未提醒，报错等提醒
			}
			
		}
	}
	public static Object getObject(String key){
		long sys_time = (new Date()).getTime();
		long expireTime = expireTimeMap.get(key);
		if(sys_time>=expireTime){//未失效
			return geekCacheMap.get(key);
		}else{//已失效
			//待清理
			list.add(key);
			return null;
		}
		
	}

	private static void cleanCache(){
		 Thread t = new Thread(new Runnable(){  
	            public void run(){  
	           // run方法具体重写
	            	while(true){
	            		if(list.size()>0){
	            			String key = list.remove(0);
	            			synchronized (lock) {
	            				geekCacheMap.remove(key);
		            			expireTimeMap.remove(key);
							}
	            			
	            		}
	            		
	            	}
	            	
	            }});  
	        t.start();  
	}
}
