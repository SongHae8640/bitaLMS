package com.bit.model;

import java.sql.Date;

import org.json.simple.JSONObject;

public class AttendanceDto {
	private String stdId,name,status,checkinTime,CheckoutTime, dayTime, lecName;
	private int lectureId;

	
	public String getDayTime() {
		return dayTime;
	}
	public void setDayTime(String dayTime) {
		this.dayTime = dayTime;
	}
	public String getStdId() {
		return stdId;
	}
	public void setStdId(String stdId) {
		this.stdId = stdId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCheckinTime() {
		return checkinTime;
	}
	public String getCheckoutTime() {
		return CheckoutTime;
	}
	public void setCheckinTime(String checkinTime) {
		this.checkinTime = checkinTime;
	}
	public void setCheckoutTime(String checkoutTime) {
		CheckoutTime = checkoutTime;
	}	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getLectureId() {
		return lectureId;
	}
	public void setLectureId(int lectureId) {
		this.lectureId = lectureId;
	}
	public String getLecName() {
		return lecName;
	}
	public void setLecName(String lecName) {
		this.lecName = lecName;
	}
	
	public JSONObject getJsonObject() {
		JSONObject obj = new JSONObject();
		String content = status + "\n"+checkinTime+" ~ "+ CheckoutTime;
		obj.put("title", content);
		obj.put("start", dayTime);
		return obj;
	}
	@Override
	public String toString() {
		return "AttendanceDto [stdId=" + stdId + ", status=" + status + ", checkinTime=" + checkinTime
				+ ", CheckoutTime=" + CheckoutTime + ", dayTime=" + dayTime + "]";
	}
	
	
}
