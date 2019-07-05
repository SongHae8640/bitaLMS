package com.bit.model;

public class LectureDto {
	int lectureID, numStd, totalDays, maxStd, lv;
	String name, startDate, endDate, content, fileName, teaName;
	boolean isClose; //�ʱⰪ false

	public String getTeaName() {
		return teaName;
	}

	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}

	public int getLectureID() {
		return lectureID;
	}

	public int getNumStd() {
		return numStd;
	}

	public int getTotalDays() {
		return totalDays;
	}

	public int getMaxStd() {
		return maxStd;
	}

	public int getLv() {
		return lv;
	}

	public String getName() {
		return name;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getContent() {
		return content;
	}

	public String getFileName() {
		return fileName;
	}

	public boolean getIsClose() {
		return isClose;
	}

	public void setLectureID(int lectureID) {
		this.lectureID = lectureID;
	}

	public void setNumStd(int numStd) {
		this.numStd = numStd;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	public void setMaxStd(int maxStd) {
		this.maxStd = maxStd;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setIsClose(String isClose) {
		this.isClose = isClose.equals("1");
	}
	
	@Override
	public String toString() {
		return content+endDate+lectureID;
		
	}

}
