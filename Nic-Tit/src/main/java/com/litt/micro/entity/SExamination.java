package com.litt.micro.entity;

public class SExamination {
	private String ID;
	private String XN;
	private String XQ;
	private String XH;
	private String KCH;
	private String KSRQ;
	private String kskssj;
	private String ksjssj;
	private String kscd;
	private String SKDD;
	private String ZWH;
	private String ZJKJS;
	private String FJKJS;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getXN() {
		return XN;
	}
	public void setXN(String xN) {
		XN = xN;
	}
	public String getXQ() {
		return XQ;
	}
	public void setXQ(String xQ) {
		XQ = xQ;
	}
	public String getXH() {
		return XH;
	}
	public void setXH(String xH) {
		XH = xH;
	}
	public String getKCH() {
		return KCH;
	}
	public void setKCH(String kCH) {
		KCH = kCH;
	}
	public String getKSRQ() {
		return KSRQ;
	}
	public void setKSRQ(String kSRQ) {
		KSRQ = kSRQ;
	}
	public String getKskssj() {
		return kskssj;
	}
	public void setKskssj(String kskssj) {
		this.kskssj = kskssj;
	}
	public String getKsjssj() {
		return ksjssj;
	}
	public void setKsjssj(String ksjssj) {
		this.ksjssj = ksjssj;
	}
	public String getKscd() {
		return kscd;
	}
	public void setKscd(String kscd) {
		this.kscd = kscd;
	}
	public String getSKDD() {
		return SKDD;
	}
	public void setSKDD(String sKDD) {
		SKDD = sKDD;
	}
	public String getZWH() {
		return ZWH;
	}
	public void setZWH(String zWH) {
		ZWH = zWH;
	}
	public String getZJKJS() {
		return ZJKJS;
	}
	public void setZJKJS(String zJKJS) {
		ZJKJS = zJKJS;
	}
	public String getFJKJS() {
		return FJKJS;
	}
	public void setFJKJS(String fJKJS) {
		FJKJS = fJKJS;
	}
	@Override
	public String toString() {
		return "SExamination [ID=" + ID + ", XN=" + XN + ", XQ=" + XQ + ", XH=" + XH + ", KCH=" + KCH + ", KSRQ=" + KSRQ
				+ ", kskssj=" + kskssj + ", ksjssj=" + ksjssj + ", kscd=" + kscd + ", SKDD=" + SKDD + ", ZWH=" + ZWH
				+ ", ZJKJS=" + ZJKJS + ", FJKJS=" + FJKJS + "]";
	}
	
}
