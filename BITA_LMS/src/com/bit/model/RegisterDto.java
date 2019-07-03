package com.bit.model;

import java.sql.Date;

public class RegisterDto {
	//번호 (제목제외) ID 이름 강좌 날짜 과정, 등록인원/최대인원
	//제목은 name을 불러와서 프론트엔드에서 ***님의 수강신청을 붙여야함
	int num,numStd,maxStd;
	String id,name,lecName;
	Date applyDate;
	
	public int getNum() {
		return num;
	}
	public int getNumStd() {
		return numStd;
	}
	public int getMaxStd() {
		return maxStd;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getLecName() {
		return lecName;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void setNumStd(int numStd) {
		this.numStd = numStd;
	}
	public void setMaxStd(int maxStd) {
		this.maxStd = maxStd;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLecName(String lecName) {
		this.lecName = lecName;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
	
	
	
	
}
