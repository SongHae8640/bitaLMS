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

		// �꽭�뀡 媛��졇�삤湲�
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");

		try {
			// admin占쏙옙 占쏙옙鳴占쏙옙占�
			if (userBean.getBelong().equals("admin")) {
				AdminDao dao = new AdminDao();

				if (path.equals("/main.adm")) {
					// 硫붿씤�럹�씠吏�
					String yearMonth = req.getParameter("yearMonth");
					String yearMonthDay = req.getParameter("yearMonthDay");
					req.setAttribute("calendarMonthList", dao.getCalendarMonthList(yearMonth));
					req.setAttribute("calendarDayList", dao.getCalendarDayList(yearMonthDay));
					dao = new AdminDao();
					req.setAttribute("beanMain", dao.getUser(userBean.getUserId()));
					rd = req.getRequestDispatcher("admin/main_A.jsp");

				} else if (path.equals("/manage_lec.adm")) {
					// 媛뺤쥖愿�由� 紐⑸줉 �럹�씠吏�
					req.setAttribute("LectureList", dao.getLectureList());

					rd = req.getRequestDispatcher("admin/manage_lec.jsp");
				} else if (path.equals("/manage_lec_detail.adm")) {
					// 占쏙옙占승곤옙 占쏙옙 占쏙옙占쏙옙占쏙옙
					int lectureId = Integer.parseInt(req.getParameter("idx"));
					req.setAttribute("lectureBean", dao.getLecture(lectureId));

					rd = req.getRequestDispatcher("admin/manage_lec_detail.jsp");
				} else if (path.equals("/manage_stu.adm")) {
					// �닔媛뺤깮愿�由� 紐⑸줉 �럹�씠吏�(紐⑸줉蹂�)
					req.setAttribute("manageStuList", dao.getManageStu());
					
					rd = req.getRequestDispatcher("admin/manage_stu.jsp");

				} else if (path.equals("/manage_stu_month.adm")) {
					// �닔媛뺤깮愿�由� 紐⑸줉 �럹�씠吏�(�썡蹂�)
					req.setAttribute("manageStuMonth", dao.getManageStuMonth());
					
					rd = req.getRequestDispatcher("admin/manage_stu_month.jsp");

				} else if (path.equals("/manage_tea.adm")) {
					// 媛뺤궗愿�由� 紐⑸줉 �럹�씠吏�
					req.setAttribute("teacherList", dao.getTeacherList());
					
					rd = req.getRequestDispatcher("admin/manage_tea.jsp");

				} else if (path.equals("/manage_tea_detail.adm")) {
					// 媛뺤궗愿�由� �긽�꽭 �럹�씠吏�
					String userId = req.getParameter("idx");
					req.setAttribute("teacherBean", dao.getTeacher(userId));
					
					rd = req.getRequestDispatcher("admin/manage_tea_detail.jsp");
				} else if (path.equals("/qna.adm")) {
					// �걧�뿏�뿉�씠 紐⑸줉 �럹�씠吏�
					req.setAttribute("qnaLList", dao.getQnaLList());
					
					rd = req.getRequestDispatcher("admin/qna_A.jsp");
				} else if (path.equals("/qna_detail.adm")) {
					// 큐占쏙옙占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙占쏙옙
					int qnlLId = Integer.parseInt(req.getParameter("idx"));
					req.setAttribute("qnaLBean", dao.getQnaL(qnlLId));
					
					rd = req.getRequestDispatcher("admin/qna_A_detail.jsp");
				} else if (path.equals("/register.adm")) {
					// �븰�깮�벑濡� 紐⑸줉�럹�씠吏�
					
					// �뼱�듃由щ럭�듃濡� ���옣�븯怨� jsp�럹�씠吏��뿉�꽌 get�쑝濡� 遺덈윭�삤湲�
					
					req.setAttribute("registerList", dao.getRegisterList());
					dao = new AdminDao();
					req.setAttribute("arrangeLectureList", dao.getArrangeLectureList());
					rd = req.getRequestDispatcher("admin/register.jsp");

				} else if (path.equals("/register_detail.adm")) {
					// 占싻삼옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙
					int applyId = Integer.parseInt(req.getParameter("idx"));

					req.setAttribute("registerBea", dao.getRegister(applyId));
					rd = req.getRequestDispatcher("admin/register_detail.jsp");
				} else {
					System.out.println("占쏙옙占쏙옙占쏙옙占십댐옙占쏙옙占쏙옙占쏙옙");
				}
			} else {
				// teacher占쏙옙 student占쏙옙占쏙옙占쏙옙占� 占쏙옙占쏙옙狗占쏙옙 占싹몌옙 占쏙옙 占쏙옙占쏙옙占쏙옙
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
		
		//占쏙옙占쏙옙 占쏙옙占쏙옙
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
			
		int result;
		
		try {
			//admin占쏙옙 占쏙옙鳴占쏙옙占�
			if (userBean.getBelong().equals("admin")) {
				AdminDao dao = new AdminDao();
				if (path.equals("/manage_lec_update.adm")) {
					//占쏙옙占승곤옙 占쏙옙占� 占쏙옙占쏙옙占쏙옙
//					LectureDto bean = (LectureDto) req.getParameterMap(); ??
//					dao.updateLecture(bean);
					
					rd = req.getRequestDispatcher("admin/manage_lec_update.jsp");
				}else if (path.equals("/manage_lec_insert.adm")) {
					//媛뺤쥖 �깮�꽦
					
//					LectureDto lectureBean = (LectureDto) req.getParameter("idx");
//					result = dao.insertLecture(lectureBean);
					
					rd = req.getRequestDispatcher("admin/manage_lec_insert.jsp");
				} else if (path.equals("/manage_lec_delete.adm")) {
					//占쏙옙占승곤옙 占쏙옙占쏙옙 占쏙옙占� (占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쌕뤄옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙)
					int idx = Integer.parseInt(req.getParameter("idx"));
					result = dao.deleteLecture(idx);
					
					rd = req.getRequestDispatcher("manage_lec_detail.adm");
				} else if (path.equals("/manage_stu_month_update.adm")) {
					// 占싻삼옙占� 占쏙옙占� 占쏙옙占쏙옙占쏙옙(占쏙옙) 占쏙옙占�
					dao.getManageStuMonth();
					
					rd = req.getRequestDispatcher("manage_stu_month.adm");
				} else if (path.equals("/manage_tea_insert.adm")) {
					// 占쏙옙占쏙옙占� 占쏙옙占쏙옙 占쌩곤옙 占쏙옙占쏙옙占쏙옙
					rd = req.getRequestDispatcher("admin/manage_tea_insert.jsp");

				} else if (path.equals("/manage_tea_update.adm")) {
					// 占쏙옙占쏙옙占� 占쏙옙占쏙옙 占쏙옙占� 占쏙옙占쏙옙占쏙옙
					rd = req.getRequestDispatcher("admin/manage_tea_update.jsp");

				} else if (path.equals("/manage_tea_insert.adm")) {
					// 占쏙옙占쏙옙占� 占쏙옙占쏙옙 占쌩곤옙 占쏙옙占쏙옙占쏙옙
					rd = req.getRequestDispatcher("admin/manage_tea_insert.jsp");

				} else if (path.equals("/qna_update.adm")) {
					// 큐占쏙옙占쏙옙占쏙옙 占썰변占쏙옙占� 占쏙옙占쏙옙占쏙옙
					rd = req.getRequestDispatcher("admin/qna_A_update.jsp");

				} else if (path.equals("/qna_delete.adm")) {
					// 큐占쏙옙占쏙옙占쏙옙 占쏙옙占� 占쏙옙占쏙옙占쏙옙
					
					rd = req.getRequestDispatcher("qna_detail.adm");
				} else if(path.equals("/attendanceAll_insert.adm")) {
					ArrayList<UserDto> stuList = dao.getStudentList();
					result = dao.insertAttendanceAll(stuList);
							
				}
			}
		} catch (java.lang.NullPointerException e) {
			resp.sendRedirect("login.bit");
		}
	}
}