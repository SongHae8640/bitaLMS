package com.bit.model;

import java.util.Date;

public class AttachedFileDto {
	private String fileGroup,originalName,fileName,fileExtension,regId;
	private Date refDate;
	int fileId;
	public String getFileGroup() {
		return fileGroup;
	}
	public String getOriginalName() {
		return originalName;
	}
	public String getFileName() {
		return fileName;
	}
	public String getFileExtension() {
		return fileExtension;
	}
	public String getRegId() {
		return regId;
	}
	public Date getRefDate() {
		return refDate;
	}
	public int getFileId() {
		return fileId;
	}
	public void setFileGroup(String fileGroup) {
		this.fileGroup = fileGroup;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public void setRefDate(Date refDate) {
		this.refDate = refDate;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	@Override
	public String toString() {
		return "AttachedFileDto [fileGroup=" + fileGroup + ", originalName="
				+ originalName + ", fileName=" + fileName + ", fileExtension="
				+ fileExtension + ", regId=" + regId + ", refDate=" + refDate
				+ ", fileId=" + fileId + "]";
	}
}
