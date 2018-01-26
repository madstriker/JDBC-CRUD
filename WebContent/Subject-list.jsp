<%@page import="com.stosh.model.Subject"%>
<%@page import="com.stosh.model.Faculty"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Subject List</title>
</head>
<body>

<h1>Subject List</h1>

<a href="SubjectController?addsubject">Add New Subject</a><br><br>

<%
	List<Subject> subjectList= (ArrayList)request.getAttribute("subjectList");
%>

<table>

<%
for(Subject subject:subjectList){

%>
	<tr>
		<td><%= subject.getSubjectId() %></td>
		<td><%= subject.getSubjectCode() %></td>
		<td><%= subject.getSubjectName() %></td>
		<td><%= subject.getFaculty().getFacultyName() %></td>
		<td>
			<a href="SubjectController?subjectIdForEdit=<%= subject.getSubjectId() %>">Edit</a><br>
			<a href="SubjectController?subjectIdForDelete=<%= subject.getSubjectId() %>">Delete</a><br>
			<a href="SubjectController?subjectIdForDetails=<%= subject.getSubjectId() %>">Details</a><br>
		</td>
	</tr>
<% } %>

</table>
</body>
</html>