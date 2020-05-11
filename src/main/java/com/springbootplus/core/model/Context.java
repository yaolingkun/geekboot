package com.springbootplus.core.model;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Context implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String,Object> date = new ConcurrentHashMap<String, Object>();

	/**
	 * @return the date
	 */
	public Object getDate(String key) {
		return ((Object[])date.get(key))[0];
	}
	/**
	 * @return the date
	 */
	public String getStringDate(String key) {
		return (String)((Object[])date.get(key))[0];
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String key ,Object value) {
		date.put(key, value);
	}
	
	public void setAllDate(Map<String, String[]> map){
		date.putAll(map);
	}

}
