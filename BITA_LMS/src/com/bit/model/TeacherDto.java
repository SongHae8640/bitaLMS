package com.bit.model;

public class TeacherDto {
	//강사목록의 번호,강사정보,작성자,강사정보작성일을 알 수 있음
	//강사의이름,강좌이름,학력,경력사항,
	//저서,자격,이메일,연락처 정보
	//강사목록의 번호,강사정보(이름 학력),작성자,강사정보작성일을 알 수 있음
	//user(강사의이름),techerinfo(강좌이름,학력,경력사항,저서,자격,이메일,연락처 정보
	//테이블 user01, data_room, teacher_info 필요
	//info_id, u.name(user_id=teacher_id같을때)
	
	private int infoId;
	private String type,content,teacherId,name,lecName,email,phoneNumber,writer,writeDate;
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	
	
	
}
