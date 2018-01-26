package com.stosh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.stosh.model.Faculty;
import com.stosh.utils.DBConnection;

public class FacultyDao {

	public void insertFaculty(Faculty faculty){
		
		try{
			
			Connection con = DBConnection.getConnection();
			String qry = "insert into faculty(facultyName, uniName) values(?,?)";
			PreparedStatement pst = con.prepareStatement(qry);
			
			pst.setString(1, faculty.getFacultyName());
			pst.setString(2, faculty.getUniName());
			
			pst.execute();
			con.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<Faculty> getFacultyList(){
		
		try{
			List<Faculty> facultyList = new ArrayList<>();
			
			Connection con = DBConnection.getConnection();
			String qry="select*from faculty";
			PreparedStatement pst=con.prepareStatement(qry);
			
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				Faculty faculty=new Faculty();
				faculty.setFacultyId(rs.getInt("facultyId"));
				faculty.setFacultyName(rs.getString("facultyName"));
				faculty.setUniName(rs.getString("uniName"));
				
				facultyList.add(faculty);
			}
			return facultyList;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
			
	public Faculty getFaculty(int facultyId){
		
		try{
			
			Connection con = DBConnection.getConnection();
			String qry="select * from faculty where facultyId=?";
			PreparedStatement pst  = con.prepareStatement(qry);
			
			pst.setInt(1, facultyId);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
				Faculty faculty = new Faculty();
				faculty.setFacultyId(rs.getInt("facultyId"));
				faculty.setFacultyName(rs.getString("facultyName"));
				faculty.setUniName(rs.getString("uniName"));
				
				return faculty;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteFaculty(int facultyId){
		try{
			
			Connection con = DBConnection.getConnection();
			String qry = "delete from faculty where facultyId=?";
			PreparedStatement pst= con.prepareStatement(qry);
			
			pst.setInt(1, facultyId);
			pst.executeUpdate();
			con.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void updateFaculty(Faculty faculty){
		try{
			Connection con = DBConnection.getConnection();
			String qry= "update faculty set facultyName=? where facultyId=?";
			PreparedStatement pst= con.prepareStatement(qry);
			
			pst.setString(1, faculty.getFacultyName());
//			pst.setString(2, faculty.getUniName());
//			pst.setString(3, faculty.getTeacherName());
			pst.setInt(2, faculty.getFacultyId());
			
			pst.executeUpdate();
			con.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
