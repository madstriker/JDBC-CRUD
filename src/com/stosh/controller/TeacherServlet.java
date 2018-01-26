package com.stosh.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.stosh.dao.TeacherDao;
import com.stosh.model.Teacher;

/**
 * Servlet implementation class TeacherServlet
 */
@WebServlet("/TeacherServlet")
@MultipartConfig(maxFileSize = 199029852)
public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	TeacherDao td = new TeacherDao();
	
	try{
		
		if(request.getParameter("add")!=null){
		
		Teacher tea = new Teacher();
		tea.settName(request.getParameter("teacherName"));
		tea.settAddress(request.getParameter("teacherAddress"));
		
		Part teacherImage = request.getPart("teacherImage");
		tea.settImage(teacherImage);
		
		td.insertTeacher(tea);
		
		}
		
		else if(request.getParameter("teacherIdForDelete")!=null){
		
			int teacherId = Integer.parseInt(request.getParameter("teacherIdForDelete"));
			td.deleteTeacher(teacherId);
			
		}
		
		else if(request.getParameter("teacherIdForEdit")!= null){
			
			int teacherId=Integer.parseInt(request.getParameter("teacherIdForEdit"));
			Teacher teacher = td.getTeacher(teacherId);
			
			request.setAttribute("teacher", teacher);
			request.getRequestDispatcher("Teacher-form.jsp").forward(request, response);
			
			return;
		}
		
		else if(request.getParameter("teacherIdForDetails")!= null){
			
			int teacherId=Integer.parseInt(request.getParameter("teacherIdForDetails"));
			Teacher teacher = td.getTeacher(teacherId);
			
			request.setAttribute("teacher", teacher);
			request.getRequestDispatcher("TeacherDetails.jsp").forward(request, response);
			
			return;
		}
		
		
		else if(request.getParameter("update")!=null){
			
			Teacher teacher = new Teacher();
			
			teacher.settId(Integer.parseInt(request.getParameter("teacherId")));
			teacher.settName(request.getParameter("teacherName"));
			teacher.settAddress(request.getParameter("teacherAddress"));
			Part teacherImage = request.getPart("teacherImage");
			teacher.settImage(teacherImage);
			
			td.updateTeacher(teacher);
			
		}
		
		else if(request.getParameter("search")!=null){
			
			String searchTeacher = request.getParameter("searchTeacher");
			
			List<Teacher> teachList = td.getTeacherList(searchTeacher);
			
			request.setAttribute("teachList", teachList);
			
			request.getRequestDispatcher("Teacher-list.jsp").forward(request, response);
			
		}
		
		List<Teacher> teachList = td.getTeacherList();
		
		request.setAttribute("teachList", teachList);
		
		request.getRequestDispatcher("Teacher-list.jsp").forward(request, response);
		
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
