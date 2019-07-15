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
					
					rd = req.getRequestDispatcher("student/main_S.jsp");
				} else if (path.equals("/attendance.stu")) {
					
					rd = req.getRequestDispatcher("student/attendance_day_S.jsp");
					
				} else if (path.equals("/attendanceMonth.stu")) {
					String yearMonth = req.getParameter("idx");	///�޷��� �� �̵��� �Ҷ� idx�� ����� �޾� �ð�
					req.setAttribute("attendanceMonthList", dao.getAttendanceMonthList(userBean.getUserId(), yearMonth));
					rd = req.getRequestDispatcher("student/attendance_month_S.jsp");	//�̰� �߰��ؾ���
					
				} else if (path.equals("/score.stu")) {
					req.setAttribute("scoreBean", dao.getScore(userBean.getUserId()));
					rd = req.getRequestDispatcher("student/score_S.jsp");
					
				} else if (path.equals("/assignment.stu")) {
					req.setAttribute("assignmentList", dao.getAssignmentList(userBean.getLectureId()));
					rd = req.getRequestDispatcher("student/assignment_S.jsp");
					
				} else if (path.equals("/assignmentdetail.stu")) {
					String assignmentId = req.getParameter("idx");	//���ȭ�鿡�� ���� ��ȣ�� ������ ��
					//req.setAttribute("assignmentBean", dao.getAssignment(assignmentId));
					//req.setAttribute("submissionBean", dao.getSubmission(assignmentId, userBean.getUserId()));
					
					rd = req.getRequestDispatcher("student/assignment_S/assignmentdetail_S.jsp");
					
				} else if (path.equals("/qna.stu")) {
					req.setAttribute("qnaList", dao.getQnaList(userBean.getUserId()));
					rd = req.getRequestDispatcher("student/qna_S.jsp");
				} else if (path.equals("/qna_detail.stu")) {
					String qnaId = req.getParameter("idx");	//���ȭ�鿡�� ���� ��ȣ�� ������ ��
					req.setAttribute("qnaBean", dao.getQna(qnaId));
					rd = req.getRequestDispatcher("student/qna_S/qnadetail_S.jsp");
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
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = null;

		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("StudentController(post) :: path = " + path);
		
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
				}
				
				if(rd!=null){
					rd.forward(req, resp);				
				}
				
				String json = "";
				if(path.equals("/callAttendance.stu")){
					resp.setContentType("text/html;charset=UTF-8"); 
					
					System.out.println("post");
					
					String id = req.getParameter("id");

					//Student �⼮��Ȳ�� �ʿ��� ���� ��������
					
					//�Խ�/����/��� ���� �� status, ����ǽð�(where����,�úи� ��������),���¸�,���±Ⱓ
					AttendanceDto AttendanceBean = dao.getAttendance(userBean.getUserId());
					//�Խ� Ƚ�� / ���� Ƚ��/ ����Ƚ�� / ����Ƚ�� / �Ἦ Ƚ�� groupby status
					int[] statusNum = dao.getAttendanceStatusList(userBean.getUserId());
					
					//�����Ȳ (���⼮��, �Ѽ�����), userbean���ִ� id �̿�
					int attendanceDays = dao.getAttendanceDays(userBean.getUserId());
					//userbean�� ����ִ� lectureId
					int totalDays= dao.getTotalDays(userBean.getLectureId());
					
					//�⼮ ���� ���� ���� �Ἦ
					json = "{\"status\" : \""+AttendanceBean.getStatus()+"\" , \"name\" : \""+userBean.getName()+"\" ,"
							+ " \"checkinTime\" : \""+AttendanceBean.getCheckinTime()+"\", \"checkoutTime\" : \""+AttendanceBean.getCheckoutTime()+"\","
							+ " \"lecName\" : \""+userBean.getLectureName()+"\", \"startDate\" : \""+userBean.getStartDate()+"\", \"endDate\" : \""+userBean.getEndDate()+"\""
							+ " ,\"attendanceDays\" : \""+attendanceDays+"\", \"totalDays\" : \""+totalDays+"\""
							+ " ,\"�⼮\" : \""+statusNum[0]+"\", \"����\" : \""+statusNum[1]+"\", \"����\" : \""+statusNum[2]+"\", \"����\" : \""+statusNum[3]+"\", \"�Ἦ\" : \""+statusNum[4]+"\"}";
					//�Խ�, ��� ��ư�� ������ ���� �⼮�Է��ϰ� �⼮��Ȳ ���� ����
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
						System.out.println("����");
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
					System.out.println("�������� �ʴ� ������");
				}
				
			}else {
				//teacher�� student�������� �����Ϸ��� �ϸ� �� ��������
				req.getRequestDispatcher("login.bit");
			}
			
		}catch (java.lang.NullPointerException e) {
			e.printStackTrace();
			resp.sendRedirect("login.bit");
		}
	}
}
