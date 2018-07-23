package com.litt.nic.entity;

import java.util.Date;

public class Information {
    private Integer informationId;

    private String informationTitle;

    private String informationLink;

    private Date informationTime;

    private String informationPic;

    private String informationDescription;

    private String informationContent;

    public Integer getInformationId() {
        return informationId;
    }

    public void setInformationId(Integer informationId) {
        this.informationId = informationId;
    }

    public String getInformationTitle() {
        return informationTitle;
    }

    public void setInformationTitle(String informationTitle) {
        this.informationTitle = informationTitle == null ? null : informationTitle.trim();
    }

    public String getInformationLink() {
        return informationLink;
    }

    public void setInformationLink(String informationLink) {
        this.informationLink = informationLink == null ? null : informationLink.trim();
    }

    public Date getInformationTime() {
        return informationTime;
    }

    public void setInformationTime(Date informationTime) {
        this.informationTime = informationTime;
    }

    public String getInformationPic() {
        return informationPic;
    }

    public void setInformationPic(String informationPic) {
        this.informationPic = informationPic == null ? null : informationPic.trim();
    }

    public String getInformationDescription() {
        return informationDescription;
    }

    public void setInformationDescription(String informationDescription) {
        this.informationDescription = informationDescription == null ? null : informationDescription.trim();
    }

    public String getInformationContent() {
        return informationContent;
    }

    public void setInformationContent(String informationContent) {
        this.informationContent = informationContent == null ? null : informationContent.trim();
    }
}