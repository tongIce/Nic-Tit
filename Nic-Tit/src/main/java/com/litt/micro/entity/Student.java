package com.litt.micro.entity;

import java.util.Date;

//学生实体类
public class Student {
	private Integer stuId;
	private String stuOpenid;
	private String stuCardNumber;
	private	String stuName;
	private String	stuCollege;
	private String	stuProfession;
	private	String 	stuGrade;
	private String 	stuClass;
	private	String	stuSex;
	private	Date	stuBorn;
	private	String	stuIDnum;
	private	String	stuPhone;
	
	public String getStuOpenid() {
		return stuOpenid;
	}
	public void setStuOpenid(String stuOpenid) {
		this.stuOpenid = stuOpenid;
	}
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	public String getStuCardNumber() {
		return stuCardNumber;
	}
	public String getStuClass() {
		return stuClass;
	}
	public void setStuClass(String stuClass) {
		this.stuClass = stuClass;
	}
	public void setStuCardNumber(String stuCardNumber) {
		this.stuCardNumber = stuCardNumber;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuCollege() {
		return stuCollege;
	}
	public void setStuCollege(String stuCollege) {
		this.stuCollege = stuCollege;
	}
	public String getStuProfession() {
		return stuProfession;
	}
	public void setStuProfession(String stuProfession) {
		this.stuProfession = stuProfession;
	}
	public String getStuGrade() {
		return stuGrade;
	}
	public void setStuGrade(String stuGrade) {
		this.stuGrade = stuGrade;
	}
	public String getStuSex() {
		return stuSex;
	}
	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}
	public Date getStuBorn() {
		return stuBorn;
	}
	public void setStuBorn(Date stuBorn) {
		this.stuBorn = stuBorn;
	}
	public String getStuIDnum() {
		return stuIDnum;
	}
	public void setStuIDnum(String stuIDnum) {
		this.stuIDnum = stuIDnum;
	}
	public String getStuPhone() {
		return stuPhone;
	}
	public void setStuPhone(String stuPhone) {
		this.stuPhone = stuPhone;
	}
	
	
	
	
}
