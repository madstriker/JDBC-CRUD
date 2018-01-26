<%@page import="com.stosh.model.Student"%>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Details</title>
</head>

<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
</style>
<body>

<h1>Student Details</h1>

<a href="StudentServlet" >Student List</a><br><br><br>

<%
Student student = (Student)request.getAttribute("student");

if(student==null){
	
	student = new Student();
	
	student.setStudentId(0);
	student.setStudentName("");
	student.setStudentAddress("");
}

%>


<img alt="" src="RetrieveImage?id=<%= student.getStudentId()%>&db=student" align="middle"><br><br><br>

<table>
			<tr>
			<td>Student Id: <%= student.getStudentId() %> </td>
			</tr>
			
			<tr>
			<td>Student Name: <%= student.getStudentName() %></td>
			</tr>
			
			<tr>
			<td>Student Address: <%= student.getStudentAddress() %></td>
			</tr>
</table>

	<a href="StudentServlet?studentIdForEdit=<%= student.getStudentId() %>">Edit</a><br>
	<a href="StudentServlet?studentIdForDelete=<%= student.getStudentId() %>">Delete</a>
			

</body>
</html>