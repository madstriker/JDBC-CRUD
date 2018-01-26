<%@page import="com.stosh.model.Teacher"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teacher List</title>
</head>
<style>
table, th, td {
    border: 1px solid black;
}
</style>
<body>

<h1>Teacher List</h1>

<a  href = "Teacher-form.jsp">Add</a><br><br>

<%
	List<Teacher> tList = (ArrayList)request.getAttribute("teachList");

%>


<form>
<input type="text" name="searchTeacher">
<input type="submit" name="search" value="Search"><br><br>
</form>


<table>

<% 
int i=0;
for(Teacher teaches:tList) { 
%>
	<tr>

		<td><%= ++i %>
		<td><%= teaches.gettName() %></td>
		<td><%= teaches.gettAddress() %></td>
		<td><img alt="" src="RetrieveImage?id=<%= teaches.gettId() %>&db=teacher" width=150px height=150px></td>
		
		<td><a href="TeacherServlet?teacherIdForEdit=<%= teaches.gettId() %>">Edit</a></td>
		<td><a href="TeacherServlet?teacherIdForDelete=<%= teaches.gettId() %>">Delete</a></td>
		<td><a href="TeacherServlet?teacherIdForDetails=<%= teaches.gettId() %>">Details</a></td>

	</tr>
	
<% } %>
	
</table>
</body>
</html>