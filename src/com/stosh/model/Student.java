package com.stosh.model;

public class Student {
	
	private int studentId;
	private String studentName;
	private String studentAddress;
	private Object studentImage;
	
	private Faculty faculty;
	
	public Faculty getFaculty() {
		return faculty;
	}
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
	public Object getStudentImage() {
		return studentImage;
	}
	public void setStudentImage(Object studentImage) {
		this.studentImage = studentImage;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentAddress() {
		return studentAddress;
	}
	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}
}
