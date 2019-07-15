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


		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("teacherController(doGet) :: path = " + path);


		try {
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
		System.out.println("userBean"+userBean);
		int lecture_id = userBean.getLectureId();
		//�뜝�뙥釉앹삕�뜝�떎�뙋�삕 RequestDispatcher
		RequestDispatcher rd = null;

			if (userBean.getBelong().equals("teacher")) {
				TeacherDao dao = new TeacherDao();
				///lecture_id 占쎈뮉 占쎌쁽雅뚳옙 占쎈쑅占쎄퐣 癰귨옙占쎈땾嚥∽옙 占쎈릭占쎄돌 �뜮�눖�뮉野껓옙 �넫�뿭�뱽 野껓옙 揶쏆늿�벉
				
				
				if (path.equals("/main.tea")) {
					
					//main �넫�슣瑜ワ옙釉�占쎈뼊 占쎌젟癰귨옙 占쎌읈占쎈뼎
					req.setAttribute("userBean", userBean);
					

					//main 우측 하단 정보 전달


					req.setAttribute("numStu", dao.getStuNum(userBean.getLectureId()));
					req.setAttribute("checkinNum", dao.getCheckinNum(userBean.getLectureId()));
					req.setAttribute("submissionNum", dao.getSubmissionNum(userBean.getLectureId()));
					req.setAttribute("totalDays", dao.getTotalDays(userBean.getLectureId()));
					req.setAttribute("progressDays", dao.getProgressDays(userBean.getLectureId()));
					
					rd = req.getRequestDispatcher("teacher/main_T.jsp");

				}else if (path.equals("/attendance.tea")) {

					 req.setAttribute("todayAttendanceList",dao.getTodayAttendance(userBean.getLectureId()));
					 rd = req.getRequestDispatcher("teacher/attendance_T.jsp");
					 
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

				}else if (path.equals("/attendance.tea")) {
					//처음 출석상태를 전부 뽑아오기
					//이름,버튼,상태
					req.setAttribute("todayAttendanceList",dao.getTodayAttendance(userBean.getLectureId()));
					rd = req.getRequestDispatcher("teacher/attendance_T.jsp");
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
					

				
				}else if (path.equals("/assignment_insert.tea")) {//assignment insert占쎈퓠占쎄퐣 post獄쎻뫗�뻼占쎌몵嚥∽옙 占쎄퐜野껋눘�뱽占쎈르
					
					AssignmentDto assignmentBean=new AssignmentDto();
					
					assignmentBean.setTitle(req.getParameter("title"));
					assignmentBean.setContent(req.getParameter("content"));
					assignmentBean.setLectureId(userBean.getLectureId());
					assignmentBean.setWriter(userBean.getUserId());
					result=dao.insertAssignment(assignmentBean);///result를 어떻게 사용할지 나중에 생각
					
					rd = req.getRequestDispatcher("/assignment_detail.tea?idx=");	//리스트 페이지로

					
				} else if (path.equals("/assignment_edit.tea")) {
					String title = req.getParameter("title");
					String content = req.getParameter("content");
					String assignmentId = req.getParameter("assignmentId");
					result = dao.updateAssignment(title,content,assignmentId);
					rd = req.getRequestDispatcher("/assignment_detail.ttkea?idx="+assignmentId);	///占쎈땾占쎌젟占쎈립 占쎈읂占쎌뵠筌욑옙嚥∽옙
				} else if (path.equals("/assignment_delete.tea")) {
					int assignmentId = Integer.parseInt(req.getParameter("idx"));
					result = dao.deleteAssignment(assignmentId);
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
				if(path.equals("/attendance_check.tea")){
					//버튼값에 따라서 update를 다르게 수행
					String stdId = req.getParameter("id");
					String btn = req.getParameter("btn");
					System.out.println(stdId+":"+btn);
				/* result = dao.updateAttendance(stdId,btn); */
					
					if(result>0){
						PrintWriter out= resp.getWriter(); 
						out.write("{\"msg\":\"성공적으로 수정되었습니다\"}");
						out.close();
					}else {
						System.out.println("존재하지 않는 페이지");
						
					}
					
			}else {
				//teacher�뜝�룞�삕 student�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕�떁�뜝�룞�삕 �뜝�떦紐뚯삕 �뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕
				req.getRequestDispatcher("login.bit");
			}
		} 
		}catch (java.lang.NullPointerException e) {
			resp.sendRedirect("login.bit");	
		}
	}
}