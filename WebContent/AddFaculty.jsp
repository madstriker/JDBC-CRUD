<%@page import="com.stosh.model.Faculty"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Faculty</title>
</head>
<body>

<h1>Add New Faculty</h1>

<a href = "FacultyServlet">Faculty List Page</a><br><br>

<%

Faculty faculty = (Faculty)request.getAttribute("faculty");

if(faculty==null){
	faculty = new Faculty();
	faculty.setFacultyId(0);
	faculty.setFacultyName("");
	faculty.setUniName("");
}

%>

<form action="FacultyServlet" method="post">
<input type="hidden" name="facultyId" value= "<%= faculty.getFacultyId() %>">
Faculty Name: <input type="text" name="facultyName" value="<%= faculty.getFacultyName() %>"><br><br>
University Name: <input type="text" name="uniName" value="<%= faculty.getUniName() %>"><br><br>

<% if(request.getParameter("facultyIdForEdit")==null) { %>
<input type="submit" value = "Add" name = "add">
<% } else { %>
<input type="submit" value = "Update" name = "update">
<% } %>

</form>
</body>
</html>