<%@page import="java.util.*"%>
<%@page import="com.stosh.model.Faculty"%>
<%@page import="java.sql.Blob"%>
<%@page import="com.stosh.model.Student"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Form</title>
</head>
<body>

<h1>Student Form</h1>


<a href="StudentServlet">Student List</a>


<%

List<Faculty> facultyList = (ArrayList)request.getAttribute("facultyList");
Student student = (Student)request.getAttribute("student");

if(student==null){
	
	student = new Student();
	
	student.setStudentId(0);
	student.setStudentName("");
	student.setStudentAddress("");
	
	Faculty faculty = new Faculty();
	student.setFaculty(faculty);
}

%>

<form action="StudentServlet" method="post" enctype="multipart/form-data">

<input type= "hidden" name = "studentId" value="<%= student.getStudentId() %>">

<select name="facultyId">
<option value="0">--Select Faculty--</option>
<% for(Faculty faculty:facultyList) { %>
<option value="<%= faculty.getFacultyId()%>" <%= faculty.getFacultyId()==student.getFaculty().getFacultyId()?"selected":" " %>>
	<%= faculty.getFacultyName() %>
</option>
<% } %>
</select>

<table>
<tr><td>Student Name: <input type="text" name="studentName" value = "<%= student.getStudentName() %>"></td></tr>
<tr><td>Student Address: <input type="text" name= "studentAddress" value = "<%= student.getStudentAddress() %>"></td></tr>


<tr><td>

<% if(student.getStudentId()!=0 && student.getStudentImage()!=null ){ %>
<img alt="" src="RetrieveImage?id=<%= student.getStudentId()%>&db=student">
</td>

<td>
Student Image: <input type="file" name="studentImage" value="<%= (Blob)student.getStudentImage() %>">

<% } %>
</td></tr>

<tr><td>
<% if(request.getParameter("studentIdForEdit")==null){ %>

Student Image: <input type="file" name="studentImage">

<input type="submit" value="Add" name="add">	
	
	
<% } %>
</td></tr>
<tr><td>
<% if(request.getParameter("studentIdForEdit")!=null){ %>
	<input type="submit" value="Update" name="update">
<% } %>
</td></tr>

</table>
</form>
</body>
</html>