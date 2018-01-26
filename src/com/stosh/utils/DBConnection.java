package com.stosh.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public static Connection getConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stoshmdb","root","");
			return con;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
