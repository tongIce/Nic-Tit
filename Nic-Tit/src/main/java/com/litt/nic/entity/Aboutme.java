package com.litt.nic.entity;

public class Aboutme {
    private Integer aboutmeId;

    private String aboutmeIntroduce;

    private String aboutmeName;

    private String aboutmeTelephone;

    public Integer getAboutmeId() {
        return aboutmeId;
    }

    public void setAboutmeId(Integer aboutmeId) {
        this.aboutmeId = aboutmeId;
    }

    public String getAboutmeIntroduce() {
        return aboutmeIntroduce;
    }

    public void setAboutmeIntroduce(String aboutmeIntroduce) {
        this.aboutmeIntroduce = aboutmeIntroduce == null ? null : aboutmeIntroduce.trim();
    }

    public String getAboutmeName() {
        return aboutmeName;
    }

    public void setAboutmeName(String aboutmeName) {
        this.aboutmeName = aboutmeName == null ? null : aboutmeName.trim();
    }

    public String getAboutmeTelephone() {
        return aboutmeTelephone;
    }

    public void setAboutmeTelephone(String aboutmeTelephone) {
        this.aboutmeTelephone = aboutmeTelephone == null ? null : aboutmeTelephone.trim();
    }
}