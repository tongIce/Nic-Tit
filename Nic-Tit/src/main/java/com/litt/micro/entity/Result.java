package com.litt.micro.entity;

import java.util.List;

public class Result {
	

	private String course_id;//课程代码
	private String course_class;//课程开始节次
	private String begin_time;
	private String end_time;
	private String course_name;// 课程名字
	private String teacher;// 课程老师名字
	private String day;		// 周几上课：1-周一；2-周二；以此类推
	private String class_name;//班级名称
	private String week;  // 第几周
	private String address;//上课地点
	private String required_course;
	
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getCourse_class() {
		return course_class;
	}
	public void setCourse_class(String course_class) {
		this.course_class = course_class;
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
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRequired_course() {
		return required_course;
	}
	public void setRequired_course(String required_course) {
		this.required_course = required_course;
	}
	@Override
	public String toString() {
		return "Result [course_id=" + course_id + ", course_class=" + course_class + ", begin_time=" + begin_time
				+ ", end_time=" + end_time + ", course_name=" + course_name + ", teacher=" + teacher + ", day=" + day
				+ ", class_name=" + class_name + ", week=" + week + ", address=" + address + ", required_course="
				+ required_course + "]";
	}

	
}

    
	
	

