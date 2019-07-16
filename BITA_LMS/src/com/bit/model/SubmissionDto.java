package com.bit.model;

public class SubmissionDto {
	private int rowNum,assignmentId;
	private String stdName,submitDate,fileName;
	private boolean isCheck;	//초기값은 false
	
	
	public int getRowNum() {
		return rowNum;
	}
	public int getAssignmentId() {
		return assignmentId;
	}
	public String getStdName() {
		return stdName;
	}
	public String getSubmitDate() {
		return submitDate;
	}
	public String getFileName() {
		return fileName;
	}
	public boolean getIsCheck() {
		return isCheck;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
	}
	public void setStdName(String stdName) {
		this.stdName = stdName;
	}
	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck.equals("1");
	}	
	
}
