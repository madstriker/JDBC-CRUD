<%@ page import="com.stosh.model.*" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Mark Form</title>
</head>
<body>
<h1>Student Marks</h1>

<a href="StudentServlet"> Goto Student List</a><br><br><br>

<%

Student student = (Student)request.getAttribute("student");
List<Subject> subjectListByFacultyId = (ArrayList)request.getAttribute("subjectListByFacultyId");

%>
<img alt="" src="RetrieveImage?id=<%= student.getStudentId()%>&db=student">
<br>
Student Name: <%= student.getStudentName() %>
<br>
Faculty Name: <%= student.getFaculty().getFacultyName() %>

<form action="StudentServlet" method="post">
<input type="hidden" name="studentId" value="<%= student.getStudentId()%>"> 
<table>
<% for(Subject subject : subjectListByFacultyId){ %>
<tr>
	<td><%= subject.getSubjectName()%></td><td><input type="text" name="subject_<%=subject.getSubjectId()%>"></td>
</tr>
<%} %>
</table>

<input type="submit" name="saveStudentMarks" value="Save Marks">

</form>
</body>
</html>