package com.bit.model;

import java.sql.Date;

public class AttendanceDto {
	String name,status;
	int stdId,lectureId;
	Date checkinTime,checkoutTime;
	public String getName() {
		return name;
	}
	public String getStatus() {
		return status;
	}
	public int getStdId() {
		return stdId;
	}
	public int getLectureId() {
		return lectureId;
	}
	public Date getCheckinTime() {
		return checkinTime;
	}
	public Date getCheckoutTime() {
		return checkoutTime;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setStdId(int stdId) {
		this.stdId = stdId;
	}
	public void setLectureId(int lectureId) {
		this.lectureId = lectureId;
	}
	public void setCheckinTime(Date checkinTime) {
		this.checkinTime = checkinTime;
	}
	public void setCheckoutTime(Date checkoutTime) {
		this.checkoutTime = checkoutTime;
	}
}
