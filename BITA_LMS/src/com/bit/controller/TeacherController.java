package com.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
import com.bit.model.SubmissionDto;
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


		try {
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
		RequestDispatcher rd = null;

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
					//일별출석
					 req.setAttribute("todayAttendanceList",dao.getTodayAttendance(userBean.getLectureId()));
					 rd = req.getRequestDispatcher("teacher/attendance_T.jsp");
				}else if (path.equals("/attendance_month.tea")) {
					//월별출석
					rd = req.getRequestDispatcher("teacher/attendance_T_month.jsp");
				}else if (path.equals("/score.tea")) {					//강사가 자신의강의 아이디를 넣으면 해당 강의의 학생들의 점수들만 볼 수 있음
					req.setAttribute("scoreList",dao.getScoreList(lecture_id));	//test 1반일 때 나중에 userBean.getLectureId() 로 바꿔
					System.out.println("/score.tea두겟"+lecture_id);
					rd = req.getRequestDispatcher("teacher/score_T.jsp");
					
				}else if (path.equals("/score_insert.tea")) {			//점수 입력
					req.setAttribute("idx", req.getParameter("idx"));
					System.out.println(req.getParameter("idx"));
					req.setAttribute("scoreList",dao.getScoreList(userBean.getLectureId()));	//test 1반일 때 나중에 userBean.getLectureId() 로 바꿔
					rd = req.getRequestDispatcher("teacher/score_T_insert.jsp");
					 
				}else if (path.equals("/score_update.tea")) {			//점수 입력
					req.setAttribute("idx", req.getParameter("idx"));
					System.out.println(req.getParameter("idx")); 
					req.setAttribute("scoreList",dao.getScoreList(userBean.getLectureId()));	//test 1반일 때 나중에 userBean.getLectureId() 로 바꿔
					rd = req.getRequestDispatcher("teacher/score_T_update.jsp");
					 
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
//					req.setAttribute("submissionIscheck", dao.getSubmissionIscheck(assignmentId,stdId));
					rd = req.getRequestDispatcher("teacher/assignment_T_detail.jsp");
				
				}else if (path.equals("/qna.tea")) {
					req.setAttribute("qnaLList", dao.getQnaLList(userBean.getLectureId()));
					rd = req.getRequestDispatcher("teacher/qna_T.jsp");

				}else if (path.equals("/qna_detail.tea")) {
					int qnaLId = Integer.parseInt(req.getParameter("idx"));	
					System.out.println(qnaLId);
					req.setAttribute("qnaLBean", dao.getQnaL(qnaLId));
					rd = req.getRequestDispatcher("teacher/qna_T_detail.jsp");

				}else if (path.equals("/attendance.tea")) {
					//처음 출석상태를 전부 뽑아오기
					//이름,버튼,상태
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
					
					JSONArray calendarMonthListJson = dao.getCalendarMonthListJson(userBean.getLectureId());
					PrintWriter out = resp.getWriter();
					out.write(calendarMonthListJson.toJSONString());
					out.close();
				}
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
		int lecture_id = userBean.getLectureId();

		RequestDispatcher rd = null;
		
		//insert, edit, delete 의 결과 내용을 저장하는 result
		///어떻게 사용할지 아직 미정
		int result = 0;

		try {
			if (userBean.getBelong().equals("teacher")) {

				TeacherDao dao = new TeacherDao();

				if(path.equals("")){
				
				}else if (path.equals("/assignment_insert.tea")) {//assignment insert
					AssignmentDto assignmentBean=new AssignmentDto();
					
					assignmentBean.setTitle(req.getParameter("title"));
					assignmentBean.setContent(req.getParameter("content"));
					assignmentBean.setLectureId(userBean.getLectureId());
					assignmentBean.setWriter(userBean.getUserId());
					result=dao.insertAssignment(assignmentBean);///result瑜� �뼱�뼸寃� �궗�슜�븷吏� �굹以묒뿉 �깮媛�
					
					rd = req.getRequestDispatcher("/assignment.tea");	//由ъ뒪�듃 �럹�씠吏�濡�

					
				} else if (path.equals("/assignment_edit.tea")) {
					System.out.println(2);
					rd = req.getRequestDispatcher("/assignment.tea");

				} else if (path.equals("/assignment_delete.tea")) {
					System.out.println(3);
					AssignmentDto bean=new AssignmentDto();
					int assignmentId = Integer.parseInt(req.getParameter("idx"));
					System.out.println("assignmentid="+assignmentId);
					result = dao.deleteAssignment(assignmentId);
					rd = req.getRequestDispatcher("teacher/assignment_T_.jsp");				
        }else if(path.equals("/score.tea")){
					String name_list = req.getParameter("name_list");
					System.out.println("이름 받아옴:"+name_list);
					String[] name_arr = name_list.split(",");
					
					String score_list1 = req.getParameter("score_list1");
					System.out.println("1시험점수 받아옴:"+score_list1);
					String[] score_arr1 = score_list1.split(",");			//list1숫자타입으로바꾸기
					String score_list2 = req.getParameter("score_list2");
					System.out.println("2시험점수 받아옴:"+score_list1);
					String[] score_arr2 = score_list2.split(",");			//list2숫자타입으로바꾸기
					String score_list3 = req.getParameter("score_list3");
					System.out.println("3시험점수 받아옴:"+score_list1);
					String[] score_arr3 = score_list3.split(",");			//list3숫자타입으로바꾸기
					int[] score_num_arr1 = new int[name_arr.length];			//인트배열로 변환
					int[] score_num_arr2 = new int[name_arr.length];			//인트배열로 변환
					int[] score_num_arr3 = new int[name_arr.length];			//인트배열로 변환
					
					for(int i = 0; i<name_arr.length; i++){  
						score_num_arr1[i] = Integer.parseInt(score_arr1[i]);
						score_num_arr2[i] = Integer.parseInt(score_arr2[i]);
						score_num_arr3[i] = Integer.parseInt(score_arr3[i]);
					}
					result = dao.updateAvgscore(name_arr,score_num_arr1,score_num_arr2,score_num_arr3,lecture_id);	//1->dao.getScoreList(userBean.getLectureId())
					if(result==1) {
						System.out.println("평균출력성공 "+result);
					}else {  
						System.out.println("평균출력실패 "+result);
					}
				}else if(path.equals("/score_insert.tea")) {
					String idx = req.getParameter("idx");
					int count = Integer.parseInt(idx);
						if(idx=="1") {
							count=1;
						}else if(idx=="2") {
							count=2;
						}else if(idx=="3") { 
							count=3;
						}
					System.out.println("idx "+idx);            			//경우나눠서 count1 ,2, 3
					String name_list = req.getParameter("name_list");
					System.out.println("이름 받아옴:"+name_list);
					String[] name_arr = name_list.split(",");
					String score_list = req.getParameter("score_list");
					System.out.println("시험점수 받아옴:"+score_list);
					String[] score_arr = score_list.split(",");			//숫자타입으로바꾸기
					int[] score_num_arr = new int[score_arr.length];	//인트배열로 변환
					for(int i = 0; i<score_arr.length; i++){
						score_num_arr[i] = Integer.parseInt(score_arr[i]);
					}
			
					result = dao.insertScore(name_arr,score_num_arr,lecture_id,count); //1->dao.getScoreList(userBean.getLectureId())
					System.out.println("result성적"+result);
					if(result==1) {
						System.out.println("성적입력성공"+result);
					}else {  
						System.out.println("성적입력실패"+result);
					}
				}
				
				//비동기 통신
				resp.setContentType("text/html;charset=UTF-8");
				if(path.equals("/attendance_check.tea")){
					//버튼값에 따라서 update를 다르게 수행
					String stdId = req.getParameter("id");
					String btn = req.getParameter("btn");
					System.out.println(stdId+":"+btn);
					result = dao.updateAttendance(stdId,btn);
					
					if(result>0){
						PrintWriter out= resp.getWriter(); 
						out.write("{\"msg\":\"성공적으로 수정되었습니다\"}");
						out.close();
					}
				}else if(path.equals("/attendance_month.tea")){
					// 수강생관리 목록 페이지(월별) 월 이동
					String yyyymm = req.getParameter("yearMonth");
					System.out.println(yyyymm);
					ArrayList<AttendanceDto> monthAttendance = dao.getMonthAttendance(userBean.getLectureId(), yyyymm);
					PrintWriter out= resp.getWriter(); 
					out.write(dao.getMonthAttendanceJson(monthAttendance)+"");
					out.close();
        else if(path.equals("/qna_update.tea")){
					QnaLDto bean = new QnaLDto();
					bean.setQnaLId(Integer.parseInt(req.getParameter("qnaLId")));
					bean.setAnswerContent(req.getParameter("questionAnswer"));
					System.out.println("bean = "+bean.toString());
					result = dao.updateQnaL(bean);
					System.out.println("result = "+result);
					
					PrintWriter out = resp.getWriter();
					out.write("OK");
					out.close();
				}else if (path.equals("/qna_delete.tea")) {
					// 큐엔에이 삭제 페이지
					result = dao.deleteQnaL(Integer.parseInt(req.getParameter("qnaLId")));
					PrintWriter out = resp.getWriter();
					out.write("OK");
					out.close();
				}else if(path.equals("/attendance_check.tea")){
					String stdId = req.getParameter("id");
					String btn = req.getParameter("btn");
					System.out.println(stdId+":"+btn);
				/* result = dao.updateAttendance(stdId,btn); */
				}else if (path.equals("/calendar_insert.tea")) {
					// calendar insert 페이지
					CalendarDto calendarBean = new CalendarDto();
					calendarBean.setLectureId(userBean.getLectureId());	
					calendarBean.setStartDate(req.getParameter("startDate")+" 00:00:00");
					calendarBean.setEndDate(req.getParameter("endDate")+" 23:59:00");
					calendarBean.setTitle(req.getParameter("title"));
					calendarBean.setContent(req.getParameter("content"));
					result = dao.insertCalendar(calendarBean);
				}else if(path.equals("/calendar_updateDrag.tea")){	//drag를 통한 업데이트
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
				}else if(path.equals("/calendar_updateEdit.tea")){	//상세 보기 > 수정을 통한 업데이트
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
					System.out.println("존재하지 않는 페이지 입니다.");

				}
				
				if(result>0){
					rd.forward(req, resp);					
				}
			}else {
				req.getRequestDispatcher("login.bit");
			}
		} 
		}catch (java.lang.NullPointerException e) {
			resp.sendRedirect("login.bit");	
		}
		System.out.println("Tea Con : method : post - if문 퇴장");
	}
}