package com.bit.model;

public class QnaLDto {
	private int rowNum;				//bbs�������� ���� �� ��ȣ
	private int qnaLId;				//qanL�� ���� id
	private int isCheck;			//�����ڰ� �亯 ������ Ȯ���ϸ� 1, �亯�� �Դµ� Ȯ���� ���ϸ� 0, �亯�� �ȿ����� null;
	private String stuId;			//������ id (User01.user_id)
	private String stdName;			//������ name(User01.name)
	private String title;			//���� ����
	private String writeDate;		//�ۼ���
	private String questionContent;	//���� ����
	private String type;			//���� ����
	private String responderId;		//�亯�� id(User01.user_id)
	private String responderName;	//�亯�� name(User01.name)
	private String answerContent;	//�亯 ����
	
	
	public int getRowNum() {
		return rowNum;
	}
	public int getQnaLId() {
		return qnaLId;
	}
	public int getIsCheck() {
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
	public String getResponderId() {
		return responderId;
	}
	public String getResponderName() {
		return responderName;
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
	public void setIsCheck(int isCheck) {
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
	public void setResponderId(String responderId) {
		this.responderId = responderId;
	}
	public void setResponderName(String responderName) {
		this.responderName = responderName;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
}
