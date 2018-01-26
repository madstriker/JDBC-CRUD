package com.stosh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import com.stosh.model.Teacher;
import com.stosh.utils.DBConnection;

public class TeacherDao {
	
	public void insertTeacher(Teacher teacher){
		
		try{
			Connection con = DBConnection.getConnection();
			String qry = "insert into teacher(teacherName, teacherAddress, teacherImage) values(?,?,?)";
			PreparedStatement pst = con.prepareStatement(qry);
			
			pst.setString(1, teacher.gettName());
			pst.setString(2, teacher.gettAddress());
			pst.setBlob(3, ((Part)teacher.gettImage()).getInputStream());
			
			pst.execute();
			
			con.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public List<Teacher> getTeacherList(){
		try{
			List<Teacher> teacherList = new ArrayList<>();
			
			Connection con = DBConnection.getConnection();
			String qry = "select * from teacher";
			PreparedStatement pst = con.prepareStatement(qry);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
				Teacher teachers = new Teacher();
				teachers.settId(rs.getInt("teacherId"));
				teachers.settName(rs.getString("teacherName"));
				teachers.settAddress(rs.getString("teacherAddress"));
				teachers.settImage(rs.getBlob("teacherImage"));
				
				teacherList.add(teachers);
			}
			return teacherList;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Teacher getTeacher(int teacherId){
		try{
			
			Connection con = DBConnection.getConnection();
			String qry = "select * from teacher where teacherId=?";

			PreparedStatement pst = con.prepareStatement(qry);
			
			pst.setInt(1, teacherId);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
				Teacher teachers = new Teacher();
				
				teachers.settId(rs.getInt("teacherId"));
				teachers.settName(rs.getString("teacherName"));
				teachers.settAddress(rs.getString("teacherAddress"));
				teachers.settImage(rs.getBlob("teacherImage"));
				
				return teachers;
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void deleteTeacher(int teacherId){
		
		try{
			
			Connection con = DBConnection.getConnection();
			String qry = "delete from teacher where teacherId=?";
			
			PreparedStatement pst = con.prepareStatement(qry);
			
			pst.setInt(1, teacherId);
			
			pst.executeUpdate();
			
			con.close();
			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void updateTeacher(Teacher teacher){
		
		try{
			
			Connection con = DBConnection.getConnection();
			String qry="update teacher set teacherName=?, teacherAddress=? where teacherId=?";
			
			PreparedStatement pst= con.prepareStatement(qry);
			
			pst.setString(1, teacher.gettName());
			pst.setString(2, teacher.gettAddress());
			pst.setInt(3, teacher.gettId());
			
			pst.executeUpdate();
			
			con.close();
			
			if(((Part)teacher.gettImage()).getSize()!=0){
				updateTeacherImage(teacher);
			}
			
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void updateTeacherImage(Teacher teacher){
		
		try{
			
			Connection con = DBConnection.getConnection();
			String qry="update teacher set teacherImage=? where teacherId=?";
			
			PreparedStatement pst=con.prepareStatement(qry);
			
			
			pst.setBlob(1,((Part) teacher.gettImage()).getInputStream());
			pst.setInt(2, teacher.gettId());
			
			pst.executeUpdate();
			
			con.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<Teacher> getTeacherList(String searchTeacher){
		try{
			List<Teacher> teacherList = new ArrayList<>();
			
			Connection con = DBConnection.getConnection();
			String qry = "select * from teacher where teacherName like '%"+searchTeacher+"%' or teacherAddress like '%"+searchTeacher+"%'";
			PreparedStatement pst = con.prepareStatement(qry);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
				Teacher teachers = new Teacher();
				teachers.settId(rs.getInt("teacherId"));
				teachers.settName(rs.getString("teacherName"));
				teachers.settAddress(rs.getString("teacherAddress"));
				teachers.settImage(rs.getBlob("teacherImage"));
				
				teacherList.add(teachers);
			}
			return teacherList;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
