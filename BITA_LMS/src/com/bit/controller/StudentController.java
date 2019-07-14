package com.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;

import com.bit.model.QnaLDto;
import com.bit.model.StudentDao;
import com.bit.model.UserDto;

@WebServlet("*.stu")
public class StudentController extends HttpServlet {
	protected void doGet(javax.servlet.http.HttpServletRequest req,
			javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {

		RequestDispatcher rd = null;

		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("StudentController(doGet) :: path = " + path);
		
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
		
		try {
			if(userBean.getBelong().equals("student")){
				StudentDao dao = new StudentDao();
				if (path.equals("/main.stu")) {
					String yearMonth = req.getParameter("yearMonthDay");	
					String yearMonthDay = req.getParameter("yearMonthDay");	

					
					//main 
					req.setAttribute("userBean", userBean);
					

					
					rd = req.getRequestDispatcher("student/main_S.jsp");
				} else if (path.equals("/attendance.stu")) {
					//main 좌측 하단 정보 전달
					req.setAttribute("attendanceBean", dao.getAttendance(userBean.getUserId())); 	
					req.setAttribute("userBean", userBean);
					
					//main 우측 하단 정보 전달
					req.setAttribute("attendanceDays", dao.getAttendanceDays(userBean.getUserId()));
					req.setAttribute("totalDays", dao.getTotalDays(userBean.getLectureId()));
					req.setAttribute("attendanceStatusList", dao.getAttendanceStatusList(userBean.getUserId()));
					
					rd = req.getRequestDispatcher("student/attendance_day_S.jsp");
					
				} else if (path.equals("/attendanceMonth.stu")) {
					String yearMonth = req.getParameter("idx");	
					rd = req.getRequestDispatcher("student/attendance_month_S.jsp");	//이거 추가해야함

					
				} else if (path.equals("/score.stu")) {
					req.setAttribute("scoreBean", dao.getScoreBean(userBean.getUserId()));
					rd = req.getRequestDispatcher("student/score_S.jsp");
					
				} else if (path.equals("/assignment.stu")) {
					req.setAttribute("assignmentList", dao.getAssignmentList(userBean.getLectureId()));
					rd = req.getRequestDispatcher("student/assignment_S.jsp");
			
					
				} else if (path.equals("/assignment_detail.stu")) {
					int assignmentId = Integer.parseInt(req.getParameter("idx"));	
					req.setAttribute("assignmentBean", dao.getAssignment(assignmentId));
					System.out.println("dao.getAssignmentBean(assignmentId)");
//					req.setAttribute("submissionBean", dao.getSubmission(assignmentId, userBean.getUserId()));
					System.out.println("dao.getSubmissionBean(assignmentId, userBean.getUserId())");
					rd = req.getRequestDispatcher("student/assignment_S_detail.jsp");
					
				} else if (path.equals("/qna.stu")) {
					req.setAttribute("qnaLList", dao.getQnaList(userBean.getUserId()));
					rd = req.getRequestDispatcher("student/qna_S.jsp");
				} else if (path.equals("/qna_insert.stu")) {
					rd = req.getRequestDispatcher("student/qna_S_add.jsp");
				} else if (path.equals("/qna_detail.stu")) {
					int qnaLId = Integer.parseInt(req.getParameter("idx"));	
					req.setAttribute("qnaLBean", dao.getQnaL(qnaLId));
					rd = req.getRequestDispatcher("student/qna_S_detail.jsp");
				} else if (path.equals("/qna_edit.stu")) {
					int qnaLId = Integer.parseInt(req.getParameter("idx"));	
					req.setAttribute("qnaLBean", dao.getQnaL(qnaLId));
					rd = req.getRequestDispatcher("student/qna_S_edit.jsp");
				}
				
				//ajax 방식 ( rd를 사요하지 않음)
				else if(path.equals("/callAttendanceMonthListJson.stu")) {
					//json으로 보낼때 한글 깨짐 방지
					resp.setContentType("text/html;charset=UTF-8");
					
					// calendar 가져와야 함
					System.out.println();
					String yearMonthDay = "2019-07-10";
					String yearMonth = yearMonthDay.substring(0,7);
					
					JSONArray attendanceMonthListJson = dao.getAttendanceMonthListJson(userBean.getUserId(),yearMonth);
					PrintWriter out = resp.getWriter();
					out.write(attendanceMonthListJson.toJSONString());
					out.close();
				}else {
					System.out.println("존재하지 않는 페이지");

				}
				
				
			}else {
				//teacher나 student페이지로 접근하려고 하면 걍 보내버림
				req.getRequestDispatcher("login.bit");
			}
			if(rd!=null) {
				rd.forward(req, resp);
			}
			
		}catch (java.lang.NullPointerException e) {
			resp.sendRedirect("login.bit");
		}

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = null;

		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("StudentController(doPost) :: path = " + path);
		
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
					rd = req.getRequestDispatcher("assignmentdetail.stu");	
				}else if(path.equals("/submission_update.stu")){
					String assignmentId = req.getParameter("idx");	
					String fileName = req.getParameter("fileName");	//수정페이지에서 가져올 것
					result = dao.updateSubmission(assignmentId, userBean.getUserId() , fileName);		
					rd = req.getRequestDispatcher("assignmentdetail.stu");	
				}else if(path.equals("/submission_delete.stu")){
					int assignmentId = Integer.parseInt(req.getParameter("idx"));	
					result = dao.deleteSubmission(assignmentId, userBean.getUserId());		
					rd = req.getRequestDispatcher("assignmentdetail.stu");	
				}else if(path.equals("/qna_insert.stu")){
					QnaLDto qnaLBean = new QnaLDto();
					qnaLBean.setTitle(req.getParameter("title"));
					qnaLBean.setType(req.getParameter("type"));
					qnaLBean.setQuestionContent(req.getParameter("questionContent"));
					qnaLBean.setStuId(userBean.getUserId());					
					result = dao.insertQnaL(qnaLBean);		
					
					///result 값에 따라 write 값 변경 해야함
					PrintWriter out = resp.getWriter();
					out.write("OK");
					out.close();	
				}else if(path.equals("/qna_update.stu")){
					//json으로 보낼때 한글 깨짐 방지
					resp.setContentType("text/html;charset=UTF-8");
					QnaLDto bean = new QnaLDto();
					bean.setQnaLId(Integer.parseInt(req.getParameter("qnaLId")));
					bean.setTitle(req.getParameter("title"));
					bean.setQuestionContent(req.getParameter("questionContent"));
					result = dao.updateQnaL(bean);	
					
					
					///result 값에 따라 write 값 변경 해야함
					PrintWriter out = resp.getWriter();
					out.write("OK");
					out.close();
				}else {
					System.out.println("존재하지 않는 페이지");
				}
			}else {
				
				req.getRequestDispatcher("login.bit");
			}
			if(rd!=null) {
				rd.forward(req, resp);
			}
			
		}catch (java.lang.NullPointerException e) {
			resp.sendRedirect("login.bit");
		}
	}
}
