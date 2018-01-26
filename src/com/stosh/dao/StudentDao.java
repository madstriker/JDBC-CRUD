package com.stosh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import com.stosh.model.Faculty;
import com.stosh.model.Student;
import com.stosh.utils.DBConnection;

public class StudentDao {
	
	public void insertStudent(Student student){

	try{
		Connection con = DBConnection.getConnection();
		String qry = "insert into student(studentName, studentAddress, studentImage , facultyId) values(?,?,?,?)";
		PreparedStatement pst=con.prepareStatement(qry);
		
		pst.setString(1, student.getStudentName());
		pst.setString(2, student.getStudentAddress());
		pst.setBlob(3, ((Part)student.getStudentImage()).getInputStream());
		pst.setInt(4, student.getFaculty().getFacultyId());
		
		pst.execute();
		
		con.close();
		
	}
	catch(Exception  e){
		e.printStackTrace();
	}
	}

	
	public List<Student> getStudentList(){
		try{
			List<Student> studentList = new ArrayList<>();
			
			Connection con = DBConnection.getConnection();
			String qry = "select * from student";
			PreparedStatement pst = con.prepareStatement(qry);
			
			ResultSet rs = pst.executeQuery();
			FacultyDao fd = new FacultyDao();
			
			while(rs.next()){
				Student student = new Student();
				student.setStudentId(rs.getInt("studentId"));
				student.setStudentName(rs.getString("studentName"));
				student.setStudentAddress(rs.getString("studentAddress"));
				student.setStudentImage(rs.getBlob("studentImage"));
				Faculty faculty = fd.getFaculty(rs.getInt("facultyId"));
				student.setFaculty(faculty);
				
				studentList.add(student);
			}
			return studentList;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public Student getStudent(int studentId){
		
		try{
			FacultyDao fd = new FacultyDao();
			Connection con = DBConnection.getConnection();
			String qry = "select * from student where studentId=?";
			
			PreparedStatement pst = con.prepareStatement(qry);
			
			pst.setInt(1, studentId);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
				Student student = new Student();
				student.setStudentId(rs.getInt("studentId"));
				student.setStudentName(rs.getString("studentName"));
				student.setStudentAddress(rs.getString("studentAddress"));
				student.setStudentImage(rs.getBlob("studentImage"));
				Faculty faculty = fd.getFaculty(rs.getInt("facultyId"));
				student.setFaculty(faculty);
				
				return student;				
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void deleteStudent(int studentId){
		
		try{
			
			Connection con = DBConnection.getConnection();
			String qry = "delete from student where studentId=?";
			
			PreparedStatement pst= con.prepareStatement(qry);
			
			pst.setInt(1, studentId);
			
			pst.executeUpdate();
			
			con.close();
			
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	public void updateStudent(Student student){
		
		try{
			
			Connection con = DBConnection.getConnection();
			String qry="update student set studentName=?, studentAddress=?, facultyId=? where studentId=?";
			
			PreparedStatement pst = con.prepareStatement(qry);
			
			pst.setString(1, student.getStudentName());
			pst.setString(2, student.getStudentAddress());
			pst.setInt(3, student.getFaculty().getFacultyId());
			pst.setInt(4, student.getStudentId());
			
			pst.executeUpdate();
			
			con.close();
			
			if(((Part)student.getStudentImage()).getSize()!=0){
				updateStudentImage(student);
			}
			
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
public void updateStudentImage(Student student){
		
		try{
			
			Connection con = DBConnection.getConnection();
			String qry="update student set studentImage=? where studentId=?";
			
			PreparedStatement pst = con.prepareStatement(qry);
			
			pst.setBlob(1, ((Part)student.getStudentImage()).getInputStream());
			pst.setInt(2, student.getStudentId());
			
			
			pst.executeUpdate();
			
			con.close();
			
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		
	}


public List<Student> getStudentList(String searchStudent){
	try{
		List<Student> studentList = new ArrayList<>();
		
		Connection con = DBConnection.getConnection();
		String qry = "select * from student where studentName like '%"+searchStudent+"%' or studentAddress like '%"+searchStudent+"%'";
		System.out.println("Qry--->>" + qry);
		
		PreparedStatement pst = con.prepareStatement(qry);
		
		ResultSet rs = pst.executeQuery();
		FacultyDao fd = new FacultyDao();
		
		while(rs.next()){
			Student student = new Student();
			student.setStudentId(rs.getInt("studentId"));
			student.setStudentName(rs.getString("studentName"));
			student.setStudentAddress(rs.getString("studentAddress"));
			student.setStudentImage(rs.getBlob("studentImage"));
			Faculty faculty = fd.getFaculty(rs.getInt("facultyId"));
			student.setFaculty(faculty);
			
			studentList.add(student);
		}
		return studentList;
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return null;	
	}	

}
