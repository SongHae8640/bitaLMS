package com.bit.model;

public class ApplyDto {
	//어플라이아이디 = 시퀀스
	//apply_id apply_date lecture_id file_name user_id
	private int applyId,lectureId;
	private String applyDate,fileName,userId;
	public int getApplyId() {
		return applyId;
	}
	public int getLectureId() {
		return lectureId;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public String getFileName() {
		return fileName;
	}
	public String getUserId() {
		return userId;
	}
	public void setApplyId(int applyId) {
		this.applyId = applyId;
	}
	public void setLectureId(int lectureId) {
		this.lectureId = lectureId;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
