package com.bit.model;

public class ScoreDto {
	private String name;
	private int firstScore, secondScore, thirdScore;
	private double avgScore;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFirstScore() {
		return firstScore;
	}
	public void setFirstScore(int firstScore) {
		this.firstScore = firstScore;
	}
	public int getSecondScore() {
		return secondScore;
	}
	public void setSecondScore(int secondScore) {
		this.secondScore = secondScore;
	}
	public int getThirdScore() {
		return thirdScore;
	}
	public void setThirdScore(int thirdScore) {
		this.thirdScore = thirdScore;
	}
	public double getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(double avgScore) {
		this.avgScore = avgScore;
	}
}
