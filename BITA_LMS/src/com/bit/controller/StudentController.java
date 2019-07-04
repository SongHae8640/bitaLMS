package com.bit.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import com.bit.model.UserDto;

@WebServlet("*.stu")
public class StudentController extends HttpServlet {
	protected void doGet(javax.servlet.http.HttpServletRequest req,
			javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {

		RequestDispatcher rd = null;

		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("StudentController :: path = " + path);
		
		//세션 저장
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
		
		try {
			if(userBean.getBelong().equals("student")){
			if (path.equals("/main.stu")) {
				rd = req.getRequestDispatcher("student/main_S.jsp");
			} else if (path.equals("/attendance.stu")) {
				rd = req.getRequestDispatcher("student/attendance_S.jsp");
			} else if (path.equals("/score.stu")) {
				rd = req.getRequestDispatcher("student/score_S.jsp");
			} else if (path.equals("/assignment.stu")) {
				rd = req.getRequestDispatcher("student/assignment_S.jsp");
			} else if (path.equals("/assignmentdetail.stu")) {
				rd = req.getRequestDispatcher("student/assignment_S/assignmentdetail_S.jsp");
			} else if (path.equals("/qna.stu")) {
				rd = req.getRequestDispatcher("student/qna_S.jsp");
			} else if (path.equals("/qnaadd.stu")) {
				rd = req.getRequestDispatcher("student/qna_S/qnaadd_S.jsp");
			} else if (path.equals("/qnadetail.stu")) {
				rd = req.getRequestDispatcher("student/qna_S/qnadetail_S.jsp");
			} else {
				System.out.println("존재하지 않는 페이지");
			}
		}else {
			//teacher나 student페이지로 접근하려고 하면 걍 보내버림
			req.getRequestDispatcher("login.bit");
		}
			rd.forward(req, resp);
		}catch (java.lang.NullPointerException e) {
			resp.sendRedirect("login.bit");
		}

	}
}
