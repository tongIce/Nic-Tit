package com.litt.micro.entity;

import java.util.List;

public class Result {
	

	private String KCDM;//课程代码
	private String SKBJ;//上课班级
	private String xqj;//k42
	private String room;//授课教室
	private String lessname;//课程名
	private String jsxm;//教师姓名
	private String begin_time;
	private String end_time;
	private String week;
	private String required_course;
	
	
	public String getRequired_course() {
		return required_course;
	}
	public void setRequired_course(String required_course) {
		this.required_course = required_course;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(String begin_time) {
		this.begin_time = begin_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}	
	public String getKCDM() {
		return KCDM;
	}
	public void setKCDM(String kCDM) {
		KCDM = kCDM;
	}
	public String getSKBJ() {
		return SKBJ;
	}
	public void setSKBJ(String sKBJ) {
		SKBJ = sKBJ;
	}
	
	
	public String getXqj() {
		return xqj;
	}
	public void setXqj(String xqj) {
		this.xqj = xqj;
	}
	
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	
	public String getLessname() {
		return lessname;
	}
	public void setLessname(String lessname) {
		this.lessname = lessname;
	}
	public String getJsxm() {
		return jsxm;
	}
	public void setJsxm(String jsxm) {
		this.jsxm = jsxm;
	}

}

    
	
	

