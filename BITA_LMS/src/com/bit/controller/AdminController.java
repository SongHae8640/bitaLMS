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

		// 세션 저장
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");

		try {
			// admin만 접근가능
			if (userBean.getBelong().equals("admin")) {
				AdminDao dao = new AdminDao();

				if (path.equals("/main.adm")) {
					// 메인페이지
					UserDto bean = (UserDto) session.getAttribute("userBean");
					ArrayList<CalendarDto> calendarList = dao.getMainCalendar();
					req.setAttribute("calendarList", calendarList);
					
					dao = new AdminDao();
					MainUserDto beanMain = dao.getMainUser(bean.getUserId());
					req.setAttribute("beanMain", beanMain);
					rd = req.getRequestDispatcher("admin/main_A.jsp");

				} else if (path.equals("/manage_lec.adm")) {
					// 강좌관리 목록 페이지
					ArrayList<LectureDto> LectureList = dao.getLecture();
					req.setAttribute("LectureList", LectureList);

					rd = req.getRequestDispatcher("admin/manage_lec.jsp");
				} else if (path.equals("/manage_lec_detail.adm")) {
					// 강좌관리 상세 페이지
					int idx = Integer.parseInt(req.getParameter("idx"));
					LectureDto LectureDetail = dao.detailLecture(idx);
					req.setAttribute("LectureDetail", LectureDetail);

					rd = req.getRequestDispatcher("admin/manage_lec_detail.jsp");
				} else if (path.equals("/manage_stu.adm")) {
					// 수강생관리 목록 페이지(목록별)
					ArrayList<AttendanceDto> ManageStuList = dao.getManageStu();
					req.setAttribute("ManageStuList", ManageStuList);
					
					rd = req.getRequestDispatcher("admin/manage_stu.jsp");

				} else if (path.equals("/manage_stu_month.adm")) {
					// 수강생관리 목록 페이지(월별)
					ArrayList<AttendanceDto> ManageStuMonth = dao.getManageStuMonth();
					req.setAttribute("ManageStuMonth", ManageStuMonth);
					
					rd = req.getRequestDispatcher("admin/manage_stu_month.jsp");

				} else if (path.equals("/manage_tea.adm")) {
					// 강사관리 목록 페이지
					ArrayList<TeacherDto> ManageTeaList = dao.getManageTea();
					req.setAttribute("ManageTeaList", ManageTeaList);
					
					rd = req.getRequestDispatcher("admin/manage_tea.jsp");

				} else if (path.equals("/manage_tea_detail.adm")) {
					// 강사관리 상세 페이지
					int idx = Integer.parseInt(req.getParameter("idx"));
					TeacherDto bean = dao.detailManageTea(idx);
					req.setAttribute("beanTea", bean);
					
					rd = req.getRequestDispatcher("admin/manage_tea_detail.jsp");
				} else if (path.equals("/qna.adm")) {
					// 큐엔에이 목록 페이지
					ArrayList<QnaLDto> QnaList = dao.getQnaList();
					req.setAttribute("QnaList", QnaList);
					
					rd = req.getRequestDispatcher("admin/qna_A.jsp");
				} else if (path.equals("/qna_detail.adm")) {
					// 큐엔에이 상세 페이지
					int idx = Integer.parseInt(req.getParameter("idx"));
					QnaLDto bean = dao.detailQnaList(idx);
					req.setAttribute("beanQna", bean);
					
					rd = req.getRequestDispatcher("admin/qna_A_detail.jsp");
				} else if (path.equals("/register.adm")) {
					// 학생등록 목록페이지
					ArrayList<LectureDto> arrangeLecture = dao.arrangeLecture();
					// 커넥션 새로 할당 (끊겼으니까)

					dao = new AdminDao();

					ArrayList<RegisterDto> RegisterList = dao.getRegister();
					// 어트리뷰트로 저장하고 jsp페이지에서 get으로 불러오기
					req.setAttribute("RegisterList", RegisterList);
					req.setAttribute("arrangeLecture", arrangeLecture);
					rd = req.getRequestDispatcher("admin/register.jsp");

				} else if (path.equals("/register_detail.adm")) {
					// 학생등록 상세페이지
					int idx = Integer.parseInt(req.getParameter("idx"));
					RegisterDto RegisterDetail = dao.detailRegister(idx);

					req.setAttribute("DetailRegister", RegisterDetail);
					rd = req.getRequestDispatcher("admin/register_detail.jsp");
				} else {
					System.out.println("존재하지않는페이지");
				}
			} else {
				// teacher나 student페이지로 접근하려고 하면 걍 보내버림
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
		
		//세션 저장
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
				
		
		try {
			//admin만 접근가능
			if (userBean.getBelong().equals("admin")) {
				AdminDao dao = new AdminDao();
				if (path.equals("/manage_lec_update.adm")) {
					//강좌관리 수정 페이지
//					LectureDto bean = (LectureDto) req.getParameterMap();
//					dao.updateLecture(bean);
					
					rd = req.getRequestDispatcher("admin/manage_lec_update.jsp");
				}else if (path.equals("/manage_lec_insert.adm")) {
					//강좌관리 강좌 추가 페이지
					
					
					rd = req.getRequestDispatcher("admin/manage_lec_insert.jsp");
				} else if (path.equals("/manage_lec_delete.adm")) {
					//강좌관리 강좌 삭제 (가상의 페이지 바로 돌릴거 딴곳으로)
					int idx = Integer.parseInt(req.getParameter("idx"));
					int result = dao.deleteLecture(idx);
					
					rd = req.getRequestDispatcher("manage_lec_detail.adm");
				} else if (path.equals("/manage_stu_month_update.adm")) {
					// 학생관리 목록 페이지(월별) 수정
					dao.getManageStuMonth();
					
					rd = req.getRequestDispatcher("manage_stu_month.adm");
				} else if (path.equals("/manage_tea_insert.adm")) {
					// 강사관리 강사 추가 페이지
					rd = req.getRequestDispatcher("admin/manage_tea_insert.jsp");

				} else if (path.equals("/manage_tea_update.adm")) {
					// 강사관리 강사 수정 페이지
					rd = req.getRequestDispatcher("admin/manage_tea_update.jsp");

				} else if (path.equals("/manage_tea_insert.adm")) {
					// 강사관리 강사 추가 페이지
					rd = req.getRequestDispatcher("admin/manage_tea_insert.jsp");

				} else if (path.equals("/qna_update.adm")) {
					// 큐엔에이 답변등록 페이지
					rd = req.getRequestDispatcher("admin/qna_A_update.jsp");

				} else if (path.equals("/qna_delete.adm")) {
					// 큐엔에이 삭제 페이지
					
					rd = req.getRequestDispatcher("qna_detail.adm");
				} 
			}
		} catch (java.lang.NullPointerException e) {
			resp.sendRedirect("login.bit");
		}
	}
}
