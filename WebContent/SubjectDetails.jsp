<%@page import="com.stosh.model.Faculty"%>
<%@page import="com.stosh.model.Subject"%>
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
<h1>subject Details</h1>

<a href="SubjectController" >Subject List</a><br><br><br>

<%
	Subject subject = (Subject)request.getAttribute("subject");
%>
<table>
			<tr>
			<td>Subject Id: <%= subject.getSubjectId() %> </td>
			</tr>
			
			<tr>
			<td>Subject Code: <%= subject.getSubjectCode() %></td>
			</tr>
			
			<tr>
			<td>Subject Name: <%= subject.getSubjectName() %></td>
			</tr>
			
			<tr>
			<td>Faculty Name: <%= subject.getFaculty().getFacultyName() %></td>
			</tr>
</table>

	<a href="SubjectController?subjectIdForEdit=<%= subject.getSubjectId() %>">Edit</a><br>
	<a href="SubjectController?subjectIdForDelete=<%= subject.getSubjectId() %>">Delete</a>
			

</body>
</html>