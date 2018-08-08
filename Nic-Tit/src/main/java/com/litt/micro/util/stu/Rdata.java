package com.litt.micro.util.stu;

//加密之后格式
public class Rdata {
	
	
    private String raw_data;
	private	String	app_key;
	private String message;
	private String code;
	public String getRaw_data() {
		return raw_data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setRaw_data(String raw_data) {
		this.raw_data = raw_data;
	}
	public String getApp_key() {
		return app_key;
	}
	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}
	

	
}
