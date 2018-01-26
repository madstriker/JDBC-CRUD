<%@page import="com.stosh.model.Student"%>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student List</title>
</head>
<style>
table, th, td {
    border: 1px solid black;
}
</style>

<body>
<h1>Student List</h1>

<a href = "StudentServlet?addstudent">Add</a><br><br>

<%

	List<Student> studentList=(ArrayList)request.getAttribute("studentList");

%>

<form>

<input type="text" name= "searchStudent" >
<input type="submit" name="search" value= "Search"><br><br>

</form>


<table>

<% 
int i =0;
for(Student student:studentList){ 
%>
	<tr>
	
		<td><%= ++i %>
		<td><%= student.getStudentId() %></td>
		<td><%= student.getStudentName() %></td>
		<td><%= student.getStudentAddress() %></td>
		<td><%= student.getFaculty().getFacultyName() %></td>
		<td><%= student.getFaculty().getUniName() %></td>
		<td><img alt="" src="RetrieveImage?id=<%= student.getStudentId() %>&db=student" width=150px height=150px></td>
		
		<td>
			<a href="StudentServlet?studentIdForEdit=<%= student.getStudentId() %>">Edit</a>
			<a href="StudentServlet?studentIdForDelete=<%= student.getStudentId() %>">Delete</a>
			<a href="StudentServlet?studentMark=<%= student.getStudentId() %>">Add Marks</a>
			<a href="StudentServlet?studentIdForDetails=<%= student.getStudentId() %>">Details</a>
		</td>
		
	</tr>	

<% } %>

</table>
</body>
</html>