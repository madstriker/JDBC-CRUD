<%@page import="com.stosh.model.Teacher"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teacher Form</title>
</head>
<body>

<h1>Teacher Form</h1>

<a href="TeacherServlet">Teacher List</a>

<% 

Teacher teacher = (Teacher)request.getAttribute("teacher");

if(teacher==null){
	
	teacher = new Teacher();
	
	teacher.settId(0);
	teacher.settName("");
	teacher.settAddress("");
}

%>

<form action="TeacherServlet" method="post" enctype="multipart/form-data">
<input type="hidden" name= "teacherId" value = "<%= teacher.gettId() %>">
Teacher Name: <input type = "text" name="teacherName"  value = "<%= teacher.gettName() %>">
Teacher Address: <input type = "text" name = "teacherAddress" value = "<%= teacher.gettAddress() %>">

<% if(teacher.gettId()!=0 && teacher.gettImage()!=null) { %>

<img alt="" src="RetrieveImage?id=<%= teacher.gettId()%>&db=teacher" width=150px height=150px>
Teacher Image: <input type="file" name="teacherImage" >

<% } %>

<% if(request.getParameter("teacherIdForEdit")==null) { %>
	Teacher Image: <input type="file" name="teacherImage">
	<input type = "submit" value= "Add" name="add">
<% } %>


<% if(request.getParameter("teacherIdForEdit")!=null) { %>
	<input type = "submit" value= "Update" name= "update">
<% } %>

</form>
</body>
</html>