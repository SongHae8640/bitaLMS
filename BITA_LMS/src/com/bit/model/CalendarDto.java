package com.bit.model;

public class CalendarDto {
	//���۳�¥, ���� ��¥, ����, ����
	int calendarId, lectureId;
	String title, content, startDate, endDate;
	
	public int getCalendarId() {
		return calendarId;
	}
	public int getLectureId() {
		return lectureId;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getStartDate() {
		return startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setCalendarId(int calendarId) {
		this.calendarId = calendarId;
	}
	public void setLectureId(int lectureId) {
		this.lectureId = lectureId;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "CalendarDto [calendarId=" + calendarId + ", lectureId="
				+ lectureId + ", title=" + title + ", content=" + content
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

}
