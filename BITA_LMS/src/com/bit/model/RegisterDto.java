package com.bit.model;

import java.sql.Date;

public class RegisterDto {
	//번호 (제목제외) ID 이름 강좌 날짜
	//제목은 name을 불러와서 프론트엔드에서 ***님의 수강신청을 붙여야함
	int num;
	String id,name,lecName,applyDate;
	
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
	
	
	
	
	
}
