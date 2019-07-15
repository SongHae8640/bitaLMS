package com.bit.model;

public class QnaLDto {
	private int rowNum;				//bbs�������� ���� �� ��ȣ
	private int qnaLId;				//qanL�� ���� id
	private String isCheck;			//�����ڰ� �亯 ������ Ȯ���ϸ� 1, �亯�� �Դµ� Ȯ���� ���ϸ� 0, �亯�� �ȿ����� null;
	private String stuId;			//������ id (User01.user_id)
	private String stdName;			//������ name(User01.name)
	private String title;			//���� ����
	private String writeDate;		//�ۼ���
	private String questionContent;	//���� ����
	private String type;			//���� ����
	private String answerContent;	//�亯 ����

	
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
