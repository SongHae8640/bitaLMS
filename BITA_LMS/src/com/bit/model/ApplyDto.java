package com.bit.model;

public class ApplyDto {
	//어플라이아이디 = 시퀀스
	//apply_id apply_date lecture_id file_name user_id
	private int applyId,lectureId,fileId;

	private String applyDate,userId;
	public int getApplyId() {
		return applyId;
	}
	public int getLectureId() {
		return lectureId;
	}
	public int getFileId() {
		return fileId;
	}
	public String getApplyDate() {
		return applyDate;
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
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "ApplyDto [applyId=" + applyId + ", lectureId=" + lectureId
				+ ", fileId=" + fileId + ", applyDate=" + applyDate
				+ ", userId=" + userId + "]";
	}
	
}