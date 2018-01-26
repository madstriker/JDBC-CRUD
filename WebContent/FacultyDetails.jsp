<%@page import="com.stosh.model.Faculty"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
</style>
<body>
<h1>faculty Details</h1>

<a href="FacultyServlet" >Faculty List</a><br><br><br>

<%
Faculty faculty = (Faculty)request.getAttribute("faculty");

if(faculty==null){
	
	faculty = new Faculty();
	
	faculty.setFacultyId(0);
	faculty.setFacultyName("");
	faculty.setUniName("");
	faculty.setTeacherName("");
}

%>
<table>
			<tr>
			<td>Faculty Id: <%= faculty.getFacultyId() %> </td>
			</tr>
			
			<tr>
			<td>Faculty Name: <%= faculty.getFacultyName() %></td>
			</tr>
			
			<tr>
			<td>University Name: <%= faculty.getUniName() %></td>
			</tr>
			
			<tr>
			<td>Assigned Teacher: <%= faculty.getTeacherName() %></td>
			</tr>
</table>

	<a href="FacultyServlet?facultyIdForEdit=<%= faculty.getFacultyId() %>">Edit</a><br>
	<a href="FacultyServlet?facultyIdForDelete=<%= faculty.getFacultyId() %>">Delete</a>
			

</body>
</html>