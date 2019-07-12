package com.bit.model;

public class FileGroupDto {

	private String fileGroup,path;

	public String getFileGroup() {
		return fileGroup;
	}

	public void setFileGroup(String fileGroup) {
		this.fileGroup = fileGroup;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "FileGroupDto [fileGroup=" + fileGroup + ", path=" + path + "]";
	}
}