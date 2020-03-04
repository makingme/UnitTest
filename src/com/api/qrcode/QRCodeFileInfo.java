package com.api.qrcode;

public class QRCodeFileInfo{
	String fileName="";
	Long lastModified=0L;
	String context="";
	int index=-1;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Long getLastModified() {
		return lastModified;
	}
	public void setLastModified(Long lastModified) {
		this.lastModified = lastModified;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
