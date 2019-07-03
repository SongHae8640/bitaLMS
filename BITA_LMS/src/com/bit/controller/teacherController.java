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
import com.bit.model.UserDto;

@WebServlet("*.tea")
public class TeacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		//들어오는 주소 확인하고 뒷주소만 저장하기
		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("teacherController :: path = " + path);

		//세션 저장
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
		
		//중복되는 RequestDispatcher
		RequestDispatcher rd = null;

		try {
			if (userBean.getBelong().equals("teacher")) {

				TeacherDao dao = new TeacherDao();
				if (path.equals("/main.tea")) {
					rd = req.getRequestDispatcher("teacher/main_T.jsp");
				} else if (path.equals("/attendance.tea")) {
//					 ArrayList<AttendanceDto> todayAttendanceList = dao
//					 .getTodayAttendance(userBean.getLecture_id());
//					
//					 req.setAttribute("todayAttendanceList",
//					 todayAttendanceList);
					rd = req.getRequestDispatcher("teacher/attendance_T.jsp");
				} else if (path.equals("/score.tea")) {
					rd = req.getRequestDispatcher("teacher/score_T.jsp");
				} else if (path.equals("/assignment.tea")) {
					rd = req.getRequestDispatcher("teacher/assignment_T.jsp");
				} else if (path.equals("/qna.tea")) {
					rd = req.getRequestDispatcher("teacher/qna_T.jsp");
				} else {
					System.out.println("오류");
				}
			}
			rd.forward(req, resp);
		} catch (java.lang.NullPointerException e) {
			resp.sendRedirect("login.bit");
		}

	}

}
