package com.bit.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("*.stu")
public class StudentController extends HttpServlet {
	protected void doGet(javax.servlet.http.HttpServletRequest req,
			javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {

		RequestDispatcher rd = null;

		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("teacherController :: path = " + path);
		try {
			if (path.equals("/main.stu")) {
				rd = req.getRequestDispatcher("student/main_S.jsp");
				rd.forward(req, resp);
			} else if (path.equals("/attendance.stu")) {
				rd = req.getRequestDispatcher("student/attendance_S.jsp");
				rd.forward(req, resp);
			} else if (path.equals("/score.stu")) {
				rd = req.getRequestDispatcher("student/score_S.jsp");
				rd.forward(req, resp);
			} else if (path.equals("/assignment.stu")) {
				rd = req.getRequestDispatcher("student/assignment_S.jsp");
				rd.forward(req, resp);
			} else if (path.equals("/qna.stu")) {
				rd = req.getRequestDispatcher("student/qna_S.jsp");
				rd.forward(req, resp);
			} else {
				System.out.println("¿À·ù");
			}
		} catch (java.lang.NullPointerException e) {
			resp.sendRedirect("login.bit");
		}

	}
}
