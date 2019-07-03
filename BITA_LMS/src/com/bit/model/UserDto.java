package com.bit.model;

public class UserDto {
	String userId, password, name, email,phoneNumber,belong;
	int lecture_id;

	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public int getLecture_id() {
		return lecture_id;
	}

	public void setLecture_id(int lecture_id) {
		this.lecture_id = lecture_id;
	}

	public String getBelong() {
		return belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}

	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", password=" + password
				+ ", name=" + name + ", belong=" + belong + ", lecture_id="
				+ lecture_id + "]";
	}
	
	
	
}
