package com.training.models;

public class ResponseData  {

	private String status;
	
	private Object data;
	
	private Meta meta;
	
	//Constructor for success response
	public ResponseData(Object data, Meta meta) {
		this.status = "ok";
		this.data = data;
		this.meta = meta;
	}
	
	//Constructor for error response
	public ResponseData(String status, Object data, Meta meta) {
		this.status = status;
		this.data = data;
		this.meta = meta;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public Meta getMeta() {
		return meta;
	}
	
	public void setMeta(Meta meta) {
		this.meta = meta;
	}	
}