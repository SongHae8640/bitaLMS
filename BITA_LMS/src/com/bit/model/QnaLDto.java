package com.bit.model;

public class QnaLDto {
	private int rowNum;				//bbs형식으로 볼때 글 번호
	private int qnaLId;				//qanL의 고유 id
	private String isCheck;			//질문자가 답변 왔음을 확인하면 1, 답변이 왔는데 확인을 안하면 0, 답변이 안왔으면 null;
	private String stuId;			//질문자 id (User01.user_id)
	private String stdName;			//질문자 name(User01.name)
	private String title;			//질문 제목
	private String writeDate;		//작성일
	private String questionContent;	//질문 내용
	private String type;			//질문 종류
	private String answerContent;	//답변 내용

	
	public int getRowNum() {
		return rowNum;
	}
	public int getQnaLId() {
		return qnaLId;
	}
	public String getIsCheck() {
		return isCheck;
	}
	public String getStuId() {
		return stuId;
	}
	public String getStdName() {
		return stdName;
	}
	public String getTitle() {
		return title;
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
	public String getAnswerContent() {
		return answerContent;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public void setQnaLId(int qnaLId) {
		this.qnaLId = qnaLId;
	}
	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public void setStdName(String stdName) {
		this.stdName = stdName;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	@Override
	public String toString() {
		return "QnaLDto [qnaLId=" + qnaLId + ", isCheck=" + isCheck + ", stuId=" + stuId + ", stdName=" + stdName
				+ ", title=" + title + ", writeDate=" + writeDate + ", questionContent=" + questionContent + ", type="
				+ type + ", answerContent=" + answerContent + "]";
	}
	
	
}
