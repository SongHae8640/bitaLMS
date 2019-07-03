package com.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.model.AttendanceDto;
import com.bit.model.ScoreDto;
import com.bit.model.TeacherDao;
import com.bit.model.UserDto;

@WebServlet("*.tea")
public class TeacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		//?€?΄?€? μ£Όμ ??Έ?κ³? ?·μ£Όμλ§? ???₯?κΈ?
		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("teacherController :: path = " + path);

		//?Έ? ???₯
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
		
		//μ€λ³΅?? RequestDispatcher
		RequestDispatcher rd = null;

		try {
			if (userBean.getBelong().equals("teacher")) {

				TeacherDao dao = new TeacherDao();
				if (path.equals("/main.tea")) {
					rd = req.getRequestDispatcher("teacher/main_T.jsp");
				} else if (path.equals("/attendance.tea")) {
					 ArrayList<AttendanceDto> todayAttendanceList = dao.getTodayAttendance(userBean.getLecture_id());
					 //?΄?Έλ¦¬λ·°?Έλ‘? ???₯?κ³? jsp??΄μ§??? get?Όλ‘? λΆλ¬?€κΈ?
					 req.setAttribute("todayAttendanceList",todayAttendanceList);
					 rd = req.getRequestDispatcher("teacher/attendance_T.jsp");

				} else if (path.equals("/score.tea")) {
					
					ArrayList<ScoreDto> scoreList = dao.getScore(userBean.getLecture_id());
					req.setAttribute("scoreList",scoreList);
					rd = req.getRequestDispatcher("teacher/score_T.jsp");
					
				} else if (path.equals("/assignment.tea")) {
					
					rd = req.getRequestDispatcher("teacher/assignment_T.jsp");
					
				} else if (path.equals("/qna.tea")) {
					
					rd = req.getRequestDispatcher("teacher/qna_T.jsp");
				
				} else {
					System.out.println("μ‘΄μ¬?μ§? ?? ??΄μ§?");
				}
			}else {
				//teacher? student??΄μ§?λ‘? ? κ·Όν? €κ³? ?λ©? κ±? λ³΄λ΄λ²λ¦Ό
				req.getRequestDispatcher("login.bit");
			}
			rd.forward(req, resp);
		} catch (java.lang.NullPointerException e) {
			resp.sendRedirect("login.bit");
		}

	}

}
