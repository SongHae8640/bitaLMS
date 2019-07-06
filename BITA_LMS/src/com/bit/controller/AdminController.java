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

import com.bit.model.AdminDao;
import com.bit.model.AttendanceDto;
import com.bit.model.CalendarDto;
import com.bit.model.LectureDto;
import com.bit.model.MainUserDto;
import com.bit.model.QnaLDto;
import com.bit.model.RegisterDto;
import com.bit.model.TeacherDao;
import com.bit.model.TeacherDto;
import com.bit.model.UserDto;

@WebServlet("*.adm")
public class AdminController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = null;

		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("AdminController :: path = " + path);

		// 세션 가져오기
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");

		try {
			// admin�� ��ٰ���
			if (userBean.getBelong().equals("admin")) {
				AdminDao dao = new AdminDao();

				if (path.equals("/main.adm")) {
					// 메인페이지
					String yearMonth = req.getParameter("yearMonth");
					req.setAttribute("calendarList", dao.getCalendarList(yearMonth));
					
					dao = new AdminDao();
					req.setAttribute("beanMain", dao.getUser(userBean.getUserId()));
					rd = req.getRequestDispatcher("admin/main_A.jsp");

				} else if (path.equals("/manage_lec.adm")) {
					// 강좌관리 목록 페이지
					req.setAttribute("LectureList", dao.getLectureList());

					rd = req.getRequestDispatcher("admin/manage_lec.jsp");
				} else if (path.equals("/manage_lec_detail.adm")) {
					// ���°� �� ������
					int lectureId = Integer.parseInt(req.getParameter("idx"));
					req.setAttribute("lectureBean", dao.getLecture(lectureId));

					rd = req.getRequestDispatcher("admin/manage_lec_detail.jsp");
				} else if (path.equals("/manage_stu.adm")) {
					// 수강생관리 목록 페이지(목록별)
					req.setAttribute("manageStuList", dao.getManageStu());
					
					rd = req.getRequestDispatcher("admin/manage_stu.jsp");

				} else if (path.equals("/manage_stu_month.adm")) {
					// 수강생관리 목록 페이지(월별)
					req.setAttribute("manageStuMonth", dao.getManageStuMonth());
					
					rd = req.getRequestDispatcher("admin/manage_stu_month.jsp");

				} else if (path.equals("/manage_tea.adm")) {
					// 강사관리 목록 페이지
					req.setAttribute("teacherList", dao.getTeacherList());
					
					rd = req.getRequestDispatcher("admin/manage_tea.jsp");

				} else if (path.equals("/manage_tea_detail.adm")) {
					// 강사관리 상세 페이지
					String userId = req.getParameter("idx");
					req.setAttribute("teacherBean", dao.getTeacher(userId));
					
					rd = req.getRequestDispatcher("admin/manage_tea_detail.jsp");
				} else if (path.equals("/qna.adm")) {
					// 큐엔에이 목록 페이지
					req.setAttribute("qnaLList", dao.getQnaLList());
					
					rd = req.getRequestDispatcher("admin/qna_A.jsp");
				} else if (path.equals("/qna_detail.adm")) {
					// ť������ �� ������
					int qnlLId = Integer.parseInt(req.getParameter("idx"));
					req.setAttribute("qnaLBean", dao.getQnaL(qnlLId));
					
					rd = req.getRequestDispatcher("admin/qna_A_detail.jsp");
				} else if (path.equals("/register.adm")) {
					// 학생등록 목록페이지
					
					// 어트리뷰트로 저장하고 jsp페이지에서 get으로 불러오기
					
					req.setAttribute("registerList", dao.getRegisterList());
					dao = new AdminDao();
					req.setAttribute("arrangeLectureList", dao.getArrangeLectureList());
					rd = req.getRequestDispatcher("admin/register.jsp");

				} else if (path.equals("/register_detail.adm")) {
					// �л��� ��������
					int applyId = Integer.parseInt(req.getParameter("idx"));

					req.setAttribute("registerBea", dao.getRegister(applyId));
					rd = req.getRequestDispatcher("admin/register_detail.jsp");
				} else {
					System.out.println("�������ʴ�������");
				}
			} else {
				// teacher�� student������� ����Ϸ�� �ϸ� �� ������
				req.getRequestDispatcher("login.bit");
			}
			rd.forward(req, resp);
		} catch (java.lang.NullPointerException e) {
			resp.sendRedirect("login.bit");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = null;

		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("AdminController :: path = " + path);
		
		//���� ����
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
			
		int result;
		
		try {
			//admin�� ��ٰ���
			if (userBean.getBelong().equals("admin")) {
				AdminDao dao = new AdminDao();
				if (path.equals("/manage_lec_update.adm")) {
					//���°� ��� ������
//					LectureDto bean = (LectureDto) req.getParameterMap(); ??
//					dao.updateLecture(bean);
					
					rd = req.getRequestDispatcher("admin/manage_lec_update.jsp");
				}else if (path.equals("/manage_lec_insert.adm")) {
					//강좌 생성
					
//					LectureDto lectureBean = (LectureDto) req.getParameter("idx");
//					result = dao.insertLecture(lectureBean);
					
					rd = req.getRequestDispatcher("admin/manage_lec_insert.jsp");
				} else if (path.equals("/manage_lec_delete.adm")) {
					//���°� ���� ��� (������ ������ �ٷ� ������ ������)
					int idx = Integer.parseInt(req.getParameter("idx"));
					result = dao.deleteLecture(idx);
					
					rd = req.getRequestDispatcher("manage_lec_detail.adm");
				} else if (path.equals("/manage_stu_month_update.adm")) {
					// �л�� ��� ������(��) ���
					dao.getManageStuMonth();
					
					rd = req.getRequestDispatcher("manage_stu_month.adm");
				} else if (path.equals("/manage_tea_insert.adm")) {
					// ����� ���� �߰� ������
					rd = req.getRequestDispatcher("admin/manage_tea_insert.jsp");

				} else if (path.equals("/manage_tea_update.adm")) {
					// ����� ���� ��� ������
					rd = req.getRequestDispatcher("admin/manage_tea_update.jsp");

				} else if (path.equals("/manage_tea_insert.adm")) {
					// ����� ���� �߰� ������
					rd = req.getRequestDispatcher("admin/manage_tea_insert.jsp");

				} else if (path.equals("/qna_update.adm")) {
					// ť������ �亯��� ������
					rd = req.getRequestDispatcher("admin/qna_A_update.jsp");

				} else if (path.equals("/qna_delete.adm")) {
					// ť������ ��� ������
					
					rd = req.getRequestDispatcher("qna_detail.adm");
				} 
			}
		} catch (java.lang.NullPointerException e) {
			resp.sendRedirect("login.bit");
		}
	}
}