package com.bit.model;

public class UserDto {
	//Join
	private String userId, password, name, email,phoneNumber,belong,inflowPath;
	//LMS stu main
	private String lectureName,startDate,endDate;
	private int lectureId;
	//admin main
	private int applyTotal,applyNew,qnaTotal,qnaNew;
	//admin manageStu
	private int totalDays, attendanceDays, attendanceStatus;
	
	public int getTotalDays() {
		return totalDays;
	}
	public int getAttendanceDays() {
		return attendanceDays;
	}
	public int getAttendanceStatus() {
		return attendanceStatus;
	}
	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}
	public void setAttendanceDays(int attendanceDays) {
		this.attendanceDays = attendanceDays;
	}
	public void setAttendanceStatus(int attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}
	public int getApplyTotal() {
		return applyTotal;
	}
	public void setApplyTotal(int applyTotal) {
		this.applyTotal = applyTotal;
	}
	public int getApplyNew() {
		return applyNew;
	}
	public void setApplyNew(int applyNew) {
		this.applyNew = applyNew;
	}
	public int getQnaTotal() {
		return qnaTotal;
	}
	public void setQnaTotal(int qnaTotal) {
		this.qnaTotal = qnaTotal;
	}
	public int getQnaNew() {
		return qnaNew;
	}
	public void setQnaNew(int qnaNew) {
		this.qnaNew = qnaNew;
	}
	public String getInflowPath() {
		return inflowPath;
	}
	public void setInflowPath(String inflowPath) {
		this.inflowPath = inflowPath;
	}
	public String getUserId() {
		return userId;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getBelong() {
		return belong;
	}
	public String getLectureName() {
		return lectureName;
	}
	public String getStartDate() {
		return startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setBelong(String belong) {
		this.belong = belong;
	}
	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getLectureId() {
		return lectureId;
	}
	public void setLectureId(int lectureId) {
		this.lectureId = lectureId;
	}
	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", password=" + password
				+ ", name=" + name + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", belong=" + belong + ", inflowPath="
				+ inflowPath + ", lectureName=" + lectureName + ", startDate="
				+ startDate + ", endDate=" + endDate + ", lectureId="
				+ lectureId + ", applyTotal=" + applyTotal + ", applyNew="
				+ applyNew + ", qnaTotal=" + qnaTotal + ", qnaNew=" + qnaNew
				+ ", totalDays=" + totalDays + ", attendanceDays="
				+ attendanceDays + ", attendanceStatus=" + attendanceStatus
				+ "]";
	}
	
	
}
