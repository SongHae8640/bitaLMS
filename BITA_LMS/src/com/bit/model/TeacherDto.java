package com.bit.model;

public class TeacherDto {
	//�������� ��ȣ,��������,�ۼ���,���������ۼ����� �� �� ����
	//�������̸�,�����̸�,�з�,��»���,
	//����,�ڰ�,�̸���,����ó ����
	//�������� ��ȣ,��������(�̸� �з�),�ۼ���,���������ۼ����� �� �� ����
	//user(�������̸�),techerinfo(�����̸�,�з�,��»���,����,�ڰ�,�̸���,����ó ����
	//���̺� user01, data_room, teacher_info �ʿ�
	//info_id, u.name(user_id=teacher_id������)
	
	private int infoId, rowNum;
	private String type,content,teacherId,name,lecName,email,phoneNumber,startDate,password;
	public int getInfoId() {
		return infoId;
	}
	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLecName() {
		return lecName;
	}
	public void setLecName(String lecName) {
		this.lecName = lecName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
