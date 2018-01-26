package com.stosh.controller;

import java.io.IOException;
import java.sql.Blob;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stosh.dao.StudentDao;
import com.stosh.dao.TeacherDao;
import com.stosh.model.Student;
import com.stosh.model.Teacher;

/**
 * Servlet implementation class RetrieveImage
 */
@WebServlet("/RetrieveImage")
public class RetrieveImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	try{
		
		ServletOutputStream out = response.getOutputStream();
		
		String id = request.getParameter("id");
		String db = request.getParameter("db");
		
		
		
		if(db.equals("student")){
			StudentDao sd =new StudentDao();
			Student student = sd.getStudent(Integer.parseInt(id));
//		Blob blob = (Blob)student.getStudentImage();
		byte[] imgData = ((Blob)student.getStudentImage()).getBytes(1, (int)((Blob)student.getStudentImage()).length());
		out.write(imgData);
		}
		
		
		
		else if(db.equals("teacher")){
			System.out.println(id);
			TeacherDao td =new TeacherDao();
			Teacher teacher = td.getTeacher(Integer.parseInt(id));
			byte[] imgData = ((Blob)teacher.gettImage()).getBytes(1, (int)((Blob)teacher.gettImage()).length());
			out.write(imgData);
		}
		
		
		out.flush();

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
