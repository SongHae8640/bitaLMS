package com.bit.model;

import java.sql.Date;

public class AttendanceDto {
	private String stdId,name,status,checkinTime,CheckoutTime;
	private Date dayTime ;
	private int lectureId;
	
	public Date getDayTime() {
		return dayTime;
	}
	public void setDayTime(Date dayTime) {
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
	
	
}
