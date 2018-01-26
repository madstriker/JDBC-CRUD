<%@ page import="com.stosh.model.*" %>
<%@ page import="java.util.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Subject Form</h1>

<%
List<Faculty> facultyList = (ArrayList)request.getAttribute("facultyList");

Subject subject = (Subject)request.getAttribute("subject");

if(subject==null){
	subject = new Subject();
	subject.setSubjectName("");
	subject.setSubjectCode("");
	
	Faculty faculty = new Faculty();
	subject.setFaculty(faculty);
}

%>

<form action="SubjectController" method="post">
<input type= "hidden" name = "subjectId" value="<%= subject.getSubjectId() %>">
<input type= "hidden" name = "facultyId" value="<%= subject.getFaculty().getFacultyId() %>">
<select name="facultyId">
	<option value="0">--Choose Subject--</option>
	<% for(Faculty faculty:facultyList){ %>
	<option value="<%= faculty.getFacultyId()%>" <%= faculty.getFacultyId()==subject.getFaculty().getFacultyId()?"selected":"" %>>
		<%= faculty.getFacultyName() %>
	</option>
	<% } %>
</select>

Subject Code: <input type="text" name="subjectCode" value="<%= subject.getSubjectCode() %>" >
Subject Name: <input type="text" name="subjectName" value="<%= subject.getSubjectName() %>">

<% if(request.getParameter("subjectIdForEdit")==null) { %>
<input type="submit" value="Add" name="add">
<% } %>
<% if(request.getParameter("subjectIdForEdit")!=null) { %>
<input type = "submit" value="Update" name="update">
<% } %>

</form>
</body>
</html>