package com.steelIndustry.framework.web;

import java.util.Map;

public class ResponseContext {
	
	public final static int SUCCESS = 2000;
	public final static int FAIL = -2000;
	
	
	/**
	 * 响应码
	 */
	private int code;
	/**
	 * 响应消息
	 */
	private String message;
	/**
	 * 响应数据
	 */
	private Object responseData;
	/**
	 * 扩展数据
	 */
	private Map<String,Object> extendData;
	
	public ResponseContext(int code) {
		this.code = code;
	}
	public ResponseContext(int code, String message) {
		this.code = code;
		this.message = message;
	}
	public ResponseContext(int code, String message, Object responseData) {
		this.code = code;
		this.message = message;
		this.responseData = responseData;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResponseData() {
		return responseData;
	}
	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}
	public Map<String, Object> getExtendData() {
		return extendData;
	}
	public void setExtendData(Map<String, Object> extendData) {
		this.extendData = extendData;
	}
}
