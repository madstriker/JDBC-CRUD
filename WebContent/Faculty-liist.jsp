<%@page import="com.stosh.model.Faculty"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Faculty List</title>
</head>
<body>

<h1>Faculty List</h1>

<a href="AddFaculty.jsp">Add New Faculty</a><br><br>


<%
	List<Faculty> facultyList= (ArrayList)request.getAttribute("facultyList");
%>

<table>

<%
for(Faculty faculty:facultyList){

%>
	<tr>
		<td><%= faculty.getFacultyName() %></td>
		<td><%= faculty.getUniName() %></td>
		<td><%= faculty.getTeacherName() %></td>
	
		<td>
			<a href="FacultyServlet?facultyIdForAssign=<%= faculty.getFacultyId() %>">Assign Teachers</a><br>
			<a href="FacultyServlet?facultyIdForEdit=<%= faculty.getFacultyId() %>">Edit</a><br>
			<a href="FacultyServlet?facultyIdForDelete=<%= faculty.getFacultyId() %>">Delete</a><br>
			<a href="FacultyServlet?facultyIdForDetails=<%= faculty.getFacultyId() %>">Details</a><br>
		</td>
	</tr>
<% } %>

</table>
</body>
</html>