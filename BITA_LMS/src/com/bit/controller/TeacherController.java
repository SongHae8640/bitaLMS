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


		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
		
		//占쎈쐻占쎈솯�뇡�빘�굲占쎈쐻占쎈뼄占쎈솇占쎌굲 RequestDispatcher
		RequestDispatcher rd = null;

		try {
			if (userBean.getBelong().equals("teacher")) {
				TeacherDao dao = new TeacherDao();
				///lecture_id �뜝�럥裕� �뜝�럩�겱�썒�슪�삕 �뜝�럥�몗�뜝�럡�맋 �솻洹⑥삕�뜝�럥�빢�슖�댙�삕 �뜝�럥由��뜝�럡�룎 占쎈쑏占쎈닑占쎈츎�뇦猿볦삕 占쎈꽞占쎈열占쎈굵 �뇦猿볦삕 �뤆�룇�듌占쎈쾳
				
				
				if (path.equals("/main.tea")) {
					
					//main 占쎈꽞占쎌뒩�몴�꺈�삕�뇡占썲뜝�럥堉� �뜝�럩�젧�솻洹⑥삕 �뜝�럩�쓧�뜝�럥堉�
					req.setAttribute("userBean", userBean);
					

					//main �슦痢� �븯�떒 �젙蹂� �쟾�떖


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
					int assignmentId = Integer.parseInt(req.getParameter("idx"));	//�뼨�먯삕 占쎈뎨占쎈봾裕욃뜝�럥諭�(�뜝�럩援℡뜝�럥裕� edit�뜝�럥�뱺�뜝�럡�맋)�뜝�럥�뱺�뜝�럡�맋 idx�슖�댙�삕 assignmentId占쎈ご�뜝占� �뛾�룇猷뉔뇡�꼻�삕�뜝�룞�삕�땻占� �뜝�럡�뀬�뜝�럩�뮔(rownum)�뜝�럥�닡�뜝�럥六�
//					String isCheck=req.getParameter(req.getParameter("idx"));
//					SubmissionDto bean=(SubmissionDto)req.getAttribute("idx");
					System.out.println("null id="+userBean.toString());
//					System.out.println(bean.toString());
//					String stdId=bean.getStdName();
//					System.out.println(stdId);
					req.setAttribute("AssignmentBean", dao.getAssignment(assignmentId));
					req.setAttribute("submissionList", dao.getSubmissionList(assignmentId));
//					req.setAttribute("submissionIscheck", dao.getSubmissionIscheck(assignmentId,stdId));
					rd = req.getRequestDispatcher("teacher/assignment_T_detail.jsp");
				
				}else if (path.equals("/qna.tea")) {
					ArrayList<QnaLDto> qnaLList = dao.getQnaLList(userBean.getUserId());
					req.setAttribute("qnaLList", qnaLList);
					rd = req.getRequestDispatcher("teacher/qna_T.jsp");

				}else if (path.equals("/qna_detail.tea")) {
					int qnaLId = Integer.parseInt(req.getParameter("idx"));	//�뼨�먯삕 占쎈뎨占쎈봾裕욃뜝�럥諭�(�뜝�럩援℡뜝�럥裕� edit�뜝�럥�뱺�뜝�럡�맋)�뜝�럥�뱺�뜝�럡�맋 idx�슖�댙�삕 assignmentId占쎈ご�뜝占� �뛾�룇猷뉔뇡�꼻�삕�뜝�룞�삕�땻占� �뜝�럡�뀬�뜝�럩�뮔(rownum)�뜝�럥�닡�뜝�럥六�
					req.setAttribute("QnaLBean", dao.getQnaL(qnaLId));
					rd = req.getRequestDispatcher("teacher/qna_T_deatil.jsp");

				}else if(path.equals("/assignment_check.tea")) {
					int assignmentId=Integer.parseInt(req.getParameter("assignment_id"));
					String stdId=req.getParameter("std_id");
					String is_check=req.getParameter("is_check");
					dao.getSubmissionIscheck(assignmentId, stdId);
//					rd = req.getRequestDispatcher("teacher/assignment_T_detail.jsp");
				}
				if (path.equals("/attendance.tea")) {
					//泥섏쓬 異쒖꽍�긽�깭瑜� �쟾遺� 戮묒븘�삤湲�
					//�씠由�,踰꾪듉,�긽�깭
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
		System.out.println(userBean.toString());
		

		RequestDispatcher rd = null;
		
		//insert, edit, delete 의 결과 내용을 저장하는 result
		///어떻게 사용할지 아직 미정
		int result = 0;

		try {
			if (userBean.getBelong().equals("teacher")) {

				TeacherDao dao = new TeacherDao();

				if(path.equals("")){
					

				
				}else if (path.equals("/assignment_insert.tea")) {//assignment insert�뜝�럥�뱺�뜝�럡�맋 post�뛾�렮維쀯옙六쇔뜝�럩紐드슖�댙�삕 �뜝�럡�맂�뇦猿뗫닔占쎈굵�뜝�럥瑜�
					
					System.out.println(1);
					AssignmentDto assignmentBean=new AssignmentDto();
					
					assignmentBean.setTitle(req.getParameter("title"));
					assignmentBean.setContent(req.getParameter("content"));
					assignmentBean.setLectureId(userBean.getLectureId());
					assignmentBean.setWriter(userBean.getUserId());
					result=dao.insertAssignment(assignmentBean);///result瑜� �뼱�뼸寃� �궗�슜�븷吏� �굹以묒뿉 �깮媛�
					
					rd = req.getRequestDispatcher("/assignment.tea");	//由ъ뒪�듃 �럹�씠吏�濡�

					
				} else if (path.equals("/assignment_edit.tea")) {
					System.out.println(2);
//					String title = req.getParameter("title");
//					String content = req.getParameter("content");
//					String assignmentId = req.getParameter("assignmentId");
//					result = dao.updateAssignment(title,content,assignmentId);
					rd = req.getRequestDispatcher("/assignment.tea");
//					rd = req.getRequestDispatcher("/assignment_detail.ttkea?idx="+assignmentId);	///�뜝�럥�빢�뜝�럩�젧�뜝�럥由� �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕�슖�댙�삕
				} else if (path.equals("/assignment_delete.tea")) {
					System.out.println(3);
					AssignmentDto bean=new AssignmentDto();
					System.out.println("bean 나오냐?="+bean);
					int assignmentId = Integer.parseInt(req.getParameter("idx"));
					System.out.println("assignmentid="+assignmentId);
					result = dao.deleteAssignment(assignmentId);
					rd = req.getRequestDispatcher("/assignment.tea");
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
				
				//鍮꾨룞湲� �넻�떊
				if(path.equals("/attendance_check.tea")){
					//踰꾪듉媛믪뿉 �뵲�씪�꽌 update瑜� �떎瑜닿쾶 �닔�뻾
					String stdId = req.getParameter("id");
					String btn = req.getParameter("btn");
					System.out.println(stdId+":"+btn);
					result = dao.updateAttendance(stdId,btn);
					
					if(result>0){
						PrintWriter out= resp.getWriter(); 
						out.write("{\"msg\":\"�꽦怨듭쟻�쑝濡� �닔�젙�릺�뿀�뒿�땲�떎\"}");
						out.close();
					}
				}else {
					System.out.println("議댁옱�븯吏� �븡�뒗 �럹�씠吏�");

				}
			}else {
				//teacher占쎈쐻占쎈짗占쎌굲 student占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻�뜝占� 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈뻶占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈뼣筌뤿슣�굲 占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲
				req.getRequestDispatcher("login.bit");
			}
		} catch (java.lang.NullPointerException e) {
			resp.sendRedirect("login.bit");
		}
		System.out.println("Tea Con : method : post - if문 퇴장");
	}

	private void println(int i) {
		// TODO Auto-generated method stub
		
	}

}