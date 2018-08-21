package com.litt.micro.entity;


public class Score {
	private String scid;
	private String XN;  //学年
	private String XQ;	//学期
	private String logid; //个人id
	private String KCMC;  //课程名称
	private String KCDM;  //课程代码
	private String KCLB;  //课程类型
	private String kscj; //课上成绩
	private String kscj_en;  //
	private String JD;  //绩点
	private String csz;  //
	private String KCYWMC;  
	private String kcxf;  
	private String hdxf;
	private String cxbkbz; //初修  补考
	private String XFJD; //学分绩点
	private String cj_num;
	private String zxs;
	private String hjlb;
	private String bylw;
	public String getScid() {
		return scid;
	}
	public void setScid(String scid) {
		this.scid = scid;
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
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public String getKCMC() {
		return KCMC;
	}
	public void setKCMC(String kCMC) {
		KCMC = kCMC;
	}
	public String getKCDM() {
		return KCDM;
	}
	public void setKCDM(String kCDM) {
		KCDM = kCDM;
	}
	public String getKCLB() {
		return KCLB;
	}
	public void setKCLB(String kCLB) {
		KCLB = kCLB;
	}
	public String getKscj() {
		return kscj;
	}
	public void setKscj(String kscj) {
		this.kscj = kscj;
	}
	public String getKscj_en() {
		return kscj_en;
	}
	public void setKscj_en(String kscj_en) {
		this.kscj_en = kscj_en;
	}
	public String getJD() {
		return JD;
	}
	public void setJD(String jD) {
		JD = jD;
	}
	public String getCsz() {
		return csz;
	}
	public void setCsz(String csz) {
		this.csz = csz;
	}
	public String getKCYWMC() {
		return KCYWMC;
	}
	public void setKCYWMC(String kCYWMC) {
		KCYWMC = kCYWMC;
	}
	public String getKcxf() {
		return kcxf;
	}
	public void setKcxf(String kcxf) {
		this.kcxf = kcxf;
	}
	public String getHdxf() {
		return hdxf;
	}
	public void setHdxf(String hdxf) {
		this.hdxf = hdxf;
	}
	public String getCxbkbz() {
		return cxbkbz;
	}
	public void setCxbkbz(String cxbkbz) {
		this.cxbkbz = cxbkbz;
	}
	public String getXFJD() {
		return XFJD;
	}
	public void setXFJD(String xFJD) {
		XFJD = xFJD;
	}
	public String getCj_num() {
		return cj_num;
	}
	public void setCj_num(String cj_num) {
		this.cj_num = cj_num;
	}
	public String getZxs() {
		return zxs;
	}
	public void setZxs(String zxs) {
		this.zxs = zxs;
	}
	public String getHjlb() {
		return hjlb;
	}
	public void setHjlb(String hjlb) {
		this.hjlb = hjlb;
	}
	public String getBylw() {
		return bylw;
	}
	public void setBylw(String bylw) {
		this.bylw = bylw;
	}
	@Override
	public String toString() {
		return "Score [scid=" + scid + ", XN=" + XN + ", XQ=" + XQ + ", logid=" + logid + ", KCMC=" + KCMC + ", KCDM="
				+ KCDM + ", KCLB=" + KCLB + ", kscj=" + kscj + ", kscj_en=" + kscj_en + ", JD=" + JD + ", csz=" + csz
				+ ", KCYWMC=" + KCYWMC + ", kcxf=" + kcxf + ", hdxf=" + hdxf + ", cxbkbz=" + cxbkbz + ", XFJD=" + XFJD
				+ ", cj_num=" + cj_num + ", zxs=" + zxs + ", hjlb=" + hjlb + ", bylw=" + bylw + "]";
	}

	
	
}
