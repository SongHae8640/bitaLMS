package com.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.model.StudentDao;
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
				StudentDao dao = new StudentDao();
				if (path.equals("/main.stu")) {
					String yearMonth = req.getParameter("yearMonthDay");	///달력의 월 이동을 할때 idx로 년월을 받아 올것
					String yearMonthDay = req.getParameter("yearMonthDay");	///달력의 월 이동을 할때 idx로 년월을 받아 올것
					req.setAttribute("calendarMonthList",dao.getCalendarMonthList(userBean.getLecture_id(), yearMonth));
					req.setAttribute("calendarDayList",dao.getCalendarMonthList(userBean.getLecture_id(), yearMonthDay));
					
					//main 좌측하단 정보 전달
					req.setAttribute("userBean", userBean);
					
					//main 우측 하단 정보 전달
					req.setAttribute("attendanceDays", dao.getAttendanceDays(userBean.getUserId()));
					req.setAttribute("qnaNum", dao.getQnaNum(userBean.getUserId()));
					///qna에서 new는 답변이 왔으나 자신이 확인하지 않은 수를 말하는 것인가??? 후
					req.setAttribute("totalDays", dao.getTotalDays(userBean.getLecture_id()));
					req.setAttribute("progressDays", dao.getProgressDays(userBean.getLecture_id()));
					
					rd = req.getRequestDispatcher("student/main_S.jsp");
				} else if (path.equals("/attendance.stu")) {
					//좌측 정보 전달
					req.setAttribute("attendanceBean", dao.getAttendance(userBean.getUserId())); 	//bean안에 상태, 입실 퇴실 정보, 시간이 있다
					req.setAttribute("userBean", userBean);
					
					//main 우측 하단 정보 전달
					req.setAttribute("attendanceDays", dao.getAttendanceDays(userBean.getUserId()));
					req.setAttribute("totalDays", dao.getTotalDays(userBean.getLecture_id()));
					req.setAttribute("attendanceStatusList", dao.getAttendanceStatusList(userBean.getUserId()));
					
					rd = req.getRequestDispatcher("student/attendance_S.jsp");
					
				} else if (path.equals("/attendanceMonth.stu")) {
					String yearMonth = req.getParameter("idx");	///달력의 월 이동을 할때 idx로 년월을 받아 올것
					req.setAttribute("attendanceMonthList", dao.getAttendanceMonthList(userBean.getUserId(), yearMonth));
					rd = req.getRequestDispatcher("student/attendance_S_month.jsp");	//이거 추가해야함
					
				} else if (path.equals("/score.stu")) {
					req.setAttribute("scoreBean", dao.getScoreBean(userBean.getUserId()));
					rd = req.getRequestDispatcher("student/score_S.jsp");
					
				} else if (path.equals("/assignment.stu")) {
					req.setAttribute("assignmentList", dao.getAssignmentList(userBean.getLecture_id()));
					rd = req.getRequestDispatcher("student/assignment_S.jsp");
					
				} else if (path.equals("/assignmentdetail.stu")) {
					String assignmentId = req.getParameter("idx");	//목록화면에서 과제 번호를 가져올 것
					req.setAttribute("assignmentBean", dao.getAssignmentBean(assignmentId));
					req.setAttribute("submissionBean", dao.getSubmissionBean(assignmentId, userBean.getUserId()));
					
					rd = req.getRequestDispatcher("student/assignment_S/assignmentdetail_S.jsp");
					
				} else if (path.equals("/qna.stu")) {
					req.setAttribute("qnaList", dao.getQnaList(userBean.getUserId()));
					rd = req.getRequestDispatcher("student/qna_S.jsp");
				} else if (path.equals("/qna_detail.stu")) {
					String qnaId = req.getParameter("idx");	//목록화면에서 과제 번호를 가져올 것
					req.setAttribute("qnaBean", dao.getQnaBean(qnaId));
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
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = null;

		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("StudentController :: path = " + path);
		
		//세션 저장
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
		
		//insert, edit, delete 의 결과 내용을 저장하는 result
		///어떻게 사용할지 아직 미정
		int result;
		
		try {
			if(userBean.getBelong().equals("student")){
				StudentDao dao = new StudentDao();
				if (path.equals("/main.stu")) {
					
				}else if(path.equals("/submission_insert.stu")){
					int assignmentId = Integer.parseInt(req.getParameter("idx"));	
					result = dao.insertSubmission(assignmentId, userBean.getUserId());		
					rd = req.getRequestDispatcher("assignmentdetail.stu");	//과제 디테일 화면으로 이동, //굳이 rd로 이동해야하나?
				}else if(path.equals("/submission_update.stu")){
					String assignmentId = req.getParameter("idx");	
					String fileName = req.getParameter("fileName");	//수정페이지에서 가져올 것
					result = dao.updateSubmission(assignmentId, userBean.getUserId() , fileName);		
					rd = req.getRequestDispatcher("assignmentdetail.stu");	//과제 디테일 화면으로 이동, //굳이 rd로 이동해야하나?
				}else if(path.equals("/submission_delete.stu")){
					int assignmentId = Integer.parseInt(req.getParameter("idx"));	
					result = dao.deleteSubmission(assignmentId, userBean.getUserId());		
					rd = req.getRequestDispatcher("assignmentdetail.stu");	//과제 디테일 화면으로 이동, //굳이 rd로 이동해야하나?
				}else if(path.equals("/qan_insert.stu")){
					String title = req.getParameter("title");
					String type = req.getParameter("type");
					String questionContent = req.getParameter("questionContent");
					result = dao.insertQnaL(userBean.getUserId(),title,type, questionContent);		
					rd = req.getRequestDispatcher("assignmentdetail.stu");	//과제 디테일 화면으로 이동, //굳이 rd로 이동해야하나?
				}else if(path.equals("/qan_update.stu")){
					int qnaId = Integer.parseInt(req.getParameter("qnaId"));
					String title = req.getParameter("title");
					String type = req.getParameter("type");	///이것도 수정항수 있나??
					String questionContent = req.getParameter("questionContent");
					result = dao.updateQnaL(qnaId ,title,type, questionContent);		
					rd = req.getRequestDispatcher("assignmentdetail.stu");	//과제 디테일 화면으로 이동, //굳이 rd로 이동해야하나?
				}else if(path.equals("/qan_delete.stu")){
					String[] qnaId = req.getParameterValues("qnaId");
					
					result = dao.deleteQnaL(qnaId);		
					rd = req.getRequestDispatcher("qna.stu");	//qna 목록 페이지로 이동이동해야하나?
				}else {
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
