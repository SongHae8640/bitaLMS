package com.bit.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.model.AttendanceDto;
import com.bit.model.TeacherDao;

@WebServlet("*.t")
public class TeacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("teacherController :: path = "+path);
		
		HttpSession session = req.getSession();
		String belong = (String) session.getAttribute("belong");
		int lectureId = (int) session.getAttribute("lectureId");
		RequestDispatcher rd = null;

		try{
			if(belong.equals("teacher")){
				System.out.println("belong.equals 통과, path="+ path);
				if(path.equals("/main.t")){
//					RequestDispatcher rd = req.getRequestDispatcher("teacher/main_T.jsp");
					
					//테스트용으로 다른 주소로 넘김
					TeacherDao dao = new TeacherDao();
					ArrayList<AttendanceDto> todayAttendanceList = dao.getTodayAttendance(lectureId);
					
					req.setAttribute("todayAttendanceList", todayAttendanceList);
					rd = req.getRequestDispatcher("teacher/test_T.jsp");
					
					
				}else if(path.equals("/stumanage.t")){
					rd = req.getRequestDispatcher("studentManage.t");
				}
				rd.forward(req, resp);
			}
		}catch(java.lang.NullPointerException e){
			resp.sendRedirect("login.bit");
		}
		
	}
	
	
	
}
