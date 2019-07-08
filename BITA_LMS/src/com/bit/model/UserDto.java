package com.bit.model;

public class UserDto {
	private String userId, password, name, email,phoneNumber,belong, lectureName,startDate,endDate,inflowPath;
	private int applyTotal,applyNew,qnaTotal,qnaNew;
	private int lecture_id;
	
	
	
	public int getApplyTotal() {
		return applyTotal;
	}
	public void setApplyTotal(int applyTotal) {
		this.applyTotal = applyTotal;
	}
	public int getApplyNew() {
		return applyNew;
	}
	public void setApplyNew(int applyNew) {
		this.applyNew = applyNew;
	}
	public int getQnaTotal() {
		return qnaTotal;
	}
	public void setQnaTotal(int qnaTotal) {
		this.qnaTotal = qnaTotal;
	}
	public int getQnaNew() {
		return qnaNew;
	}
	public void setQnaNew(int qnaNew) {
		this.qnaNew = qnaNew;
	}
	public String getInflowPath() {
		return inflowPath;
	}
	public void setInflowPath(String inflowPath) {
		this.inflowPath = inflowPath;
	}
	public String getUserId() {
		return userId;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getBelong() {
		return belong;
	}
	public String getLectureName() {
		return lectureName;
	}
	public String getStartDate() {
		return startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public int getLecture_id() {
		return lecture_id;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setBelong(String belong) {
		this.belong = belong;
	}
	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public void setLecture_id(int lecture_id) {
		this.lecture_id = lecture_id;
	}
}
