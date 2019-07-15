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

import org.json.simple.JSONArray;

import com.bit.model.AssignmentDto;
import com.bit.model.AttendanceDto;
import com.bit.model.CalendarDto;
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


		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("teacherController(doGet) :: path = " + path);


		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
		
		RequestDispatcher rd = null;

		try {
			if (userBean.getBelong().equals("teacher")) {
				TeacherDao dao = new TeacherDao();
		
				
				if (path.equals("/main.tea")) {
					
					req.setAttribute("userBean", userBean);
					


					req.setAttribute("numStu", dao.getStuNum(userBean.getLectureId()));
					req.setAttribute("checkinNum", dao.getCheckinNum(userBean.getLectureId()));
					req.setAttribute("submissionNum", dao.getSubmissionNum(userBean.getLectureId()));
					req.setAttribute("totalDays", dao.getTotalDays(userBean.getLectureId()));
					req.setAttribute("progressDays", dao.getProgressDays(userBean.getLectureId()));
					
					rd = req.getRequestDispatcher("teacher/main_T.jsp");

				}else if (path.equals("/attendance.tea")) {

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
					int assignmentId = Integer.parseInt(req.getParameter("idx"));
					req.setAttribute("AssignmentBean", dao.getAssignment(assignmentId));
					req.setAttribute("submissionList", dao.getSubmissionList(assignmentId));
					rd = req.getRequestDispatcher("teacher/assignment_T_detail.jsp");
				
				}else if (path.equals("/qna.tea")) {
					ArrayList<QnaLDto> qnaLList = dao.getQnaLList(userBean.getUserId());
					req.setAttribute("qnaLList", qnaLList);
					rd = req.getRequestDispatcher("teacher/qna_T.jsp");

				}else if (path.equals("/qna_detail.tea")) {
					int qnaLId = Integer.parseInt(req.getParameter("idx"));	
					req.setAttribute("QnaLBean", dao.getQnaL(qnaLId));
					rd = req.getRequestDispatcher("teacher/qna_T_deatil.jsp");

				}else if (path.equals("/attendance.tea")) {
					req.setAttribute("todayAttendanceList",dao.getTodayAttendance(userBean.getLectureId()));
					rd = req.getRequestDispatcher("teacher/attendance_T.jsp");
				}
				
				//Using ajax 
				else if(path.equals("/callCalendar.tea")) {
					//json으로 보낼때 한글 깨짐 방지
					resp.setContentType("text/html;charset=UTF-8");
					
					// calendar 가져와야 함
					String yearMonthDay = "2019-07-10";
					String yearMonth = yearMonthDay.substring(0,7);
					
					JSONArray calendarMonthListJson = dao.getCalendarMonthListJson(yearMonth, userBean.getLectureId());
					PrintWriter out = resp.getWriter();
					out.write(calendarMonthListJson.toJSONString());
					out.close();
				}
				
				
				//Forward only when rd is used.
				if(rd!=null){
					rd.forward(req, resp);
				}
			}else {

				req.getRequestDispatcher("login.bit");
			}
		} catch (java.lang.NullPointerException e) {
//			e.printStackTrace();
			resp.sendRedirect("login.bit");
		}

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("teacherController(doPost) :: path = " + path);

		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
		System.out.println(userBean.toString());
		

		RequestDispatcher rd = null;
		
		//insert, edit, delete �쓽 寃곌낵 �궡�슜�쓣 ���옣�븯�뒗 result
		///�뼱�뼸寃� �궗�슜�븷吏� �븘吏� 誘몄젙
		int result = 0;

		try {
			if (userBean.getBelong().equals("teacher")) {

				TeacherDao dao = new TeacherDao();

				if(path.equals("")){
					

				
				}else if (path.equals("/assignment_insert.tea")) {//assignment insert�뜝�럥�뱺�뜝�럡�맋 post�뛾�렮維쀯옙六쇔뜝�럩紐드슖�댙�삕 �뜝�럡�맂�뇦猿뗫닔占쎈굵�뜝�럥瑜�
					

					AssignmentDto assignmentBean=new AssignmentDto();
					
					assignmentBean.setTitle(req.getParameter("title"));
					assignmentBean.setContent(req.getParameter("content"));
					assignmentBean.setLectureId(userBean.getLectureId());
					assignmentBean.setWriter(userBean.getUserId());
					result=dao.insertAssignment(assignmentBean);///result瑜� �뼱�뼸寃� �궗�슜�븷吏� �굹以묒뿉 �깮媛�
					
					rd = req.getRequestDispatcher("/assignment_detail.tea?idx=");	//由ъ뒪�듃 �럹�씠吏�濡�

					
				} else if (path.equals("/assignment_edit.tea")) {
					String title = req.getParameter("title");
					String content = req.getParameter("content");
					String assignmentId = req.getParameter("assignmentId");
					result = dao.updateAssignment(title,content,assignmentId);
					rd = req.getRequestDispatcher("/assignment_detail.ttkea?idx="+assignmentId);	///�뜝�럥�빢�뜝�럩�젧�뜝�럥由� �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕�슖�댙�삕
				} else if (path.equals("/assignment_delete.tea")) {
					int assignmentId = Integer.parseInt(req.getParameter("idx"));
					result = dao.deleteAssignment(assignmentId);
					rd = req.getRequestDispatcher("teacher/assignment_T_.jsp");
				
				}else if (path.equals("/qnaAnswer_insert.tea")) {//qna�뜝�럥�뱺�뜝�럡�맋 �뜝�럥堉쀧솻洹⑥삕�뜝�럩諭� �뜝�럩肉��뜝�럩�졑 �뜝�럩援℡뜝�럥裕� �뜝�럥�빢�뜝�럩�젧�뜝�럥留됧뜝�럥瑜� 
					String answerContent = req.getParameter("answerContent");
					int questionId = Integer.parseInt(req.getParameter("questionId"));
					result = dao.updateQnaLAnswer(answerContent,questionId);
					rd = req.getRequestDispatcher("/qna_detail.tea?idx="+questionId);	//占쎈뎨占쎈봾裕욃뜝�럥諭� �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕�슖�댙�삕
				}else if(path.equals("/calendar_insert.tea")){
					String startDate = req.getParameter("startDate");
					String endDate = req.getParameter("endDate");
					String title = req.getParameter("title");
					String content = req.getParameter("content");
					result = dao.insertCalendar(startDate, endDate, title, content, userBean.getLectureId());
				}

				if(result>0){
					rd.forward(req, resp);					
				}
				
				if(path.equals("/attendance_check.tea")){
					String stdId = req.getParameter("id");
					String btn = req.getParameter("btn");
					System.out.println(stdId+":"+btn);
					result = dao.updateAttendance(stdId,btn);
					
					if(result>0){
						PrintWriter out= resp.getWriter(); 
						out.write("{\"msg\":\"성공적으로 수정되었습니다\"}");
						out.close();
					}
				}
				else if (path.equals("/calendar_insert.tea")) {
					// calendar insert 페이지
					CalendarDto calendarBean = new CalendarDto();
					calendarBean.setLectureId(userBean.getLectureId());	
					calendarBean.setStartDate(req.getParameter("startDate")+" 00:00:00");
					calendarBean.setEndDate(req.getParameter("endDate")+" 23:59:00");
					calendarBean.setTitle(req.getParameter("title"));
					calendarBean.setContent(req.getParameter("content"));
					result = dao.insertCalendar(calendarBean);
				}else if(path.equals("/calendar_update1.tea")){	//drag를 통한 업데이트
					CalendarDto calendarBean = new CalendarDto();
					calendarBean.setCalendarId(Integer.parseInt(req.getParameter("calendarId")));
					calendarBean.setTitle(req.getParameter("title"));
					calendarBean.setLectureId(userBean.getLectureId());
					calendarBean.setLectureName(req.getParameter("lectureName"));	///update에서는 빼도 되는 부분. 정보 이동 확을을 위해 추가함
					calendarBean.setStartDate(req.getParameter("startDate").replaceAll("T", " "));
					calendarBean.setEndDate(req.getParameter("endDate").replaceAll("T", " "));
					calendarBean.setContent(req.getParameter("content"));
					System.out.println(calendarBean.toString());
					result = dao.updateCalendar(calendarBean);
					System.out.println("result = "+result);
				}else if(path.equals("/calendar_update2.tea")){	//상세 보기 > 수정을 통한 업데이트
					CalendarDto calendarBean = new CalendarDto();
					calendarBean.setCalendarId(Integer.parseInt(req.getParameter("calendarId")));
					calendarBean.setTitle(req.getParameter("title"));
					calendarBean.setLectureId(userBean.getLectureId());
					calendarBean.setLectureName(req.getParameter("lectureName"));	///update에서는 빼도 되는 부분. 정보 이동 확을을 위해 추가함
					calendarBean.setStartDate(req.getParameter("startDate")+" 00:00:00");
					calendarBean.setEndDate(req.getParameter("endDate")+" 23:59:00");
					calendarBean.setContent(req.getParameter("content"));
					System.out.println(calendarBean.toString());
					result = dao.updateCalendar(calendarBean);
					System.out.println("result = "+result);
				}else if(path.equals("/calendar_delete.tea")){
					result = dao.deleteCalendar(Integer.parseInt(req.getParameter("calendarId")));
					System.out.println("result = "+result);
				}else {
					System.out.println("존재하지 않는 페이지");

				}
			}else {
				
				req.getRequestDispatcher("login.bit");
			}
		} catch (java.lang.NullPointerException e) {
			resp.sendRedirect("login.bit");
		}
	}

}