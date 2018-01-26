package com.stosh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import com.stosh.model.Faculty;
import com.stosh.model.Student;
import com.stosh.model.Subject;
import com.stosh.utils.DBConnection;

public class SubjectDao {
	
public void insertSubject(Subject subject){
		
		try{
			
			Connection con = DBConnection.getConnection();
			String qry = "insert into subject(subjectCode, subjectName, facultyId) values(?,?,?)";
			PreparedStatement pst = con.prepareStatement(qry);
			pst.setString(1, subject.getSubjectCode());
			pst.setString(2, subject.getSubjectName());		
			pst.setInt(3, subject.getFaculty().getFacultyId());
			pst.execute();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
public List<Subject> getSubjectList(){
	try{
		List<Subject> subjectList = new ArrayList<>();
		
		Connection con = DBConnection.getConnection();
		String qry = "select * from subject";
		PreparedStatement pst = con.prepareStatement(qry);
		
		ResultSet rs = pst.executeQuery();
		FacultyDao fd = new FacultyDao();
		while(rs.next()){
			Subject subject = new Subject();
			subject.setSubjectId(rs.getInt("subjectId"));
			subject.setSubjectCode(rs.getString("subjectCode"));
			subject.setSubjectName(rs.getString("subjectName"));
			
			
			Faculty faculty = fd.getFaculty(rs.getInt("facultyId"));
			subject.setFaculty(faculty);
			
			subjectList.add(subject);
		}
		return subjectList;
	}catch(Exception e){
		e.printStackTrace();
	}
	return null;
}

public List<Subject> getSubjectListByFacultyId(int facultyId){
	try{
		List<Subject> subjectList = new ArrayList<>();
		
		Connection con = DBConnection.getConnection();
		String qry = "select * from subject where facultyId=?";
		PreparedStatement pst = con.prepareStatement(qry);
		pst.setInt(1, facultyId);
		
		ResultSet rs = pst.executeQuery();
		FacultyDao fd = new FacultyDao();
		while(rs.next()){
			Subject subject = new Subject();
			subject.setSubjectId(rs.getInt("subjectId"));
			subject.setSubjectCode(rs.getString("subjectCode"));
			subject.setSubjectName(rs.getString("subjectName"));
			
			
			Faculty faculty = fd.getFaculty(rs.getInt("facultyId"));
			subject.setFaculty(faculty);
			
			subjectList.add(subject);
		}
		return subjectList;
	}catch(Exception e){
		e.printStackTrace();
	}
	return null;
}
public Subject getSubject(int subjectId){
	try{
		
		Connection con = DBConnection.getConnection();
		String qry = "select * from subject where subjectId=?";
		PreparedStatement pst = con.prepareStatement(qry);
		pst.setInt(1, subjectId);
		ResultSet rs = pst.executeQuery();
		FacultyDao fd = new FacultyDao();
		while(rs.next()){
			Subject subject = new Subject();
			subject.setSubjectId(rs.getInt("subjectId"));
			subject.setSubjectCode(rs.getString("subjectCode"));
			subject.setSubjectName(rs.getString("subjectName"));
			
			Faculty faculty = fd.getFaculty(rs.getInt("facultyId"));
			subject.setFaculty(faculty);
			return subject;
		}
	
	}catch(Exception e){
		e.printStackTrace();
	}
	return null;
}

public void deleteSubject(int subjectId){
	
	try{
		
		Connection con = DBConnection.getConnection();
		String qry = "delete from subject where subjectId=?";
		
		PreparedStatement pst= con.prepareStatement(qry);
		
		pst.setInt(1, subjectId);
		
		pst.executeUpdate();
		
		con.close();
		
	}
	
	catch(Exception e){
		e.printStackTrace();
	}
}

public void updateSubject(Subject subject){
	
	try{
		
		Connection con = DBConnection.getConnection();
		String qry="update subject set subjectCode=?, subjectName=?, facultyId=? where subjectId=?";
		
		PreparedStatement pst = con.prepareStatement(qry);
		
		pst.setString(1, subject.getSubjectCode());
		pst.setString(2, subject.getSubjectName());
		pst.setInt(3, subject.getFaculty().getFacultyId());
		pst.setInt(4, subject.getSubjectId());
		
		pst.executeUpdate();
		
		con.close();
	}
	
	catch(Exception e){
		e.printStackTrace();
	}
	
}

}
