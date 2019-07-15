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

import com.bit.model.AttendanceDto;
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
		System.out.println("StudentController(get) :: path = " + path);
		
		//세션 저장
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
		
		try {
			if(userBean.getBelong().equals("student")){
				StudentDao dao = new StudentDao();
				if (path.equals("/main.stu")) {
					String yearMonth = req.getParameter("yearMonthDay");	///달력의 월 이동을 할때 idx로 년월을 받아 올것
					String yearMonthDay = req.getParameter("yearMonthDay");	///달력의 월 이동을 할때 idx로 년월을 받아 올것
//					req.setAttribute("calendarMonthList",dao.getCalendarMonthList(userBean.getLectureId(), yearMonth));
//					req.setAttribute("calendarDayList",dao.getCalendarMonthList(userBean.getLectureId(), yearMonthDay));
					
					//main 좌측하단 정보 전달
					req.setAttribute("userBean", userBean);
					
					//main 우측 하단 정보 전달
					
					rd = req.getRequestDispatcher("student/main_S.jsp");
				} else if (path.equals("/attendance.stu")) {
					
					rd = req.getRequestDispatcher("student/attendance_day_S.jsp");
					
				} else if (path.equals("/attendanceMonth.stu")) {
					String yearMonth = req.getParameter("idx");	///달력의 월 이동을 할때 idx로 년월을 받아 올것
					req.setAttribute("attendanceMonthList", dao.getAttendanceMonthList(userBean.getUserId(), yearMonth));
					rd = req.getRequestDispatcher("student/attendance_month_S.jsp");	//이거 추가해야함
					
				} else if (path.equals("/score.stu")) {
					req.setAttribute("scoreBean", dao.getScore(userBean.getUserId()));
					rd = req.getRequestDispatcher("student/score_S.jsp");
					
				} else if (path.equals("/assignment.stu")) {
					req.setAttribute("assignmentList", dao.getAssignmentList(userBean.getLectureId()));
					rd = req.getRequestDispatcher("student/assignment_S.jsp");
					
				} else if (path.equals("/assignmentdetail.stu")) {
					String assignmentId = req.getParameter("idx");	//목록화면에서 과제 번호를 가져올 것
					//req.setAttribute("assignmentBean", dao.getAssignment(assignmentId));
					//req.setAttribute("submissionBean", dao.getSubmission(assignmentId, userBean.getUserId()));
					
					rd = req.getRequestDispatcher("student/assignment_S/assignmentdetail_S.jsp");
					
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
		System.out.println("StudentController(post) :: path = " + path);
		
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
					QnaLDto qnaLBean = new QnaLDto();
					qnaLBean.setTitle(req.getParameter("title"));
					qnaLBean.setType(req.getParameter("type"));
					qnaLBean.setQuestionContent(req.getParameter("questionContent"));
					qnaLBean.setStuId(userBean.getUserId());					
					result = dao.insertQnaL(qnaLBean);		
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
				}
				
				if(rd!=null){
					rd.forward(req, resp);				
				}
				
				String json = "";
				if(path.equals("/callAttendance.stu")){
					resp.setContentType("text/html;charset=UTF-8"); 
					
					System.out.println("post");
					
					String id = req.getParameter("id");

					//Student 출석상황에 필요한 정보 가져오기
					
					//입실/지각/퇴실 정보 등 status, 입퇴실시간(where오늘,시분만 가져오기),강좌명,강좌기간
					AttendanceDto AttendanceBean = dao.getAttendance(userBean.getUserId());
					//입실 횟수 / 지각 횟수/ 조퇴횟수 / 외출횟수 / 결석 횟수 groupby status
					int[] statusNum = dao.getAttendanceStatusList(userBean.getUserId());
					
					//출결현황 (총출석수, 총수업수), userbean에있는 id 이용
					int attendanceDays = dao.getAttendanceDays(userBean.getUserId());
					//userbean에 들어있는 lectureId
					int totalDays= dao.getTotalDays(userBean.getLectureId());
					
					//출석 지각 조퇴 외출 결석
					json = "{\"status\" : \""+AttendanceBean.getStatus()+"\" , \"name\" : \""+userBean.getName()+"\" ,"
							+ " \"checkinTime\" : \""+AttendanceBean.getCheckinTime()+"\", \"checkoutTime\" : \""+AttendanceBean.getCheckoutTime()+"\","
							+ " \"lecName\" : \""+userBean.getLectureName()+"\", \"startDate\" : \""+userBean.getStartDate()+"\", \"endDate\" : \""+userBean.getEndDate()+"\""
							+ " ,\"attendanceDays\" : \""+attendanceDays+"\", \"totalDays\" : \""+totalDays+"\""
							+ " ,\"출석\" : \""+statusNum[0]+"\", \"지각\" : \""+statusNum[1]+"\", \"조퇴\" : \""+statusNum[2]+"\", \"외출\" : \""+statusNum[3]+"\", \"결석\" : \""+statusNum[4]+"\"}";
					//입실, 퇴실 버튼을 눌렀을 때는 출석입력하고 출석상황 갖고 오기
					System.out.println(json);
					PrintWriter out= resp.getWriter(); 
					out.write(json);
					out.close();
				}else if(path.equals("/callAttendanceBtn.stu")){
					req.setCharacterEncoding("utf-8");
					resp.setContentType("text/html;charset=UTF-8"); 
					
					System.out.println("btnpost");
					
					String btn = req.getParameter("btn");
					System.out.println(btn);
					
					System.out.println(userBean.getUserId());
					result = dao.updateAttendance(userBean.getUserId(), btn);
					AttendanceDto AttendanceBean = null;
					if(result<0){
						System.out.println("오류");
					}else{				
						AttendanceBean = dao.getAttendance(userBean.getUserId());
					}
					json = "{\"status\" : \""+AttendanceBean.getStatus()+"\", "
							+ " \"checkinTime\" : \""+AttendanceBean.getCheckinTime()+"\", \"checkoutTime\" : \""+AttendanceBean.getCheckoutTime()+"\"}";
					
					System.out.println(json);
					PrintWriter out= resp.getWriter(); 
					out.write(json);
					out.close();
				}else {
					System.out.println("존재하지 않는 페이지");
				}
				
			}else {
				//teacher나 student페이지로 접근하려고 하면 걍 보내버림
				req.getRequestDispatcher("login.bit");
			}
			
		}catch (java.lang.NullPointerException e) {
			e.printStackTrace();
			resp.sendRedirect("login.bit");
		}
	}
}
