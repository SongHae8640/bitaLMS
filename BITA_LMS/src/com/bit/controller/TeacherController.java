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

import com.bit.model.AssignmentDto;
import com.bit.model.AttendanceDto;
import com.bit.model.QnaLDto;
import com.bit.model.ScoreDto;
import com.bit.model.SubmsissionDto;
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
		System.out.println("teacherController(doGet) :: path = " + path);

		//세션 저장
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
		
		//중복되는 RequestDispatcher
		RequestDispatcher rd = null;

		try {
			if (userBean.getBelong().equals("teacher")) {

				TeacherDao dao = new TeacherDao();
				///lecture_id 는 자주 써서 변수로 하나 빼는게 좋을 것 같음
				
				if (path.equals("/main.tea")) {
					String yearMonth = req.getParameter("idx");	///달력의 월 이동을 할때 idx로 년월을 받아 올것
					req.setAttribute("calendarList",dao.getCalendarList(userBean.getLecture_id(), yearMonth));
					
					//main 좌측하단 정보 전달
					///bean을 통째 보내는게 나으려나? 일단 이렇게 하겠슴.
					req.setAttribute("name", userBean.getName());
					req.setAttribute("lectureName", userBean.getLectureName());
					req.setAttribute("startDate", userBean.getStartDate());
					req.setAttribute("endDate", userBean.getEndDate());
					
					//main 우측 하단 정보 전달
					req.setAttribute("numStu", dao.getNumStu(userBean.getLecture_id()));
					req.setAttribute("checkinNum", dao.getCheckinNum(userBean.getLecture_id()));
					req.setAttribute("assignmentNum", dao.getSubmissionList(userBean.getLecture_id()));
					req.setAttribute("totalDays", dao.getTotalDays(userBean.getLecture_id()));
					req.setAttribute("progressDays", dao.getProgressDays(userBean.getLecture_id()));
					
					rd = req.getRequestDispatcher("teacher/main_T.jsp");

				}else if (path.equals("/attendance.tea")) {
					 ArrayList<AttendanceDto> todayAttendanceList = dao.getTodayAttendance(userBean.getLecture_id());
					 //어트리뷰트로 저장하고 jsp페이지에서 get으로 불러오기
					 req.setAttribute("todayAttendanceList",todayAttendanceList);
					 rd = req.getRequestDispatcher("teacher/attendance_T.jsp");

				}else if (path.equals("/score.tea")) {
					ArrayList<ScoreDto> scoreList = dao.getScoreList(userBean.getLecture_id());
					req.setAttribute("scoreList",scoreList);
					rd = req.getRequestDispatcher("teacher/score_T.jsp");
					
				} else if (path.equals("/assignment.tea")) {
					ArrayList<AssignmentDto> assignmentList = dao.getAssignmentList(userBean.getLecture_id());
					req.setAttribute("assignmentList", assignmentList);
					rd = req.getRequestDispatcher("teacher/assignment_T.jsp");
					
				}else if (path.equals("/assignment_detail.tea")) {
					int assignmentId = Integer.parseInt(req.getParameter("idx"));	//글 리스트(또는 edit에서)에서 idx로 assignmentId를 받아와서 사용(rownum)아님
					AssignmentDto AssignmentBean = dao.getAssignmentDetail(assignmentId);
					req.setAttribute("AssignmentBean", AssignmentBean);
					ArrayList<SubmsissionDto> submissionList = dao.getSubmissionList(assignmentId); 
					req.setAttribute("submissionList", submissionList);
					rd = req.getRequestDispatcher("teacher/assignment_T_deatil.jsp");
				
				}else if (path.equals("/qna.tea")) {
					ArrayList<QnaLDto> qnaLList = dao.getQnaLList(userBean.getUserId());
					req.setAttribute("qnaLList", qnaLList);
					rd = req.getRequestDispatcher("teacher/qna_T.jsp");
				
				}else if (path.equals("/qna_detail.tea")) {
					int assignmentId = Integer.parseInt(req.getParameter("idx"));	//글 리스트(또는 edit에서)에서 idx로 assignmentId를 받아와서 사용(rownum)아님
					QnaLDto QnaLBean = dao.QnaLDetail(assignmentId);
					req.setAttribute("QnaLBean", QnaLBean);
					rd = req.getRequestDispatcher("teacher/qna_T_deatil.jsp");
				
				}else {
					System.out.println("존재하지 않는 페이지");
				}
			}else {
				//teacher나 student페이지로 접근하려고 하면 걍 보내버림
				req.getRequestDispatcher("login.bit");
			}
			rd.forward(req, resp);
		} catch (java.lang.NullPointerException e) {
			resp.sendRedirect("login.bit");
		}

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		//들어오는 주소 확인하고 뒷주소만 저장하기
		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("teacherController(doPost) :: path = " + path);

		//세션 저장
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
		
		//중복되는 RequestDispatcher
		RequestDispatcher rd = null;
		
		//insert, edit, delete 의 결과 내용을 저장하는 result
		///어떻게 사용할지 아직 미정
		int result;

		try {
			if (userBean.getBelong().equals("teacher")) {

				TeacherDao dao = new TeacherDao();
				if(path.equals("")){
					
				}else if(path.equals("/attendance_checkin.tea")){
					String stdId = (String)req.getParameter("idx");
					result = dao.insertAttendanceCheckin(stdId);
					rd = req.getRequestDispatcher("/attendance.tea");	//다시 today 출석 리스트로 이동
					
				}else if(path.equals("/attendance_checkout.tea")){
					String stdId = (String)req.getParameter("idx");
					result = dao.updateAttendanceCheckout(stdId);
					rd = req.getRequestDispatcher("/attendance.tea");	//다시 today 출석 리스트로 이동
					
				}else if (path.equals("/assignment_insert.tea")) {//assignment insert에서 post방식으로 넘겼을때
					String title = req.getParameter("title");
					String content = req.getParameter("content");
					result = dao.insertAssignment(title, content,userBean.getLecture_id());	///esult를 어떻게 사용할지 나중에 생각
					rd = req.getRequestDispatcher("/assignment.tea");	//리스트 페이지로
					
				} else if (path.equals("/assignment_edit.tea")) {
					String title = req.getParameter("title");
					String content = req.getParameter("content");
					String assingmentId = req.getParameter("assignmentId");
					result = dao.editAssignment(title,content,assingmentId);
					rd = req.getRequestDispatcher("/assignment_detail.tea?idx="+assingmentId);	///수정한 페이지로
				} else if (path.equals("/assignment_delete.tea")) {
					int assignmentId = Integer.parseInt(req.getParameter("idx"));
					result = dao.getAssignmentDelete(assignmentId);
					rd = req.getRequestDispatcher("teacher/assignment_T_.jsp");
				
				}else if (path.equals("/qnaAnswer_insert.tea")) {//qna에서 답변을 입력 또는 수정할때 
					String answerContent = req.getParameter("answerContent");
					String questionId = req.getParameter("questionId");
					result = dao.insertQnaLAnswer(answerContent,questionId);
					rd = req.getRequestDispatcher("/qna_detail.tea?idx="+questionId);	//리스트 페이지로
				}else if(path.equals("/calendar_insert.tea")){
					String startDate = req.getParameter("startDate");
					String endDate = req.getParameter("endDate");
					String title = req.getParameter("title");
					String content = req.getParameter("content");
					result = dao.insertCalendar(startDate, endDate, title, content, userBean.getLecture_id());
				}
				else {
					System.out.println("존재하지 않는 페이지");
				}
			}else {
				//teacher나 student페이지로 접근하려고 하면 걍 보내버림
				req.getRequestDispatcher("login.bit");
			}
			rd.forward(req, resp);
		} catch (java.lang.NullPointerException e) {
			resp.sendRedirect("login.bit");
		}
	}

}