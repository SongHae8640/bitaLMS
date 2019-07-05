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

		//������ �ּ� Ȯ���ϰ� ���ּҸ� �����ϱ�
		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("teacherController(doGet) :: path = " + path);

		//���� ����
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
		
		//�ߺ��Ǵ� RequestDispatcher
		RequestDispatcher rd = null;

		try {
			if (userBean.getBelong().equals("teacher")) {

				TeacherDao dao = new TeacherDao();
				if (path.equals("/main.tea")) {
					rd = req.getRequestDispatcher("teacher/main_T.jsp");
				} else if (path.equals("/attendance.tea")) {
					 ArrayList<AttendanceDto> todayAttendanceList = dao.getTodayAttendance(userBean.getLecture_id());
					 //��Ʈ����Ʈ�� �����ϰ� jsp���������� get���� �ҷ�����
					 req.setAttribute("todayAttendanceList",todayAttendanceList);
					 rd = req.getRequestDispatcher("teacher/attendance_T.jsp");

				} else if (path.equals("/score.tea")) {
					ArrayList<ScoreDto> scoreList = dao.getScoreList(userBean.getLecture_id());
					req.setAttribute("scoreList",scoreList);
					rd = req.getRequestDispatcher("teacher/score_T.jsp");
					
				} else if (path.equals("/assignment.tea")) {
					ArrayList<AssignmentDto> assignmentList = dao.getAssignmentList(userBean.getLecture_id());
					req.setAttribute("assignmentList", assignmentList);
					rd = req.getRequestDispatcher("teacher/assignment_T.jsp");
					
//					//detail �϶�
//					int assignmentId = 1;	//�� ����Ʈ���� idx�� assignmentId�� �޾ƿͼ� ���(rownum)�ƴ�
//					AssignmentDto AssignmentBean = dao.getAssignmentDetail(assignmentId);
//					req.setAttribute("AssignmentBean", AssignmentBean);
//					ArrayList<SubmsissionDto> submissionList = dao.getSubmissionList(assignmentId); 
//					req.setAttribute("submissionList", submissionList);
//					rd = req.getRequestDispatcher("teacher/assignment_T_detail.jsp");//������ �ּ�
					
					
					
				} else if (path.equals("/qna.tea")) {
					ArrayList<QnaLDto> qnaLList = dao.getQnaLList(userBean.getUserId());
					req.setAttribute("qnaLList", qnaLList);
					rd = req.getRequestDispatcher("teacher/qna_T.jsp");
				
				} else {
					System.out.println("�������� �ʴ� ������");
				}
			}else {
				//teacher�� student�������� �����Ϸ��� �ϸ� �� ��������
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

		//������ �ּ� Ȯ���ϰ� ���ּҸ� �����ϱ�
		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("teacherController(doPost) :: path = " + path);

		//���� ����
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
		
		//�ߺ��Ǵ� RequestDispatcher
		RequestDispatcher rd = null;

		try {
			if (userBean.getBelong().equals("teacher")) {

				TeacherDao dao = new TeacherDao();
				if (path.equals("/assignment.tea")) {//assignment insert���� post������� �Ѱ�����
					String titledao= req.getParameter("title");
					String content;
					
					
				} else if (path.equals("/")) {
					 

				}else {
					System.out.println("�������� �ʴ� ������");
				}
			}else {
				//teacher�� student�������� �����Ϸ��� �ϸ� �� ��������
				req.getRequestDispatcher("login.bit");
			}
			rd.forward(req, resp);
		} catch (java.lang.NullPointerException e) {
			resp.sendRedirect("login.bit");
		}
	}

}