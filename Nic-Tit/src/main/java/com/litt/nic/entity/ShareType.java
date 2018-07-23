package com.litt.nic.entity;

import java.util.ArrayList;
import java.util.List;

//用户分享级别类
public class ShareType {
	private Integer shid;
	private String shareType;
	//一个共享级别对应多个用户，一对多
	private List<User> shareLists=new ArrayList<User>();
	public List<User> getShareLists() {
		return shareLists;
	}
	public void setShareLists(List<User> shareLists) {
		this.shareLists = shareLists;
	}
	public Integer getShid() {
		return shid;
	}
	public void setShid(Integer shid) {
		this.shid = shid;
	}
	public String getShareType() {
		return shareType;
	}
	public void setShareType(String shareType) {
		this.shareType = shareType;
	}
	
}
