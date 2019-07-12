package com.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		System.out.println("StudentController :: path = " + path);
		
		//���� ����
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
		
		try {
			if(userBean.getBelong().equals("student")){
				StudentDao dao = new StudentDao();
				if (path.equals("/main.stu")) {
					String yearMonth = req.getParameter("yearMonthDay");	///�޷��� �� �̵��� �Ҷ� idx�� ����� �޾� �ð�
					String yearMonthDay = req.getParameter("yearMonthDay");	///�޷��� �� �̵��� �Ҷ� idx�� ����� �޾� �ð�
//					req.setAttribute("calendarMonthList",dao.getCalendarMonthList(userBean.getLectureId(), yearMonth));
//					req.setAttribute("calendarDayList",dao.getCalendarMonthList(userBean.getLectureId(), yearMonthDay));
					
					//main �����ϴ� ���� ����
					req.setAttribute("userBean", userBean);
					
					//main ���� �ϴ� ���� ����
					/// ajax�� ���� �ϰڴ�
					
					rd = req.getRequestDispatcher("student/main_S.jsp");
				} else if (path.equals("/attendance.stu")) {
					//���� ���� ����
					req.setAttribute("attendanceBean", dao.getAttendance(userBean.getUserId())); 	//bean�ȿ� ����, �Խ� ��� ����, �ð��� �ִ�
					req.setAttribute("userBean", userBean);
					
					//main ���� �ϴ� ���� ����
					req.setAttribute("attendanceDays", dao.getAttendanceDays(userBean.getUserId()));
					req.setAttribute("totalDays", dao.getTotalDays(userBean.getLectureId()));
					req.setAttribute("attendanceStatusList", dao.getAttendanceStatusList(userBean.getUserId()));
					
					rd = req.getRequestDispatcher("student/attendance_S.jsp");
					
				} else if (path.equals("/attendanceMonth.stu")) {
					String yearMonth = req.getParameter("idx");	///�޷��� �� �̵��� �Ҷ� idx�� ����� �޾� �ð�
					req.setAttribute("attendanceMonthList", dao.getAttendanceMonthList(userBean.getUserId(), yearMonth));
					rd = req.getRequestDispatcher("student/attendance_S_month.jsp");	//�̰� �߰��ؾ���
					
				} else if (path.equals("/score.stu")) {
					req.setAttribute("scoreBean", dao.getScoreBean(userBean.getUserId()));
					rd = req.getRequestDispatcher("student/score_S.jsp");
					
				} else if (path.equals("/assignment.stu")) {
					req.setAttribute("assignmentList", dao.getAssignmentList(userBean.getLectureId()));
					rd = req.getRequestDispatcher("student/assignment_S.jsp");
			
					
				} else if (path.equals("/assignment_detail.stu")) {
					int assignmentId = Integer.parseInt(req.getParameter("idx"));	//���ȭ�鿡�� ���� ��ȣ�� ������ ��
					req.setAttribute("assignmentBean", dao.getAssignment(assignmentId));
					System.out.println("dao.getAssignmentBean(assignmentId)");
					req.setAttribute("submissionBean", dao.getSubmission(assignmentId, userBean.getUserId())); //dto수정
					System.out.println("dao.getSubmissionBean(assignmentId, userBean.getUserId())");
					rd = req.getRequestDispatcher("student/assignment_S_detail.jsp");
					
				} else if (path.equals("/qna.stu")) {
					req.setAttribute("qnaList", dao.getQnaList(userBean.getUserId()));
					rd = req.getRequestDispatcher("student/qna_S.jsp");
				} else if (path.equals("/qna_detail.stu")) {
					String qnaId = req.getParameter("idx");	//���ȭ�鿡�� ���� ��ȣ�� ������ ��
					req.setAttribute("qnaBean", dao.getQnaBean(qnaId));
					rd = req.getRequestDispatcher("student/qna_S/qnadetail_S.jsp");
				} else {
					System.out.println("�������� �ʴ� ������");
				}
			}else {
				//teacher�� student�������� �����Ϸ��� �ϸ� �� ��������
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
		System.out.println("StudentController :: path = " + path);
		
		//���� ����
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
		
		//insert, edit, delete �� ��� ������ �����ϴ� result
		///��� ������� ���� ����
		int result;
		
		try {
			if(userBean.getBelong().equals("student")){
				StudentDao dao = new StudentDao();
				if (path.equals("/main.stu")) {
					
				}else if(path.equals("/submission_insert.stu")){
					int assignmentId = Integer.parseInt(req.getParameter("idx"));	
					result = dao.insertSubmission(assignmentId, userBean.getUserId());		
					rd = req.getRequestDispatcher("assignmentdetail.stu");	//���� ������ ȭ������ �̵�, //���� rd�� �̵��ؾ��ϳ�?
				}else if(path.equals("/submission_update.stu")){
					String assignmentId = req.getParameter("idx");	
					String fileName = req.getParameter("fileName");	//�������������� ������ ��
					result = dao.updateSubmission(assignmentId, userBean.getUserId() , fileName);		
					rd = req.getRequestDispatcher("assignmentdetail.stu");	//���� ������ ȭ������ �̵�, //���� rd�� �̵��ؾ��ϳ�?
				}else if(path.equals("/submission_delete.stu")){
					int assignmentId = Integer.parseInt(req.getParameter("idx"));	
					result = dao.deleteSubmission(assignmentId, userBean.getUserId());		
					rd = req.getRequestDispatcher("assignmentdetail.stu");	//���� ������ ȭ������ �̵�, //���� rd�� �̵��ؾ��ϳ�?
				}else if(path.equals("/qan_insert.stu")){
					QnaLDto qnaLBean = new QnaLDto();
					qnaLBean.setTitle(req.getParameter("title"));
					qnaLBean.setType(req.getParameter("type"));
					qnaLBean.setQuestionContent(req.getParameter("questionContent"));
					qnaLBean.setStuId(userBean.getUserId());					
					result = dao.insertQnaL(qnaLBean);		
					rd = req.getRequestDispatcher("assignmentdetail.stu");	//���� ������ ȭ������ �̵�, //���� rd�� �̵��ؾ��ϳ�?
				}else if(path.equals("/qan_update.stu")){
					int qnaId = Integer.parseInt(req.getParameter("qnaId"));
					String title = req.getParameter("title");
					String type = req.getParameter("type");	///�̰͵� �����׼� �ֳ�??
					String questionContent = req.getParameter("questionContent");
					result = dao.updateQnaL(qnaId ,title,type, questionContent);		
					rd = req.getRequestDispatcher("assignmentdetail.stu");	//���� ������ ȭ������ �̵�, //���� rd�� �̵��ؾ��ϳ�?
				}else if(path.equals("/qan_delete.stu")){
					String[] qnaId = req.getParameterValues("qnaId");
					
					result = dao.deleteQnaL(qnaId);		
					rd = req.getRequestDispatcher("qna.stu");	//qna ��� �������� �̵��̵��ؾ��ϳ�?
				}else {
					System.out.println("�������� �ʴ� ������");
				}
			}else {
				//teacher�� student�������� �����Ϸ��� �ϸ� �� ��������
				req.getRequestDispatcher("login.bit");
			}
			rd.forward(req, resp);
		}catch (java.lang.NullPointerException e) {
			resp.sendRedirect("login.bit");
		}
	}
}
