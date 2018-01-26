package com.stosh.controller;

import java.io.IOException;
//import java.sql.Blob;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.stosh.dao.FacultyDao;
import com.stosh.dao.StudentDao;
import com.stosh.dao.SubjectDao;
import com.stosh.model.Faculty;
import com.stosh.model.Student;
import com.stosh.model.Subject;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
@MultipartConfig(maxFileSize = 161772145)
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StudentDao sd = new StudentDao();
		FacultyDao fd = new FacultyDao();
		SubjectDao sbd = new SubjectDao();
		
		try{
			if(request.getParameter("add")!=null){
				
			Student student = new Student();
			Faculty faculty = new Faculty();
			faculty.setFacultyId(Integer.parseInt( request.getParameter("facultyId")));
			
			student.setFaculty(faculty);
			student.setStudentName(request.getParameter("studentName"));
			student.setStudentAddress(request.getParameter("studentAddress"));
			
			Part studentImage = request.getPart("studentImage");
			student.setStudentImage(studentImage);
			
			sd.insertStudent(student);
			}
			
			else if(request.getParameter("studentIdForDelete")!=null){
				
				int stduentId = Integer.parseInt(request.getParameter("studentIdForDelete"));
				sd.deleteStudent(stduentId);
				
			}
			
			else if(request.getParameter("studentIdForEdit")!=null){
				
				int studentId = Integer.parseInt(request.getParameter("studentIdForEdit"));
				Student student = sd.getStudent(studentId);
				
				List<Faculty> facultyList = fd.getFacultyList();
				request.setAttribute("facultyList", facultyList);
				
				request.setAttribute("student", student);
				request.getRequestDispatcher("Student-form.jsp").forward(request, response);
				
				return;
			}
			
			else if(request.getParameter("studentMark")!=null){
				int studentId = Integer.parseInt(request.getParameter("studentMark"));
				Student student = sd.getStudent(studentId);
				
				List<Subject> subjectListByFacultyId = sbd.getSubjectListByFacultyId(student.getFaculty().getFacultyId());
				request.setAttribute("student", student);
				request.setAttribute("subjectListByFacultyId", subjectListByFacultyId);
				
				request.getRequestDispatcher("StudentMark-form.jsp").forward(request, response);
			}
			
			else if(request.getParameter("saveStudentMarks")!=null) {
				
				int studentId = Integer.parseInt(request.getParameter("studentId"));
				Student student = sd.getStudent(studentId);
				
				List<Subject> subjectListByFacultyId = sbd.getSubjectListByFacultyId(student.getFaculty().getFacultyId());
				
				for(Subject subject:subjectListByFacultyId){
					int subjectId = subject.getSubjectId();
					System.out.println(studentId+"--"+ subject.getFaculty().getFacultyId() +"--"+subjectId + "---" + request.getParameter("subject_"+subjectId));
				}
			}
			
			else if(request.getParameter("studentIdForDetails")!=null){
				
				int studentId = Integer.parseInt(request.getParameter("studentIdForDetails"));
				Student student = sd.getStudent(studentId);
				
				request.setAttribute("student", student);
				request.getRequestDispatcher("StudentDetails.jsp").forward(request, response);
				
				return;
			}
			
			else if(request.getParameter("update")!= null){
				
				Student student = new Student();
				Faculty faculty = new Faculty();
				faculty.setFacultyId(Integer.parseInt( request.getParameter("facultyId")));
				
				student.setFaculty(faculty);
				student.setStudentId(Integer.parseInt(request.getParameter("studentId")));
				student.setStudentName(request.getParameter("studentName"));
				student.setStudentAddress(request.getParameter("studentAddress"));
				Part studentImage = request.getPart("studentImage");
				student.setStudentImage(studentImage);
				
				sd.updateStudent(student);
			}
			
			else if(request.getParameter("search")!=null){
				
				String searchStudent = request.getParameter("searchStudent");
				
				List<Student> studentList = sd.getStudentList(searchStudent);
				
				request.setAttribute("studentList", studentList);
				
				request.getRequestDispatcher("Student-list.jsp").forward(request, response);
				
				return;
			}
			
			else if(request.getParameter("addstudent")!=null){
				
				List<Faculty> facultyList = fd.getFacultyList();
				request.setAttribute("facultyList", facultyList);
				request.getRequestDispatcher("Student-form.jsp").forward(request, response);
				return;
			}
			
			List<Student> studentList= sd.getStudentList();
			
			request.setAttribute("studentList", studentList);
			
			request.getRequestDispatcher("Student-list.jsp").forward(request, response);
			
		}catch(Exception e){ 
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
