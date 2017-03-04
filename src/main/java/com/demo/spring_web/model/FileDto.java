package com.demo.spring_web.model;

public class FileDto {
	private int id;
	private String fileName;
	private String fileUrl;
	private String fileType;
	private String fileFullUrl;
	
    public String getFileFullUrl() {
		return fileFullUrl;
	}

	public void setFileFullUrl(String fileFullUrl) {
		this.fileFullUrl = fileFullUrl;
	}

	public int getId() {
        return id;
    }

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
}
