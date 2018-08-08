package com.litt.micro.entity;

import java.util.List;

//学生课表实体类
public class CoursesSchedule {
    private String code;
    private String message;
    private String raw_data;
    private String APP_KEY;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRaw_data() {
		return raw_data;
	}
	public void setRaw_data(String raw_data) {
		this.raw_data = raw_data;
	}
	public String getAPP_KEY() {
		return APP_KEY;
	}
	public void setAPP_KEY(String aPP_KEY) {
		APP_KEY = aPP_KEY;
	}
	private String ID;
	private String xn;//学年
	private String xq;//学期
	private String xh;//学号
	private String KCDM;//课程代码
	private String SKBJ;//上课班级
	private String stimezc;//1-16周授课
	private String DSZ;//0
	private String xqj;//k42
	private String JCInfo;//3-4
	private String room;//授课教室
	private String RKJSGH;//任课教师工号
	private String KCLBlMC;//01
	private String KHFS;//考核方式
	private String XF;//学分
	private String jsxs;//64
	private String SYXS;
	private String SJXS;//所有学生
	private String QTXS;//
	private String JSM_XQ;//本校区
	private String lessname;//课程名
	private String jsxm;//教师姓名
	private String type;
	private String begin_time;
	private String end_time;
	private String week;
	private String required_course;
	public String getRequired_course() {
		return required_course;
	}
	public void setRequired_course(String required_course) {
		this.required_course = required_course;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	private String session;
	private List<CoursesSchedule>  list ;
	public List<CoursesSchedule> getList() {
		return list;
	}
	public void setList(List<CoursesSchedule> list) {
		this.list = list;
	}
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
	public String getSKBJ() {
		return SKBJ;
	}
	public void setSKBJ(String sKBJ) {
		SKBJ = sKBJ;
	}
	public String getStimezc() {
		return stimezc;
	}
	public void setStimezc(String stimezc) {
		this.stimezc = stimezc;
	}
	public String getDSZ() {
		return DSZ;
	}
	public void setDSZ(String dSZ) {
		DSZ = dSZ;
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
	public String getXF() {
		return XF;
	}
	public void setXF(String xF) {
		XF = xF;
	}
	public String getJsxs() {
		return jsxs;
	}
	public void setJsxs(String jsxs) {
		this.jsxs = jsxs;
	}
	public String getSYXS() {
		return SYXS;
	}
	public void setSYXS(String sYXS) {
		SYXS = sYXS;
	}
	public String getSJXS() {
		return SJXS;
	}
	public void setSJXS(String sJXS) {
		SJXS = sJXS;
	}
	public String getQTXS() {
		return QTXS;
	}
	public void setQTXS(String qTXS) {
		QTXS = qTXS;
	}
	public String getJSM_XQ() {
		return JSM_XQ;
	}
	public void setJSM_XQ(String jSM_XQ) {
		JSM_XQ = jSM_XQ;
	}
	public String getLessname() {
		return lessname;
	}
	public void setLessname(String lessname) {
		this.lessname = lessname;
	}
	public String getJsxm() {
		return jsxm;
	}
	public void setJsxm(String jsxm) {
		this.jsxm = jsxm;
	}
	
	
}
