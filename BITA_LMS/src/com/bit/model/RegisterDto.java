package com.bit.model;

import java.sql.Date;

public class RegisterDto {
	//��ȣ (��������) ID �̸� ���� ��¥
	//������ name�� �ҷ��ͼ� ����Ʈ���忡�� ***���� ������û�� �ٿ�����
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
