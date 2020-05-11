package com.springbootplus.core.model;

/**
 * 数据返回实体
 * 
 * @author Administrator
 *
 */
public class Result {
	private Integer code;// 状态码
	private String massege;// 消息
	private Object result;// 数据对象

	/**
	 * 无参构造器
	 */
	public Result() {
		super();
	}

	/**
	 * 只返回状态，状态码，消息
	 * 
	 * @param statu
	 * @param code
	 * @param massege
	 */
	public Result(Integer code) {
		super();
		this.code = code;
		this.massege = StatusEnumerationValue.getStatusMessage(code);
	}


	/**
	 * 返回全部信息即状态，状态码，消息，数据对象
	 * 
	 * @param statu
	 * @param code
	 * @param massege
	 * @param result
	 */
	public Result( Integer code,  Object result) {
		super();
		this.code = code;
		this.massege = StatusEnumerationValue.getStatusMessage(code);
		this.result = result;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}


	public String getMassege() {
		return massege;
	}

	public void setMassege(String massege) {
		this.massege = massege;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
