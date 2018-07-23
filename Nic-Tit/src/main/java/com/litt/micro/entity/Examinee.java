package com.litt.micro.entity;

import java.util.Date;

//等级考生，实体
public class Examinee {
	private Integer exId;
	private String	exOpenid;//微信唯一标示
	private String 	exAdmissionTicket;//准考证号
	private String 	exEnterNumber;//报名号
	private String exNumber;//学号
	private String exName;//姓名
	private String exSex;//性别
	private Date exBorn;//生日
	private String bornTostring;//后台将日期格式处理为字符串直接显示到前台
	private String exIDcard;//身份证
	private	String exNation;//名族
	private	String exTele;//电话
	private String exLevel;//级别（二级，三级）
	private String exType;//具体类型（24、26/28---）
	private String exExamRoom;//考场
	private String exBatchNum;//批次号，根据批次号对应具体的时间节点
	//添加三个属性
	private String exDegree; //文化程度
	private String exProfession;//职业
	private String exNote;//备注
	private String	exImg;
	
	
	public String getBornTostring() {
		return bornTostring;
	}
	public void setBornTostring(String bornTostring) {
		this.bornTostring = bornTostring;
	}
	public String getExDegree() {
		return exDegree;
	}
	public void setExDegree(String exDegree) {
		this.exDegree = exDegree;
	}
	public String getExProfession() {
		return exProfession;
	}
	public void setExProfession(String exProfession) {
		this.exProfession = exProfession;
	}
	public String getExNote() {
		return exNote;
	}
	public void setExNote(String exNote) {
		this.exNote = exNote;
	}
	public String getExAdmissionTicket() {
		return exAdmissionTicket;
	}
	public void setExAdmissionTicket(String exAdmissionTicket) {
		this.exAdmissionTicket = exAdmissionTicket;
	}
	public String getExEnterNumber() {
		return exEnterNumber;
	}
	public void setExEnterNumber(String exEnterNumber) {
		this.exEnterNumber = exEnterNumber;
	}
	public Date getExBorn() {
		return exBorn;
	}
	public void setExBorn(Date exBorn) {
		this.exBorn = exBorn;
	}
	public String getExExamRoom() {
		return exExamRoom;
	}
	public void setExExamRoom(String exExamRoom) {
		this.exExamRoom = exExamRoom;
	}
	public String getExBatchNum() {
		return exBatchNum;
	}
	public void setExBatchNum(String exBatchNum) {
		this.exBatchNum = exBatchNum;
	}
	public String getExOpenid() {
		return exOpenid;
	}
	public void setExOpenid(String exOpenid) {
		this.exOpenid = exOpenid;
	}
	public String getExImg() {
		return exImg;
	}
	public void setExImg(String exImg) {
		this.exImg = exImg;
	}
	public Integer getExId() {
		return exId;
	}
	public void setExId(Integer exId) {
		this.exId = exId;
	}
	public String getExNumber() {
		return exNumber;
	}
	public void setExNumber(String exNumber) {
		this.exNumber = exNumber;
	}
	public String getExName() {
		return exName;
	}
	public void setExName(String exName) {
		this.exName = exName;
	}
	public String getExSex() {
		return exSex;
	}
	public void setExSex(String exSex) {
		this.exSex = exSex;
	}
	public String getExNation() {
		return exNation;
	}
	public void setExNation(String exNation) {
		this.exNation = exNation;
	}
	public String getExTele() {
		return exTele;
	}
	public void setExTele(String exTele) {
		this.exTele = exTele;
	}
	
	public String getExIDcard() {
		return exIDcard;
	}
	public void setExIDcard(String exIDcard) {
		this.exIDcard = exIDcard;
	}
	public String getExLevel() {
		return exLevel;
	}
	public void setExLevel(String exLevel) {
		this.exLevel = exLevel;
	}
	public String getExType() {
		return exType;
	}
	public void setExType(String exType) {
		this.exType = exType;
	}
	
}
