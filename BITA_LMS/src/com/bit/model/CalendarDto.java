package com.bit.model;

import org.json.simple.JSONObject;

public class CalendarDto {
	//시작날짜, 종료 날짜, 제목, 내용
	private int calendarId, lectureId;
	private String title, content, startDate, endDate, lectureName;
	
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
	public String getLectureName() {
		return lectureName;
	}
	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}
	
	
	public JSONObject getJsonObject(){
		JSONObject obj = new JSONObject();
		obj.put("id", calendarId);
		obj.put("lectureId", lectureId);
		obj.put("title", title);
		obj.put("content", content);
		obj.put("start", startDate);
		obj.put("end", endDate);
		obj.put("lectureName", lectureName);
		return obj;
	}
	@Override
	public String toString() {
		return "CalendarDto [calendarId=" + calendarId + ", lectureId="
				+ lectureId + ", title=" + title + ", content=" + content
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", lectureName=" + lectureName + "]";
	}
	
	
}
