package com.bit.model;

public class QnaLDto {
	private int rowNum;
	private String title,stdName,writeDate,questionContent,type,responName,answerContent;
	private boolean isRespon;	//초기값은 false
	
	public int getRowNum() {
		return rowNum;
	}
	public String getTitle() {
		return title;
	}
	public String getStdName() {
		return stdName;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public String getQuestionContent() {
		return questionContent;
	}
	public String getType() {
		return type;
	}
	public String getResponName() {
		return responName;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public boolean getIsRespon() {
		return isRespon;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setStdName(String stdName) {
		this.stdName = stdName;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setResponName(String responName) {
		this.responName = responName;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	public void setIsRespon(String isRespon) {
		if(isRespon.equals("1")){ //null값이랑 비교면 어찌 될지 모르겠군
			this.isRespon = true;
		}else{
			this.isRespon = false;
		}
	}
}
