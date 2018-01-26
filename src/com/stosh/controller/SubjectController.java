package com.stosh.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stosh.dao.FacultyDao;
import com.stosh.dao.SubjectDao;
import com.stosh.model.Faculty;
import com.stosh.model.Subject;

/**
 * Servlet implementation class SubjectController
 */
@WebServlet("/SubjectController")
public class SubjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubjectController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{
			
			SubjectDao sbd = new SubjectDao();
			FacultyDao fd = new FacultyDao();
			if(request.getParameter("add")!=null){
				
				Subject subject = new Subject();
				subject.setSubjectCode(request.getParameter("subjectCode"));
				subject.setSubjectName(request.getParameter("subjectName"));
				
				Faculty faculty = new Faculty();
				faculty.setFacultyId(Integer.parseInt( request.getParameter("facultyId")));
				
				subject.setFaculty(faculty);
				
				sbd.insertSubject(subject);
			}
			
			else if(request.getParameter("subjectIdForDelete")!=null){
				int subjectId=Integer.parseInt(request.getParameter("subjectIdForDelete"));
				sbd.deleteSubject(subjectId);
			}

			else if(request.getParameter("subjectIdForEdit")!=null){
				int subjectId= Integer.parseInt(request.getParameter("subjectIdForEdit"));
				Subject subject = sbd.getSubject(subjectId);
				
				List<Faculty> facultyList = fd.getFacultyList();
				request.setAttribute("facultyList", facultyList);
				
				request.setAttribute("subject", subject);
				request.getRequestDispatcher("Subject-form.jsp").forward(request, response);
			}
			
			else if(request.getParameter("subjectIdForDetails")!=null){
				int subjectId = Integer.parseInt(request.getParameter("subjectIdForDetails"));
				Subject subject = sbd.getSubject(subjectId);
				request.setAttribute("subject", subject);
				request.getRequestDispatcher("SubjectDetails.jsp").forward(request, response);
			}
			
			else if(request.getParameter("update")!=null){
				Subject subject  =new Subject();
				Faculty faculty = new Faculty();
				faculty.setFacultyId(Integer.parseInt( request.getParameter("facultyId")));
				
			
				subject.setSubjectId(Integer.parseInt(request.getParameter("subjectId")));
				subject.setSubjectCode(request.getParameter("subjectCode"));
				subject.setSubjectName(request.getParameter("subjectName"));
				
				
				sbd.updateSubject(subject);
			}
			else if(request.getParameter("addsubject")!=null){
				
				List<Faculty> facultyList = fd.getFacultyList();
				request.setAttribute("facultyList", facultyList);
				request.getRequestDispatcher("Subject-form.jsp").forward(request, response);
				return;
			}
			
			
			List<Subject> subjectList = sbd.getSubjectList();
			request.setAttribute("subjectList", subjectList);
			RequestDispatcher rd = request.getRequestDispatcher("Subject-list.jsp");
			rd.forward(request, response);
			
			
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
