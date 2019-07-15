package com.bit.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
		System.out.println("StudentController :: path(doGet) = " + path);
		
		//�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
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
					

					//main �슦痢� �븯�떒 �젙蹂� �쟾�떖

					
					rd = req.getRequestDispatcher("student/main_S.jsp");
				} else if (path.equals("/attendance.stu")) {
					//�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
					req.setAttribute("attendanceBean", dao.getAttendance(userBean.getUserId())); 	
					req.setAttribute("userBean", userBean);
					
					//main �뜝�룞�삕�뜝�룞�삕 �뜝�떦�뙋�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
					req.setAttribute("attendanceDays", dao.getAttendanceDays(userBean.getUserId()));
					req.setAttribute("totalDays", dao.getTotalDays(userBean.getLectureId()));
					req.setAttribute("attendanceStatusList", dao.getAttendanceStatusList(userBean.getUserId()));
					
					rd = req.getRequestDispatcher("student/attendance_day_S.jsp");
					
				} else if (path.equals("/attendanceMonth.stu")) {
					String yearMonth = req.getParameter("idx");	
					req.setAttribute("attendanceMonthList", dao.getAttendanceMonthList(userBean.getUserId(), yearMonth));

					rd = req.getRequestDispatcher("student/attendance_month_S.jsp");	//�씠嫄� 異붽��빐�빞�븿

					
				} else if (path.equals("/score.stu")) {//scoreBean에 점수를 담아 view로 보냄
					req.setAttribute("scoreBean", dao.getScoreBean(userBean.getUserId()));
					rd = req.getRequestDispatcher("student/score_S.jsp");
					
				} else if (path.equals("/assignment.stu")) {
					req.setAttribute("assignmentList", dao.getAssignmentList(userBean.getLectureId()));
					rd = req.getRequestDispatcher("student/assignment_S.jsp");
			
					
				} else if (path.equals("/assignment_detail.stu")) {
					int assignmentId = Integer.parseInt(req.getParameter("idx"));	//�뜝�룞�삕�뜝�떕占쎌삕易뚢댙�삕�뜝 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�샇�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕
					req.setAttribute("assignmentBean", dao.getAssignment(assignmentId));
					System.out.println("dao.getAssignmentBean(assignmentId)");
					/*
					 * req.setAttribute("submissionBean", dao.getSubmission(assignmentId,
					 * userBean.getUserId())); //dto혞혱占승�
					 */					System.out.println("dao.getSubmissionBean(assignmentId, userBean.getUserId())");
					rd = req.getRequestDispatcher("student/assignment_S_detail.jsp");
				} else if (path.equals("/qna.stu")) {
					req.setAttribute("qnaList", dao.getQnaList(userBean.getUserId()));
					rd = req.getRequestDispatcher("student/qna_S.jsp");
				} else if (path.equals("/qna_insert.stu")) {
					req.setAttribute("name",userBean.getName());
					rd = req.getRequestDispatcher("student/qna_S_insert.jsp");
				}else if (path.equals("/qna_detail.stu")) {

					String qnaId = req.getParameter("idx");	//紐⑸줉�솕硫댁뿉�꽌 怨쇱젣 踰덊샇瑜� 媛��졇�삱 寃�
					req.setAttribute("qnaBean", dao.getQna(qnaId));
					rd = req.getRequestDispatcher("student/qna_S_detail.jsp");
				}else {
					System.out.println("議댁옱�븯吏� �븡�뒗 �럹�씠吏�");

				}
			}else {
				//teacher�뜝�룞�삕 student�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦琉꾩삕�뜝�룞�삕 �뜝�떦紐뚯삕 �뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕
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
		System.out.println("StudentController :: path(doPost) = " + path);
		
		//�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
		
		//insert, edit, delete �뜝�룞�삕 �뜝�룞�삕�뜝 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦�뙋�삕 result
		///�뜝�룜�뼸�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
		int result;
		
		try {
			if(userBean.getBelong().equals("student")){
				StudentDao dao = new StudentDao();
				if (path.equals("/main.stu")) {
					
				}else if(path.equals("/submission_insert.stu")){
					int assignmentId = Integer.parseInt(req.getParameter("idx"));	
					result = dao.insertSubmission(assignmentId, userBean.getUserId());		
					rd = req.getRequestDispatcher("assignmentdetail.stu");	//�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �솕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�떛�벝�삕, //�뜝�룞�삕�뜝�룞�삕 rd�뜝�룞�삕 �뜝�떛�벝�삕�뜝�뙏�뼲�삕�뜝�떦�냲�삕?
				}else if(path.equals("/submission_update.stu")){
					String assignmentId = req.getParameter("idx");	
					String fileName = req.getParameter("fileName");	//�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕
					result = dao.updateSubmission(assignmentId, userBean.getUserId() , fileName);		
					rd = req.getRequestDispatcher("assignmentdetail.stu");	//�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �솕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�떛�벝�삕, //�뜝�룞�삕�뜝�룞�삕 rd�뜝�룞�삕 �뜝�떛�벝�삕�뜝�뙏�뼲�삕�뜝�떦�냲�삕?
				}else if(path.equals("/submission_delete.stu")){
					int assignmentId = Integer.parseInt(req.getParameter("idx"));	
					result = dao.deleteSubmission(assignmentId, userBean.getUserId());		
					rd = req.getRequestDispatcher("assignmentdetail.stu");	//�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �솕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�떛�벝�삕, //�뜝�룞�삕�뜝�룞�삕 rd�뜝�룞�삕 �뜝�떛�벝�삕�뜝�뙏�뼲�삕�뜝�떦�냲�삕?
				}else if(path.equals("/qna_insert.stu")){
					System.out.println("이의제기 dopost");
					req.setCharacterEncoding("UTF-8");
					resp.setContentType("text/html;charset=UTF-8");
					QnaLDto qnaLBean = new QnaLDto();
					qnaLBean.setTitle(req.getParameter("title"));
					qnaLBean.setType(req.getParameter("type"));
					qnaLBean.setQuestionContent(req.getParameter("questionContent"));
					qnaLBean.setStuId(userBean.getUserId());					
					result = dao.insertQnaL(qnaLBean);
					if(result==1) {
						System.out.println("문의등록성공");
						rd = req.getRequestDispatcher("student/qna_S.jsp");	//�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �솕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�떛�벝�삕, //�뜝�룞�삕�뜝�룞�삕 rd�뜝�룞�삕 �뜝�떛�벝�삕�뜝�뙏�뼲�삕�뜝�떦�냲�삕?
					}else {
						System.out.println("문의등록실패");
					}
					}else if(path.equals("/qan_update.stu")){
					int qnaId = Integer.parseInt(req.getParameter("qnaId"));
					String title = req.getParameter("title");
					String type = req.getParameter("type");	///�뜝�떛寃껊벝�삕 �뜝�룞�삕�뜝�룞�삕�뜝�뙎�눦�삕 �뜝�뙇�냲�삕??
					String questionContent = req.getParameter("questionContent");
					result = dao.updateQnaL(qnaId ,title,type, questionContent);		
					rd = req.getRequestDispatcher("assignmentdetail.stu");	//�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �솕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�떛�벝�삕, //�뜝�룞�삕�뜝�룞�삕 rd�뜝�룞�삕 �뜝�떛�벝�삕�뜝�뙏�뼲�삕�뜝�떦�냲�삕?
				}else if(path.equals("/qan_delete.stu")){
					String[] qnaId = req.getParameterValues("qnaId");
					
					result = dao.deleteQnaL(qnaId);		
					rd = req.getRequestDispatcher("qna.stu");	//qna �뜝�룞�삕�뜝 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�떛�벝�삕�뜝�떛�벝�삕�뜝�뙏�뼲�삕�뜝�떦�냲�삕?
				}
			}else {
				//teacher�뜝�룞�삕 student�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦琉꾩삕�뜝�룞�삕 �뜝�떦紐뚯삕 �뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕
				req.getRequestDispatcher("login.bit");
			}
			rd.forward(req, resp);
		}catch (java.lang.NullPointerException e) {
			resp.sendRedirect("login.bit");
		}
	}
}
