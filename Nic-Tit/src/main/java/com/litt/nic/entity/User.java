package com.litt.nic.entity;

import java.util.Date;

public class User {
    private Integer userId;

    private String userName;

    private String userTelephone;

    private String userDepartment;

    private String userOpenid;

    private Integer userSubscribe;

    private Date userSubscribetime;

    private String userNickname;

    private Integer userSex;

    private String userCountry;

    private String userProvince;

    private String userCity;

    private String userLanguage;

    private String userHeadimgurl;

    //一个用户对应一个共享级别
    private ShareType shareType;
	public ShareType getShareType() {
		return shareType;
	}

	public void setShareType(ShareType shareType) {
		this.shareType = shareType;
	}
	public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone == null ? null : userTelephone.trim();
    }

    public String getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(String userDepartment) {
        this.userDepartment = userDepartment == null ? null : userDepartment.trim();
    }

    public String getUserOpenid() {
        return userOpenid;
    }

    public void setUserOpenid(String userOpenid) {
        this.userOpenid = userOpenid == null ? null : userOpenid.trim();
    }

    public Integer getUserSubscribe() {
        return userSubscribe;
    }

    public void setUserSubscribe(Integer userSubscribe) {
        this.userSubscribe = userSubscribe;
    }

    public Date getUserSubscribetime() {
        return userSubscribetime;
    }

    public void setUserSubscribetime(Date userSubscribetime) {
        this.userSubscribetime = userSubscribetime;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname == null ? null : userNickname.trim();
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry == null ? null : userCountry.trim();
    }

    public String getUserProvince() {
        return userProvince;
    }

    public void setUserProvince(String userProvince) {
        this.userProvince = userProvince == null ? null : userProvince.trim();
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity == null ? null : userCity.trim();
    }

    public String getUserLanguage() {
        return userLanguage;
    }

    public void setUserLanguage(String userLanguage) {
        this.userLanguage = userLanguage == null ? null : userLanguage.trim();
    }

    public String getUserHeadimgurl() {
        return userHeadimgurl;
    }

    public void setUserHeadimgurl(String userHeadimgurl) {
        this.userHeadimgurl = userHeadimgurl == null ? null : userHeadimgurl.trim();
    }
}