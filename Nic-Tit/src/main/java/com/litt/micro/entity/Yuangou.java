package com.litt.micro.entity;

import java.util.List;

public class Yuangou {
	private String card_number;
	private String type;
	private String session;
	private List<Result>  timetable ;
	

	public List<Result> getTimetable() {
		return timetable;
	}
	public void setTimetable(List<Result> timetable) {
		this.timetable = timetable;
	}
	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
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

	
}
