package com.bit.model;

public class AssignmentDto {
	private String title,writeDate, situation,writer,content;
	private int assignmentId, lectureId , rowNum;
	
	public AssignmentDto() {
		
	}
	
	public String getTitle() {
		return title;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public String getSituation() {
		return situation;
	}
	public String getWriter() {
		return writer;
	}
	public String getContent() {
		return content;
	}
	public int getAssignmentId() {
		return assignmentId;
	}
	public int getLectureId() {
		return lectureId;
	}
	public int getRowNum() {
		return rowNum;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
	}
	public void setLectureId(int lectureId) {
		this.lectureId = lectureId;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
}
