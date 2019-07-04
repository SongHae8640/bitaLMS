package com.bit.model;

public class RegisterDto {
	//번호 (제목제외) ID 이름 강좌 날짜 과정, 등록인원/최대인원
	//제목은 name을 불러와서 프론트엔드에서 ***님의 수강신청을 붙여야함
	int num;
	String id,name,lecName,applyDate,numStd,maxStd;
	public int getNum() {
		return num;
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
	public String getApplyDate() {
		return applyDate;
	}
	public String getNumStd() {
		return numStd;
	}
	public String getMaxStd() {
		return maxStd;
	}
	public void setNum(int num) {
		this.num = num;
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
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public void setNumStd(String numStd) {
		this.numStd = numStd;
	}
	public void setMaxStd(String maxStd) {
		this.maxStd = maxStd;
	}
	
	
}
