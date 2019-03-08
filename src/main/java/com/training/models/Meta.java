package com.training.models;

public class Meta {
	
	private int code;
	private String message;
	private String debugInfo;
	
	//Constructor for success response
	public Meta() {
		this.code = 200;
		this.message="success";
		this.debugInfo="";
	}
	
	//Constructor for error response
	public Meta(int code, String message, String debugInfo) {
		this.code = code;
		this.message = message;
		this.debugInfo = debugInfo;
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
	
	public String getDebugInfo() {
		return debugInfo;
	}
	
	public void setDebugInfo(String debugInfo) {
		this.debugInfo = debugInfo;
	}
}
