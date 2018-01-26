<%@page import="com.stosh.model.Faculty"%>
<%@page import="com.stosh.model.Teacher"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Faculty Manager</title>
</head>
<body>

<a href="FacultyServlet">Faculty List</a>

<h1>Manage Faculty</h1>

<%

	Faculty faculty = (Faculty)request.getAttribute("faculty");
	List<Teacher> teacherList=(ArrayList)request.getAttribute("teacherList");
	
%>

<form action="FacultyServlet" method="post">

Faculty Name: <%= faculty.getFacultyName() %><br><br>
University Name: <%= faculty.getUniName() %><br><br>
Assign Teacher:
<select name = "teacherName">
	<% for(Teacher teacher:teacherList) { %>
			<option value="<%= faculty.getFacultyId() %>"> 
				<%= teacher.gettName() %>
			</option>
	<% } %>
</select>

<input type = "submit" name="assign" value="Assign">

</form>
</body>
</html>	