package com.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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

		//�뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�뙇�눦�삕 �솗�뜝�룞�삕�뜝�떦怨ㅼ삕 �뜝�룞�삕�뜝�뙇�냼紐뚯삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦源띿삕
		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("teacherController(doGet) :: path = " + path);

		//�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
		
		//�뜝�뙥釉앹삕�뜝�떎�뙋�삕 RequestDispatcher
		RequestDispatcher rd = null;

		try {
			if (userBean.getBelong().equals("teacher")) {

				TeacherDao dao = new TeacherDao();
				///lecture_id 占쎈뮉 占쎌쁽雅뚳옙 占쎈쑅占쎄퐣 癰귨옙占쎈땾嚥∽옙 占쎈릭占쎄돌 �뜮�눖�뮉野껓옙 �넫�뿭�뱽 野껓옙 揶쏆늿�벉
				
				
				if (path.equals("/main.tea")) {
					String yearMonth = req.getParameter("yearMonth");	///占쎈뼎占쎌젾占쎌벥 占쎌뜞 占쎌뵠占쎈짗占쎌뱽 占쎈막占쎈르 idx嚥∽옙 占쎈�덌옙�뜞占쎌뱽 獄쏆룇釉� 占쎌궞野껓옙
					String yearMonthDay = req.getParameter("yearMonthDay");	///占쎈뼎占쎌젾占쎌벥 占쎌뜞 占쎌뵠占쎈짗占쎌뱽 占쎈막占쎈르 idx嚥∽옙 占쎈�덌옙�뜞占쎌뱽 獄쏆룇釉� 占쎌궞野껓옙
					req.setAttribute("calendarMonthList",dao.getCalendarMonthList(userBean.getLectureId(), yearMonth));
					req.setAttribute("calendarDayList",dao.getCalendarDayList(userBean.getLectureId(), yearMonthDay));
					
					//main �넫�슣瑜ワ옙釉�占쎈뼊 占쎌젟癰귨옙 占쎌읈占쎈뼎
					req.setAttribute("userBean", userBean);
					
					//main 占쎌뒭筌ο옙 占쎈릭占쎈뼊 占쎌젟癰귨옙 占쎌읈占쎈뼎
					req.setAttribute("numStu", dao.getStuNum(userBean.getLectureId()));
					req.setAttribute("checkinNum", dao.getCheckinNum(userBean.getLectureId()));
					req.setAttribute("submissionNum", dao.getSubmissionNum(userBean.getLectureId()));
					req.setAttribute("totalDays", dao.getTotalDays(userBean.getLectureId()));
					req.setAttribute("progressDays", dao.getProgressDays(userBean.getLectureId()));
					
					rd = req.getRequestDispatcher("teacher/main_T.jsp");

				}else if (path.equals("/attendance.tea")) {
					 //�뜝�룞�삕�듃�뜝�룞�삕�뜝�룞�삕�듃�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦怨ㅼ삕 jsp�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占� get�뜝�룞�삕�뜝占� �뜝��琉꾩삕�뜝�룞�삕�뜝占�
					 req.setAttribute("todayAttendanceList",dao.getTodayAttendance(userBean.getLectureId()));
					 rd = req.getRequestDispatcher("teacher/attendance_T.jsp");

				}else if (path.equals("/score.tea")) {
					req.setAttribute("scoreList",dao.getScoreList(userBean.getLectureId()));
					rd = req.getRequestDispatcher("teacher/score_T.jsp");
					
				} else if (path.equals("/assignment.tea")) {
					req.setAttribute("assignmentList", dao.getAssignmentList(userBean.getLectureId()));
					rd = req.getRequestDispatcher("teacher/assignment_T.jsp");
					
					//insert
				} else if(path.equals("/assignment_insert.tea")){
					
					rd = req.getRequestDispatcher("teacher/assignment_T_insert.jsp");
					
				} else if (path.equals("/assignment_detail.tea")) {
					int assignmentId = Integer.parseInt(req.getParameter("idx"));	//疫뀐옙 �뵳�딅뮞占쎈뱜(占쎌굢占쎈뮉 edit占쎈퓠占쎄퐣)占쎈퓠占쎄퐣 idx嚥∽옙 assignmentId�몴占� 獄쏆룇釉섓옙占쏙옙苑� 占쎄텢占쎌뒠(rownum)占쎈툡占쎈뻷
					req.setAttribute("AssignmentBean", dao.getAssignment(assignmentId));
					req.setAttribute("submissionList", dao.getSubmissionList(assignmentId));
					rd = req.getRequestDispatcher("teacher/assignment_T_detail.jsp");
				
				}else if (path.equals("/qna.tea")) {
					ArrayList<QnaLDto> qnaLList = dao.getQnaLList(userBean.getUserId());
					req.setAttribute("qnaLList", qnaLList);
					rd = req.getRequestDispatcher("teacher/qna_T.jsp");

				}else if (path.equals("/qna_detail.tea")) {
					int qnaLId = Integer.parseInt(req.getParameter("idx"));	//疫뀐옙 �뵳�딅뮞占쎈뱜(占쎌굢占쎈뮉 edit占쎈퓠占쎄퐣)占쎈퓠占쎄퐣 idx嚥∽옙 assignmentId�몴占� 獄쏆룇釉섓옙占쏙옙苑� 占쎄텢占쎌뒠(rownum)占쎈툡占쎈뻷
					req.setAttribute("QnaLBean", dao.getQnaL(qnaLId));
					rd = req.getRequestDispatcher("teacher/qna_T_deatil.jsp");
				
				}else {
					System.out.println("鈺곕똻�삺占쎈릭筌욑옙 占쎈륫占쎈뮉 占쎈읂占쎌뵠筌욑옙");
				}
			}else {
				//teacher�뜝�룞�삕 student�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕�떁�뜝�룞�삕 �뜝�떦紐뚯삕 �뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕
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

		//�뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�뙇�눦�삕 �솗�뜝�룞�삕�뜝�떦怨ㅼ삕 �뜝�룞�삕�뜝�뙇�냼紐뚯삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦源띿삕
		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("teacherController(doPost) :: path = " + path);

		//�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
		System.out.println(userBean.toString());
		
		//�뜝�뙥釉앹삕�뜝�떎�뙋�삕 RequestDispatcher
		RequestDispatcher rd = null;
		
		//insert, edit, delete 占쎌벥 野껉퀗�궢 占쎄땀占쎌뒠占쎌뱽 占쏙옙占쎌삢占쎈릭占쎈뮉 result
		///占쎈선占쎈섯野껓옙 占쎄텢占쎌뒠占쎈막筌욑옙 占쎈툡筌욑옙 沃섎챷�젟
		int result;

		try {
			if (userBean.getBelong().equals("teacher")) {

				TeacherDao dao = new TeacherDao();

				if(path.equals("")){
					
				}else if(path.equals("/attendance_checkin.tea")){
					String stdId = (String)req.getParameter("idx");
					result = dao.insertAttendanceCheckin(stdId);
					rd = req.getRequestDispatcher("/attendance.tea");	//占쎈뼄占쎈뻻 today �빊�뮇苑� �뵳�딅뮞占쎈뱜嚥∽옙 占쎌뵠占쎈짗
					
				}else if(path.equals("/attendance_checkout.tea")){
					String stdId = (String)req.getParameter("idx");
					result = dao.updateAttendanceCheckout(stdId);
					rd = req.getRequestDispatcher("/attendance.tea");	//占쎈뼄占쎈뻻 today �빊�뮇苑� �뵳�딅뮞占쎈뱜嚥∽옙 占쎌뵠占쎈짗
					
				}else if (path.equals("/assignment_insert.tea")) {//assignment insert占쎈퓠占쎄퐣 post獄쎻뫗�뻼占쎌몵嚥∽옙 占쎄퐜野껋눘�뱽占쎈르
					

					AssignmentDto assignmentBean=new AssignmentDto();
					
					assignmentBean.setTitle(req.getParameter("title"));
					assignmentBean.setContent(req.getParameter("content"));
					assignmentBean.setLectureId(userBean.getLectureId());
					assignmentBean.setWriter(userBean.getUserId());
					result=dao.insertAssignment(assignmentBean);
					
					rd = req.getRequestDispatcher("/assignment_detail.tea?idx=");	//�뵳�딅뮞占쎈뱜 占쎈읂占쎌뵠筌욑옙嚥∽옙
//					rd = req.getRequestDispatcher("/assignment_detail.tea?idx");	//�뵳�딅뮞占쎈뱜 占쎈읂占쎌뵠筌욑옙嚥∽옙
					
				} else if (path.equals("/assignment_edit.tea")) {
					String title = req.getParameter("title");
					String content = req.getParameter("content");
					String assignmentId = req.getParameter("assignmentId");
					result = dao.updateAssignment(title,content,assignmentId);
					rd = req.getRequestDispatcher("/assignment_detail.ttkea?idx="+assignmentId);	///占쎈땾占쎌젟占쎈립 占쎈읂占쎌뵠筌욑옙嚥∽옙
				} else if (path.equals("/assignment_delete.tea")) {
					int assignmentId = Integer.parseInt(req.getParameter("idx"));
					result = dao.getAssignmentDelete(assignmentId);
					rd = req.getRequestDispatcher("teacher/assignment_T_.jsp");
				
				}else if (path.equals("/qnaAnswer_insert.tea")) {//qna占쎈퓠占쎄퐣 占쎈뼗癰귨옙占쎌뱽 占쎌뿯占쎌젾 占쎌굢占쎈뮉 占쎈땾占쎌젟占쎈막占쎈르 
					String answerContent = req.getParameter("answerContent");
					int questionId = Integer.parseInt(req.getParameter("questionId"));
					result = dao.updateQnaLAnswer(answerContent,questionId);
					rd = req.getRequestDispatcher("/qna_detail.tea?idx="+questionId);	//�뵳�딅뮞占쎈뱜 占쎈읂占쎌뵠筌욑옙嚥∽옙
				}else if(path.equals("/calendar_insert.tea")){
					String startDate = req.getParameter("startDate");
					String endDate = req.getParameter("endDate");
					String title = req.getParameter("title");
					String content = req.getParameter("content");
					result = dao.insertCalendar(startDate, endDate, title, content, userBean.getLectureId());
				}
				else {
					System.out.println("鈺곕똻�삺占쎈릭筌욑옙 占쎈륫占쎈뮉 占쎈읂占쎌뵠筌욑옙");
				}
			}else {
				//teacher�뜝�룞�삕 student�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕�떁�뜝�룞�삕 �뜝�떦紐뚯삕 �뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕
				req.getRequestDispatcher("login.bit");
			}
			rd.forward(req, resp);
		} catch (java.lang.NullPointerException e) {
			resp.sendRedirect("login.bit");
		}
	}

}