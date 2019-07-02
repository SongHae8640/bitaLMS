package com.bit.model;

import java.sql.Date;

public class AttendanceDto {
	String name,status;
	Date dayTime;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDayTime() {
		return dayTime;
	}
	public void setDayTime(Date dayTime) {
		this.dayTime = dayTime;
	}
	
	
}
