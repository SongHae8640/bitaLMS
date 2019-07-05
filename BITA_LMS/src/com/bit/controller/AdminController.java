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

		// ���� ����
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");

		try {
			// admin�� ���ٰ���
			if (userBean.getBelong().equals("admin")) {
				AdminDao dao = new AdminDao();

				if (path.equals("/main.adm")) {
					// ����������
					String month = req.getParameter("month");
					req.setAttribute("calendarList", dao.getMainCalendar(month));
					
					dao = new AdminDao();
					req.setAttribute("beanMain", dao.getMainUser(userBean.getUserId()));
					rd = req.getRequestDispatcher("admin/main_A.jsp");

				} else if (path.equals("/manage_lec.adm")) {
					// ���°��� ��� ������
					req.setAttribute("LectureList", dao.getLecture());

					rd = req.getRequestDispatcher("admin/manage_lec.jsp");
				} else if (path.equals("/manage_lec_detail.adm")) {
					// ���°��� �� ������
					int idx = Integer.parseInt(req.getParameter("idx"));
					req.setAttribute("LectureDetail", dao.detailLecture(idx));

					rd = req.getRequestDispatcher("admin/manage_lec_detail.jsp");
				} else if (path.equals("/manage_stu.adm")) {
					// ���������� ��� ������(��Ϻ�)
					req.setAttribute("ManageStuList", dao.getManageStu());
					
					rd = req.getRequestDispatcher("admin/manage_stu.jsp");

				} else if (path.equals("/manage_stu_month.adm")) {
					// ���������� ��� ������(����)
					req.setAttribute("ManageStuMonth", dao.getManageStuMonth());
					
					rd = req.getRequestDispatcher("admin/manage_stu_month.jsp");

				} else if (path.equals("/manage_tea.adm")) {
					// ������� ��� ������
					req.setAttribute("ManageTeaList", dao.getManageTea());
					
					rd = req.getRequestDispatcher("admin/manage_tea.jsp");

				} else if (path.equals("/manage_tea_detail.adm")) {
					// ������� �� ������
					//���� ������ �����ؾ� �ϱ� ������ user_id�� �ڷ����� String������ ����
					String userId = req.getParameter("idx");
					req.setAttribute("beanTea", dao.detailManageTea(userId));
					
					rd = req.getRequestDispatcher("admin/manage_tea_detail.jsp");
				} else if (path.equals("/qna.adm")) {
					// ť������ ��� ������
					req.setAttribute("QnaList", dao.getQnaList());
					
					rd = req.getRequestDispatcher("admin/qna_A.jsp");
				} else if (path.equals("/qna_detail.adm")) {
					// ť������ �� ������
					int idx = Integer.parseInt(req.getParameter("idx"));
					req.setAttribute("beanQna", dao.detailQnaList(idx));
					
					rd = req.getRequestDispatcher("admin/qna_A_detail.jsp");
				} else if (path.equals("/register.adm")) {
					// �л���� ���������
					
					// ��Ʈ����Ʈ�� �����ϰ� jsp���������� get���� �ҷ�����
					
					req.setAttribute("RegisterList", dao.getRegister());
					dao = new AdminDao();
					req.setAttribute("arrangeLecture", dao.arrangeLecture());
					rd = req.getRequestDispatcher("admin/register.jsp");

				} else if (path.equals("/register_detail.adm")) {
					// �л���� ��������
					int idx = Integer.parseInt(req.getParameter("idx"));

					req.setAttribute("DetailRegister", dao.detailRegister(idx));
					rd = req.getRequestDispatcher("admin/register_detail.jsp");
				} else {
					System.out.println("���������ʴ�������");
				}
			} else {
				// teacher�� student�������� �����Ϸ��� �ϸ� �� ��������
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
				
		
		try {
			//admin�� ���ٰ���
			if (userBean.getBelong().equals("admin")) {
				AdminDao dao = new AdminDao();
				if (path.equals("/manage_lec_update.adm")) {
					//���°��� ���� ������
//					LectureDto bean = (LectureDto) req.getParameterMap();
//					dao.updateLecture(bean);
					
					rd = req.getRequestDispatcher("admin/manage_lec_update.jsp");
				}else if (path.equals("/manage_lec_insert.adm")) {
					//���°��� ���� �߰� ������
					
					
					rd = req.getRequestDispatcher("admin/manage_lec_insert.jsp");
				} else if (path.equals("/manage_lec_delete.adm")) {
					//���°��� ���� ���� (������ ������ �ٷ� ������ ��������)
					int idx = Integer.parseInt(req.getParameter("idx"));
					int result = dao.deleteLecture(idx);
					
					rd = req.getRequestDispatcher("manage_lec_detail.adm");
				} else if (path.equals("/manage_stu_month_update.adm")) {
					// �л����� ��� ������(����) ����
					dao.getManageStuMonth();
					
					rd = req.getRequestDispatcher("manage_stu_month.adm");
				} else if (path.equals("/manage_tea_insert.adm")) {
					// ������� ���� �߰� ������
					rd = req.getRequestDispatcher("admin/manage_tea_insert.jsp");

				} else if (path.equals("/manage_tea_update.adm")) {
					// ������� ���� ���� ������
					rd = req.getRequestDispatcher("admin/manage_tea_update.jsp");

				} else if (path.equals("/manage_tea_insert.adm")) {
					// ������� ���� �߰� ������
					rd = req.getRequestDispatcher("admin/manage_tea_insert.jsp");

				} else if (path.equals("/qna_update.adm")) {
					// ť������ �亯��� ������
					rd = req.getRequestDispatcher("admin/qna_A_update.jsp");

				} else if (path.equals("/qna_delete.adm")) {
					// ť������ ���� ������
					
					rd = req.getRequestDispatcher("qna_detail.adm");
				} 
			}
		} catch (java.lang.NullPointerException e) {
			resp.sendRedirect("login.bit");
		}
	}
}