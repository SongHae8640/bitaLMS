package com.bit.model;

import java.sql.Date;

public class RegisterDto {
	//��ȣ (��������) ID �̸� ���� ��¥ ����, ����ο�/�ִ��ο�
	//������ name�� �ҷ��ͼ� ����Ʈ���忡�� ***���� ������û�� �ٿ�����
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
