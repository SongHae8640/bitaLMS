package com.bit.model;

public class LectureDto {
	private int lectureID, numStd, totalDays, maxStd, lv, progressDays, fileId, curriId;
	private String name, startDate, endDate, content, teaName, teaId;
	private boolean isClose; //�ʱⰪ false

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

	public void setIsClose(String isClose) {
		this.isClose = isClose.equals("1");
	}
	public int getProgressDays() {
		return progressDays;
	}
	
	public void setProgressDays(int progressDays) {
		this.progressDays = progressDays;
	}

	@Override
	public String toString() {
		return "LectureDto [lectureID=" + lectureID + ", numStd=" + numStd
				+ ", totalDays=" + totalDays + ", maxStd=" + maxStd + ", lv="
				+ lv + ", name=" + name + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", content=" + content
				+ ", teaName=" + teaName
				+ ", isClose=" + isClose + "]";
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public int getCurriId() {
		return curriId;
	}

	public void setCurriId(int curriId) {
		this.curriId = curriId;
	}

	public String getTeaId() {
		return teaId;
	}

	public void setTeaId(String teaId) {
		this.teaId = teaId;
	}


}
