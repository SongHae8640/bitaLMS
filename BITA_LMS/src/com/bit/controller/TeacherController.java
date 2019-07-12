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

		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("teacherController(doGet) :: path = " + path);

		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
		
		//�ߺ��Ǵ� RequestDispatcher
		RequestDispatcher rd = null;

		try {
			if (userBean.getBelong().equals("teacher")) {
				TeacherDao dao = new TeacherDao();
				///lecture_id 는 자주 써서 변수로 하나 빼는게 좋을 것 같음
				
				if (path.equals("/main.tea")) {
					
					//main 좌측하단 정보 전달
					req.setAttribute("userBean", userBean);
					
					//main 우측 하단 정보 전달


					req.setAttribute("numStu", dao.getStuNum(userBean.getLectureId()));
					req.setAttribute("checkinNum", dao.getCheckinNum(userBean.getLectureId()));
					req.setAttribute("submissionNum", dao.getSubmissionNum(userBean.getLectureId()));
					req.setAttribute("totalDays", dao.getTotalDays(userBean.getLectureId()));
					req.setAttribute("progressDays", dao.getProgressDays(userBean.getLectureId()));
					
					rd = req.getRequestDispatcher("teacher/main_T.jsp");

				}else if (path.equals("/attendance.tea")) {
					 //��Ʈ����Ʈ�� �����ϰ� jsp������� get��� �ҷ����
					 req.setAttribute("todayAttendanceList",dao.getTodayAttendance(userBean.getLectureId()));
					 rd = req.getRequestDispatcher("teacher/attendance_T.jsp");
				}else if (path.equals("/score.tea")) {
					req.setAttribute("scoreList",dao.getScoreList(userBean.getLectureId()));
					rd = req.getRequestDispatcher("teacher/score_T.jsp");
					
				} else if (path.equals("/assignment.tea")) {
					req.setAttribute("assignmentList", dao.getAssignmentList(userBean.getLectureId()));
					rd = req.getRequestDispatcher("teacher/assignment_T.jsp");
	
				}else if (path.equals("/assignment_detail.tea")) {
					int assignmentId = Integer.parseInt(req.getParameter("idx"));	//글 리스트(또는 edit에서)에서 idx로 assignmentId를 받아와서 사용(rownum)아님
					req.setAttribute("AssignmentBean", dao.getAssignment(assignmentId));
					req.setAttribute("submissionList", dao.getSubmissionList(assignmentId));
					rd = req.getRequestDispatcher("teacher/assignment_T_deatil.jsp");
				
				}else if (path.equals("/qna.tea")) {
					ArrayList<QnaLDto> qnaLList = dao.getQnaLList(userBean.getUserId());
					req.setAttribute("qnaLList", qnaLList);
					rd = req.getRequestDispatcher("teacher/qna_T.jsp");

				}else if (path.equals("/qna_detail.tea")) {
					int qnaLId = Integer.parseInt(req.getParameter("idx"));	//글 리스트(또는 edit에서)에서 idx로 assignmentId를 받아와서 사용(rownum)아님
					req.setAttribute("QnaLBean", dao.getQnaL(qnaLId));
					rd = req.getRequestDispatcher("teacher/qna_T_deatil.jsp");
				}
				if (path.equals("/attendance.tea")) {
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
		
		RequestDispatcher rd = null;
		
		//insert, edit, delete 의 결과 내용을 저장하는 result
		///어떻게 사용할지 아직 미정
		int result = 0;

		try {
			if (userBean.getBelong().equals("teacher")) {

				TeacherDao dao = new TeacherDao();

				if(path.equals("")){
					
				}else if (path.equals("/assignment_insert.tea")) {//assignment insert에서 post방식으로 넘겼을때
					String title = req.getParameter("title");
					String content = req.getParameter("content");
					result = dao.insertAssignment(title, content,userBean.getLectureId());	///result를 어떻게 사용할지 나중에 생각
					rd = req.getRequestDispatcher("/assignment.tea");	//리스트 페이지로
					
				} else if (path.equals("/assignment_edit.tea")) {
					String title = req.getParameter("title");
					String content = req.getParameter("content");
					String assingmentId = req.getParameter("assignmentId");
					result = dao.updateAssignment(title,content,assingmentId);
					rd = req.getRequestDispatcher("/assignment_detail.ttkea?idx="+assingmentId);	///수정한 페이지로
				} else if (path.equals("/assignment_delete.tea")) {
					int assignmentId = Integer.parseInt(req.getParameter("idx"));
					result = dao.deleteAssignment(assignmentId);
					rd = req.getRequestDispatcher("teacher/assignment_T_.jsp");
				
				}else if (path.equals("/qnaAnswer_insert.tea")) {//qna에서 답변을 입력 또는 수정할때 
					String answerContent = req.getParameter("answerContent");
					int questionId = Integer.parseInt(req.getParameter("questionId"));
					result = dao.updateQnaLAnswer(answerContent,questionId);
					rd = req.getRequestDispatcher("/qna_detail.tea?idx="+questionId);	//리스트 페이지로
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
				
				//비동기 통신
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
				}else {
					System.out.println("존재하지 않는 페이지");
				}
			}else {
				//teacher�� student������� ����Ϸ�� �ϸ� �� ������
				req.getRequestDispatcher("login.bit");
			}
		} catch (java.lang.NullPointerException e) {
			resp.sendRedirect("login.bit");
		}
	}

}