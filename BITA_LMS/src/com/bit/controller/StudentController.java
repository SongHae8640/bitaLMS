package com.bit.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("*.stu")
public class StudentController extends HttpServlet {
	protected void doGet(javax.servlet.http.HttpServletRequest req,
			javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {

		RequestDispatcher rd = null;

		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("StudentController :: path = " + path);
		
		//占쏙옙占쏙옙 占쏙옙占쏙옙
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
		System.out.println("userid="+userBean.toString());
		try {
			if(userBean.getBelong().equals("student")){
				StudentDao dao = new StudentDao();
				if (path.equals("/main.stu")) {
					String yearMonth = req.getParameter("yearMonthDay");	
					String yearMonthDay = req.getParameter("yearMonthDay");	

					
					//main 
					req.setAttribute("userBean", userBean);
					

					//main 우측 하단 정보 전달

					
					rd = req.getRequestDispatcher("student/main_S.jsp");
				} else if (path.equals("/attendance.stu")) {
					//占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙
					req.setAttribute("attendanceBean", dao.getAttendance(userBean.getUserId())); 	
					req.setAttribute("userBean", userBean);
					
					//main 占쏙옙占쏙옙 占싹댐옙 占쏙옙占쏙옙 占쏙옙占쏙옙
					req.setAttribute("attendanceDays", dao.getAttendanceDays(userBean.getUserId()));
					req.setAttribute("totalDays", dao.getTotalDays(userBean.getLectureId()));
					req.setAttribute("attendanceStatusList", dao.getAttendanceStatusList(userBean.getUserId()));
					
					rd = req.getRequestDispatcher("student/attendance_day_S.jsp");
					
				} else if (path.equals("/attendanceMonth.stu")) {
					String yearMonth = req.getParameter("idx");	
					req.setAttribute("attendanceMonthList", dao.getAttendanceMonthList(userBean.getUserId(), yearMonth));

					rd = req.getRequestDispatcher("student/attendance_month_S.jsp");	//이거 추가해야함

					
				} else if (path.equals("/score.stu")) {
					req.setAttribute("scoreBean", dao.getScoreBean(userBean.getUserId()));
					rd = req.getRequestDispatcher("student/score_S.jsp");
					
				} else if (path.equals("/assignment.stu")) {
					req.setAttribute("assignmentList", dao.getAssignmentList(userBean.getLectureId()));
					rd = req.getRequestDispatcher("student/assignment_S.jsp");
			
					
				} else if (path.equals("/assignment_detail.stu")) {
					int assignmentId = Integer.parseInt(req.getParameter("idx"));	//疫뀐옙 �뵳�딅뮞占쎈뱜(占쎌굢占쎈뮉 edit占쎈퓠占쎄퐣)占쎈퓠占쎄퐣 idx嚥∽옙 assignmentId�몴占� 獄쏆룇釉섓옙占쏙옙苑� 占쎄텢占쎌뒠(rownum)占쎈툡占쎈뻷
					String userId=userBean.getUserId();
					System.out.println("hello="+userId);
//					req.setAttribute("attachedBean", dao.getAttachedBean(AttachedFileDto));
					req.setAttribute("assignmentBean", dao.getAssignment(assignmentId));
					req.setAttribute("submissionList", dao.getSubmissionList(assignmentId ,userId));
					rd = req.getRequestDispatcher("student/assignment_S_detail.jsp");
					
				} else if (path.equals("/qna.stu")) {
					req.setAttribute("qnaList", dao.getQnaList(userBean.getUserId()));
					rd = req.getRequestDispatcher("student/qna_S.jsp");
				} else if (path.equals("/qna_detail.stu")) {

					String qnaId = req.getParameter("idx");	//목록화면에서 과제 번호를 가져올 것
					req.setAttribute("qnaBean", dao.getQna(qnaId));
					rd = req.getRequestDispatcher("student/qna_S/qnadetail_S.jsp");
				}else {
					System.out.println("존재하지 않는 페이지");

				}
			}else {
				//teacher占쏙옙 student占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싹뤄옙占쏙옙 占싹몌옙 占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙
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
		
		//占쏙옙占쏙옙 占쏙옙占쏙옙
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
		
		//insert, edit, delete 占쏙옙 占쏙옙占 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싹댐옙 result
		///占쏘떻占쏙옙 占쏙옙占쏙옙占쏙옙占 占쏙옙占쏙옙 占쏙옙占쏙옙
		int result;
		
		try {
			if(userBean.getBelong().equals("student")){
				StudentDao dao = new StudentDao();
				if (path.equals("/main.stu")) {
					
				}else if(path.equals("/submission_insert.stu")){
					int assignmentId = Integer.parseInt(req.getParameter("idx"));
					SubmissionDto bean_s=new SubmissionDto();
					AttachedFileDto fileBean=new AttachedFileDto();
					bean_s.setAssignmentId(Integer.parseInt(req.getParameter("assignment_id")));
					result = dao.insertSubmission(userBean, bean_s, fileBean);		
					rd = req.getRequestDispatcher("assignmentdetail.stu");	//占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 화占쏙옙占쏙옙占쏙옙 占싱듸옙, //占쏙옙占쏙옙 rd占쏙옙 占싱듸옙占쌔억옙占싹놂옙?
				}else if(path.equals("/submission_update.stu")){
					String assignmentId = req.getParameter("idx");	
					String fileName = req.getParameter("fileName");	//占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙
					result = dao.updateSubmission(assignmentId, userBean.getUserId() , fileName);		
					rd = req.getRequestDispatcher("assignmentdetail.stu");	//占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 화占쏙옙占쏙옙占쏙옙 占싱듸옙, //占쏙옙占쏙옙 rd占쏙옙 占싱듸옙占쌔억옙占싹놂옙?
				}else if(path.equals("/submission_delete.stu")){
					int assignmentId = Integer.parseInt(req.getParameter("idx"));	
					result = dao.deleteSubmission(assignmentId, userBean.getUserId());		
					rd = req.getRequestDispatcher("assignmentdetail.stu");	//占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 화占쏙옙占쏙옙占쏙옙 占싱듸옙, //占쏙옙占쏙옙 rd占쏙옙 占싱듸옙占쌔억옙占싹놂옙?
				}else if(path.equals("/qan_insert.stu")){
					QnaLDto qnaLBean = new QnaLDto();
					qnaLBean.setTitle(req.getParameter("title"));
					qnaLBean.setType(req.getParameter("type"));
					qnaLBean.setQuestionContent(req.getParameter("questionContent"));
					qnaLBean.setStuId(userBean.getUserId());					
					result = dao.insertQnaL(qnaLBean);		
					rd = req.getRequestDispatcher("assignmentdetail.stu");	//占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 화占쏙옙占쏙옙占쏙옙 占싱듸옙, //占쏙옙占쏙옙 rd占쏙옙 占싱듸옙占쌔억옙占싹놂옙?
				}else if(path.equals("/qan_update.stu")){
					int qnaId = Integer.parseInt(req.getParameter("qnaId"));
					String title = req.getParameter("title");
					String type = req.getParameter("type");	///占싱것듸옙 占쏙옙占쏙옙占쌓쇽옙 占쌍놂옙??
					String questionContent = req.getParameter("questionContent");
					result = dao.updateQnaL(qnaId ,title,type, questionContent);		
					rd = req.getRequestDispatcher("assignmentdetail.stu");	//占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 화占쏙옙占쏙옙占쏙옙 占싱듸옙, //占쏙옙占쏙옙 rd占쏙옙 占싱듸옙占쌔억옙占싹놂옙?
				}else if(path.equals("/qan_delete.stu")){
					String[] qnaId = req.getParameterValues("qnaId");
					
					result = dao.deleteQnaL(qnaId);		
					rd = req.getRequestDispatcher("qna.stu");	//qna 占쏙옙占 占쏙옙占쏙옙占쏙옙占쏙옙 占싱듸옙占싱듸옙占쌔억옙占싹놂옙?
				}else {
					System.out.println("占쏙옙占쏙옙占쏙옙占쏙옙 占십댐옙 占쏙옙占쏙옙占쏙옙");
				}
			}else {
				//teacher占쏙옙 student占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싹뤄옙占쏙옙 占싹몌옙 占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙
				req.getRequestDispatcher("login.bit");
			}
			rd.forward(req, resp);
		}catch (java.lang.NullPointerException e) {
			resp.sendRedirect("login.bit");
		}
	}
}
