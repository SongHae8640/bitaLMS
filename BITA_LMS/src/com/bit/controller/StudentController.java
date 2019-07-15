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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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

		System.out.println("StudentController(doGet) :: path = " + path);
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
		try {
			if(userBean.getBelong().equals("student")){
				StudentDao dao = new StudentDao();
				if (path.equals("/main.stu")) {	
//					String yearMonthDay = req.getParameter("yearMonthDay");	///달력의 월 이동을 할때 idx로 년월을 받아 올것
//					String yearMonth = yearMonthDay.substring(0, 10);
					
					req.setAttribute("userBean", userBean);
					

					//main �슦痢� �븯�떒 �젙蹂� �쟾�떖

					
					rd = req.getRequestDispatcher("student/main_S.jsp");
				} else if (path.equals("/attendance.stu")) {
					//좌측 정보 전달
					req.setAttribute("attendanceBean", dao.getAttendance(userBean.getUserId())); 	
					req.setAttribute("userBean", userBean);
					
					//main 우측 하단 정보 전달
					req.setAttribute("attendanceDays", dao.getAttendanceDays(userBean.getUserId()));
					req.setAttribute("totalDays", dao.getTotalDays(userBean.getLectureId()));
					req.setAttribute("attendanceStatusList", dao.getAttendanceStatusList(userBean.getUserId()));
					
					rd = req.getRequestDispatcher("student/attendance_day_S.jsp");
					

				}else if (path.equals("/attendanceMonth.stu")) {
					
					rd = req.getRequestDispatcher("student/attendance_month_S.jsp");

					
				} else if (path.equals("/score.stu")) {//scoreBean에 점수를 담아 view로 보냄
					req.setAttribute("scoreBean", dao.getScoreBean(userBean.getUserId()));
					rd = req.getRequestDispatcher("student/score_S.jsp");
					
				} else if (path.equals("/assignment.stu")) {
					req.setAttribute("assignmentList", dao.getAssignmentList(userBean.getLectureId()));
					rd = req.getRequestDispatcher("student/assignment_S.jsp");

				} else if (path.equals("/assignment_detail.stu")) {
					int assignmentId = Integer.parseInt(req.getParameter("idx"));	//목록화면에서 과제 번호를 가져올 것
					req.setAttribute("assignmentBean", dao.getAssignment(assignmentId));
					req.setAttribute("submissionBean", dao.getSubmission(assignmentId, userBean.getUserId())); 

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
				else if(path.equals("/callCalendar.stu")) {
					//json으로 보낼때 한글 깨짐 방지
					resp.setContentType("text/html;charset=UTF-8");
					
					// calendar 가져와야 함
					String yearMonthDay = "2019-07-10";
					String yearMonth = yearMonthDay.substring(0,7);
					JSONArray calendarMonthListJson = dao.getCalendarMonthListJson(userBean.getLectureId());
					PrintWriter out = resp.getWriter();
					out.write(calendarMonthListJson.toJSONString());
					out.close();
				}else if(path.equals("/callAttendanceMonthListJson.stu")) {
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
				}else if(path.equals("/studentStatus.stu")) {
					//json으로 보낼때 한글 깨짐 방지
					resp.setContentType("text/html;charset=UTF-8");
					
					
					JSONObject jObj = new JSONObject();
					jObj.put("attendanceDays", dao.getAttendanceDays(userBean.getUserId()));
					jObj.put("totalyDays", dao.getTotalDays(userBean.getLectureId()));
					jObj.put("newAnswerNum",dao.getNewQnaLAnswerNum(userBean.getUserId()));
					jObj.put("totalQnaLNum", dao.getTotalQnaLNum(userBean.getUserId()));
					jObj.put("progressDays", dao.getProgressDays(userBean.getLectureId()));
					
					PrintWriter out = resp.getWriter();
					out.write(jObj.toJSONString());
					out.close();
				}else {
					System.out.println("알수 없는 페이지입니다.");
				}
				
				
			}else {
				//teacher나 student페이지로 접근하려고 하면 걍 보내버림
				req.getRequestDispatcher("login.bit");
			}
			
			
			if(rd !=null){
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
		
		//세션 저장
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
		
		//json으로 보낼때 한글 깨짐 방지
		resp.setContentType("text/html;charset=UTF-8");
		
		//insert, edit, delete 의 결과 내용을 저장하는 result
		///어떻게 사용할지 아직 미정
		int result;
		
		try {
			if(userBean.getBelong().equals("student")){
				StudentDao dao = new StudentDao();
				if (path.equals("/main.stu")) {
					
				}else if(path.equals("/submission_insert.stu")){

		
				}else if(path.equals("/submission_delete.stu")){
				
					int assignmentId = Integer.parseInt(req.getParameter("idx"));
					SubmissionDto bean_s=new SubmissionDto();
					AttachedFileDto fileBean=new AttachedFileDto();
					bean_s.setAssignmentId(Integer.parseInt(req.getParameter("assignment_id")));
					result = dao.insertSubmission(userBean, bean_s, fileBean);		
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
					QnaLDto bean = new QnaLDto();
					bean.setQnaLId(Integer.parseInt(req.getParameter("qnaLId")));
					bean.setTitle(req.getParameter("title"));
					bean.setQuestionContent(req.getParameter("questionContent"));
					result = dao.updateQnaL(bean);				
					///result 값에 따라 write 값 변경 해야함
					PrintWriter out = resp.getWriter();
					out.write("OK");
					out.close();
				}else if(path.equals("/qna_delete.stu")){
					String[] qnaId = req.getParameterValues("qnaId");
					result = dao.deleteQnaL(qnaId);		
					rd = req.getRequestDispatcher("qna.stu");	
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