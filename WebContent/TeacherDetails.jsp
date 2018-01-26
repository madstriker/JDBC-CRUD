<%@page import="com.stosh.model.Teacher"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teacher Details</title>
</head>
<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
</style>
<body>

<h1>Teacher Details</h1>

<a href="TeacherServlet" >Teacher List</a><br><br><br>

<%
Teacher teacher = (Teacher)request.getAttribute("teacher");
%>


<img alt="" src="RetrieveImage?id=<%= teacher.gettId()%>&db=teacher" align="middle" width=150px height=150px><br><br><br>

<table>
			<tr>
			<td>Teacher Id: <%= teacher.gettId() %> </td>
			</tr>
			
			<tr>
			<td>Teacher Name: <%= teacher.gettName() %></td>
			</tr>
			
			<tr>
			<td>Teacher Address: <%= teacher.gettAddress() %></td>
			</tr>
</table>

	<a href="TeacherServlet?teacherIdForEdit=<%= teacher.gettId() %>">Edit</a><br>
	<a href="TeacherServlet?teacherIdForDelete=<%= teacher.gettId() %>">Delete</a>

</body>
</html>