package com.bit.model;

public class RegisterDto {
	//��ȣ (��������) ID �̸� ���� ��¥ ����, ����ο�/�ִ��ο�
	//������ name�� �ҷ��ͼ� ����Ʈ���忡�� ***���� ������û�� �ٿ�����
	private int num;
	private String id,name,lecName,applyDate,numStd,maxStd;
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
