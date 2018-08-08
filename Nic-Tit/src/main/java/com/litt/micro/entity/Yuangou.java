package com.litt.micro.entity;

import java.util.List;

public class Yuangou {
	private String xh;
	private String type;
	private String session;
	private List<Yuangou>  list ;
	
	public List<Yuangou> getList() {
		return list;
	}
	public void setList(List<Yuangou> list) {
		this.list = list;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	
	
}
