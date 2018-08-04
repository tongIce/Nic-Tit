package com.litt.micro.entity;

//学生课表实体类
public class CoursesSchedule {
       
	private String ID;
	private String xn;//学年
	private String xq;//学期
	private String xh;//学号
	private String KCDM;//课程代码
	private String skbj;//上课班级
	private String stimezc;//1-16周授课
	private String dsz;//0
	private String xqj;//k42
	private String JCInfo;//3-4
	
	private String room;//授课教室
	private String RKJSGH;//任课教师工号
	private String KCLBlMC;//01
	private String KHFS;//考核方式
	private String xf;//学分
	private String jsxs;//64
	private String syxs;
	private String sjxs;//所有学生
	private String qtxs;//
	private String JSM_XQ;//本校区
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getKCDM() {
		return KCDM;
	}
	public void setKCDM(String kCDM) {
		KCDM = kCDM;
	}
	public String getSkbj() {
		return skbj;
	}
	public void setSkbj(String skbj) {
		this.skbj = skbj;
	}
	public String getStimezc() {
		return stimezc;
	}
	public void setStimezc(String stimezc) {
		this.stimezc = stimezc;
	}
	public String getDsz() {
		return dsz;
	}
	public void setDsz(String dsz) {
		this.dsz = dsz;
	}
	public String getXqj() {
		return xqj;
	}
	public void setXqj(String xqj) {
		this.xqj = xqj;
	}
	public String getJCInfo() {
		return JCInfo;
	}
	public void setJCInfo(String jCInfo) {
		JCInfo = jCInfo;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getRKJSGH() {
		return RKJSGH;
	}
	public void setRKJSGH(String rKJSGH) {
		RKJSGH = rKJSGH;
	}
	public String getKCLBlMC() {
		return KCLBlMC;
	}
	public void setKCLBlMC(String kCLBlMC) {
		KCLBlMC = kCLBlMC;
	}
	public String getKHFS() {
		return KHFS;
	}
	public void setKHFS(String kHFS) {
		KHFS = kHFS;
	}
	public String getXf() {
		return xf;
	}
	public void setXf(String xf) {
		this.xf = xf;
	}
	public String getJsxs() {
		return jsxs;
	}
	public void setJsxs(String jsxs) {
		this.jsxs = jsxs;
	}
	public String getSyxs() {
		return syxs;
	}
	public void setSyxs(String syxs) {
		this.syxs = syxs;
	}
	public String getSjxs() {
		return sjxs;
	}
	public void setSjxs(String sjxs) {
		this.sjxs = sjxs;
	}
	public String getQtxs() {
		return qtxs;
	}
	public void setQtxs(String qtxs) {
		this.qtxs = qtxs;
	}
	public String getJSM_XQ() {
		return JSM_XQ;
	}
	public void setJSM_XQ(String jSM_XQ) {
		JSM_XQ = jSM_XQ;
	}
	
}
