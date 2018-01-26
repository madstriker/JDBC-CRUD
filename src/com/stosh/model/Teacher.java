package com.stosh.model;

public class Teacher {
	
	private int tId;
	private String tName;
	private String tAddress;
	private Object tImage;
	
	
	public Object gettImage() {
		return tImage;
	}
	public void settImage(Object tImage) {
		this.tImage = tImage;
	}
	public int gettId() {
		return tId;
	}
	public void settId(int tId) {
		this.tId = tId;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	public String gettAddress() {
		return tAddress;
	}
	public void settAddress(String tAddress) {
		this.tAddress = tAddress;
	}

}
