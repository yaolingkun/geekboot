package com.springbootplus.core.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("请求参数")
public class Context implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "数据")
	private Map<String,Object> data = new HashMap<String, Object>();

	/**
	 * @return the data
	 */
	public Object getData(String key) {
		return ((Object[])data.get(key))[0];
	}
	/**
	 * @return the data
	 */
	public String getStringData(String key) {
		return (String)((Object[])data.get(key))[0];
	}
	/**
	 * @param data the data to set
	 */
	public void setData(String key ,Object value) {
		data.put(key, value);
	}
	
	public void setAllData(Map<String, String[]> map){
		data.putAll(map);
	}

}
