package com.stosh.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stosh.dao.FacultyDao;
import com.stosh.dao.TeacherDao;
import com.stosh.model.Faculty;
import com.stosh.model.Teacher;

/**
 * Servlet implementation class FacultyServlet
 */
@WebServlet("/FacultyServlet")
public class FacultyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FacultyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		FacultyDao fd = new FacultyDao();
		TeacherDao td = new TeacherDao();
		
		try{
			
			if(request.getParameter("add")!=null){
				
				Faculty faculty = new Faculty();
				faculty.setFacultyName(request.getParameter("facultyName"));
				faculty.setUniName(request.getParameter("uniName"));
				faculty.setTeacherName(request.getParameter("teacherName"));
				
				fd.insertFaculty(faculty);
			}
			
			else if(request.getParameter("facultyIdForDelete")!=null){
				int facultyId = Integer.parseInt(request.getParameter("facultyIdForDelete"));
				fd.deleteFaculty(facultyId);
			}
			
			else if(request.getParameter("facultyIdForEdit")!=null){
				int facultyId = Integer.parseInt(request.getParameter("facultyIdForEdit"));
				Faculty faculty = fd.getFaculty(facultyId);
				request.setAttribute("faculty", faculty);
				request.getRequestDispatcher("Faculty-form.jsp").forward(request, response);
			}
			
			else if(request.getParameter("facultyIdForDetails")!=null){
				int facultyId = Integer.parseInt(request.getParameter("facultyIdForDetails"));
				Faculty faculty = fd.getFaculty(facultyId);
				request.setAttribute("faculty", faculty);
				request.getRequestDispatcher("FacultyDetails.jsp").forward(request, response);
			}
//			
//			else if(request.getParameter("facultyIdForAssign")!=null){
//				int facultyId = Integer.parseInt(request.getParameter("facultyIdForAssign"));
//				Faculty faculty = fd.getFaculty(facultyId);
//				List<Teacher> teacherList = td.getTeacherList();
//				request.setAttribute("faculty", faculty);
//				request.setAttribute("teacherList", teacherList);
//				request.getRequestDispatcher("FacultyManager.jsp").forward(request, response);
//			}
			
			else if(request.getParameter("update")!=null){
				Faculty faculty = new Faculty();
				faculty.setFacultyId(Integer.parseInt(request.getParameter("facultyId")));
				faculty.setFacultyName(request.getParameter("facultyName"));
//				faculty.setUniName(request.getParameter("uniName"));
//				faculty.setTeacherName(request.getParameter("teacherName"));
				
				fd.updateFaculty(faculty);
			}
			
//			else if(request.getParameter("assign")!=null){
//				Faculty faculty = new Faculty();
//				faculty.setFacultyId(Integer.parseInt(request.getParameter("facultyId")));
//				faculty.setTeacherName(request.getParameter("teacherName"));
//				
//				fd.updateFaculty(faculty);
//				
//			}
			
			List<Faculty> facultyList = fd.getFacultyList();
			request.setAttribute("facultyList", facultyList);
			request.getRequestDispatcher("Faculty-list.jsp").forward(request, response);
		}
		catch(Exception e){
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
