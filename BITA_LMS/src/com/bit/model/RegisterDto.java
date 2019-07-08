package com.bit.model;

public class RegisterDto {
	private int rowNum;
	private String userId,userName,lecName,applyDate,numStd,maxStd;
	
	public int getRowNum() {
		return rowNum;
	}
	public String getLecName() {
		return lecName;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public String getNumStd() {
		return numStd;
	}
	public String getMaxStd() {
		return maxStd;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public void setLecName(String lecName) {
		this.lecName = lecName;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public void setNumStd(String numStd) {
		this.numStd = numStd;
	}
	public void setMaxStd(String maxStd) {
		this.maxStd = maxStd;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
