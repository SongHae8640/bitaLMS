package com.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

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
					//년월일 하나만 받아와서
					String yearMonthDay = req.getParameter("yearMonthDay");
					req.setAttribute("calendarMonthList", dao.getCalendarMonthList(yearMonthDay.substring(0, 6)));
					//년월일 다 넣어주기
					dao = new AdminDao();
					req.setAttribute("calendarDayList", dao.getCalendarDayList(yearMonthDay));
					dao = new AdminDao();
					dao = new AdminDao();
					req.setAttribute("userBean", dao.getUser(userBean.getUserId()));
					rd = req.getRequestDispatcher("admin/main_A.jsp");

				} else if (path.equals("/manage_lec.adm")) {
					// 강좌관리 목록 페이지
					req.setAttribute("LectureList", dao.getLectureList());

					rd = req.getRequestDispatcher("admin/manage_lec.jsp");
				} else if (path.equals("/manage_lec_detail.adm")) {
					// 강좌관리 상세 페이지
					int lectureId = Integer.parseInt(req.getParameter("idx"));
					req.setAttribute("lectureBean", dao.getLecture(lectureId));

					rd = req.getRequestDispatcher("admin/manage_lec_detail.jsp");
				} else if (path.equals("/manage_stu.adm")) {
					// 수강생관리 목록 페이지(목록별)
					//userDto이용
					req.setAttribute("userBean", dao.getManageStu());
					
					rd = req.getRequestDispatcher("admin/manage_stu.jsp");

				} else if (path.equals("/manage_stu_month.adm")) {
					// 수강생관리 목록 페이지(월별)
					String yearMonth = req.getParameter("yearMonth");
					req.setAttribute("manageStuMonth", dao.getManageStuMonth(yearMonth));
					
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
					// 큐엔에이 상세 페이지
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
					// 학생등록 상세페이지
					int applyId = Integer.parseInt(req.getParameter("idx"));

					req.setAttribute("registerBean", dao.getRegister(applyId));
					rd = req.getRequestDispatcher("admin/register_detail.jsp");
				}  else if (path.equals("/manage_lec_update.adm")) {
					//강좌관리 수정 페이지
					
					rd = req.getRequestDispatcher("admin/manage_lec_update.jsp");
				}else if (path.equals("/manage_lec_insert.adm")) {
					//강좌관리 입력 페이지
					
					rd = req.getRequestDispatcher("admin/manage_lec_insert.jsp");
				} else if (path.equals("/manage_tea_insert.adm")) {
					// 강사관리 강사 추가 페이지
					rd = req.getRequestDispatcher("admin/manage_tea_insert.jsp");

				} else if (path.equals("/manage_tea_update.adm")) {
					// 강사관리 강사 수정 페이지
					rd = req.getRequestDispatcher("admin/manage_tea_update.jsp");

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
		
		int result = -1,idx = -1;

		try {
			//admin만 접근가능
			if (userBean.getBelong().equals("admin")) {
				AdminDao dao = new AdminDao();
				if (path.equals("/manage_lec_update.adm")) {
					//강좌관리 수정 페이지
					//커리큘럼이미지,강좌명,강사명,교육기간,교육수준,최대인원,강좌내용,첨부파일을 수정가능
					LectureDto lectureBean = new LectureDto();
					lectureBean.setContent(req.getParameter(""));
					lectureBean.setEndDate(req.getParameter(""));
					lectureBean.setFileName(req.getParameter(""));
					lectureBean.setContent(req.getParameter(""));
					lectureBean.setLectureID(Integer.parseInt(req.getParameter(""))); //구분자로서의 역할
					lectureBean.setName(req.getParameter(""));
					lectureBean.setLv(Integer.parseInt(req.getParameter("")));
					lectureBean.setTeaName(req.getParameter(""));
					lectureBean.setStartDate(req.getParameter(""));
					lectureBean.setMaxStd(Integer.parseInt(req.getParameter("")));
					
					//결과값에 따라 성공/실패 구분
					result = dao.updateLecture(lectureBean);
					
					idx = lectureBean.getLectureID();
					rd = req.getRequestDispatcher("manage_lec_detail.adm?idx="+idx);
					
				}else if (path.equals("/manage_lec_insert.adm")) {
					//강좌관리 입력 페이지
					LectureDto lectureBean = new LectureDto();
					lectureBean.setContent(req.getParameter(""));
					lectureBean.setEndDate(req.getParameter(""));
					lectureBean.setFileName(req.getParameter(""));
					lectureBean.setContent(req.getParameter(""));
					lectureBean.setLectureID(Integer.parseInt(req.getParameter(""))); //구분자로서의 역할
					lectureBean.setName(req.getParameter(""));
					lectureBean.setLv(Integer.parseInt(req.getParameter("")));
					lectureBean.setTeaName(req.getParameter(""));
					lectureBean.setStartDate(req.getParameter(""));
					lectureBean.setMaxStd(Integer.parseInt(req.getParameter("")));
					
					idx = dao.insertLecture(lectureBean);
					rd = req.getRequestDispatcher("manage_lec_detail.adm?idx="+idx);
					
					rd = req.getRequestDispatcher("admin/manage_lec_insert.jsp");
				} else if (path.equals("/manage_lec_delete.adm")) {
					//강좌관리 강좌 삭제 (가상의 페이지 바로 돌릴거 딴곳으로)
					idx = Integer.parseInt(req.getParameter("idx"));
					result = dao.deleteLecture(idx); //나중에 쓸 결과값
					
					rd = req.getRequestDispatcher("manage_lec.adm");
				} else if (path.equals("/manage_stu_month_update.adm")) {
					// 수강생관리 목록 페이지(월별) 수정
					String yyyymm = req.getParameter("");
					String[] userId = req.getParameterValues("");
					String[] status = req.getParameterValues("");
					result = dao.updateManageStuMonth(yyyymm,userId,status);
					
					rd = req.getRequestDispatcher("manage_stu_month.adm?idx="+idx);
				} else if (path.equals("/manage_stu_month_update.adm")) {
					// 수강생관리 목록 페이지 삭제
					String[] userId = req.getParameterValues("");
					result = dao.deleteUser(userId);
					
					rd = req.getRequestDispatcher("manage_stu.adm");
				} else if (path.equals("/manage_tea_insert.adm")) {
					// 강사관리 강사 추가 페이지
					TeacherDto teacherBean = new TeacherDto();
//					teacherBean.set어쩌구
					result = dao.insertTeacher(teacherBean);
					
					rd = req.getRequestDispatcher("manage_tea_detail.adm?idx="+idx);

				} else if (path.equals("/manage_tea_update.adm")) {
					// 강사관리 강사 수정 페이지
					TeacherDto teacherBean = new TeacherDto();
//					teacherBean.set어쩌구
					result = dao.updateTeacher(teacherBean);
					
					rd = req.getRequestDispatcher("manage_tea_detail.adm?idx="+idx);

				} else if (path.equals("/manage_tea_delete.adm")) {
					// 강사관리 강사 삭제 페이지
					String[] userId = req.getParameterValues("");
					result = dao.deleteUser(userId);
					rd = req.getRequestDispatcher("manage_tea.adm");

				} else if (path.equals("/qna_update.adm")) {
					// 큐엔에이 답변등록 페이지
					String answerContent = req.getParameter("");
					QnaLDto qnaLBean = new QnaLDto();
					//bean에 값넣기
					
					result = dao.updateQnaL(qnaLBean);
					idx = qnaLBean.getQnaLId();
					rd = req.getRequestDispatcher("qna_detail.adm?idx="+idx);

				} else if (path.equals("/qna_delete.adm")) {
					// 큐엔에이 삭제 페이지
					int[] qnaLIdList = Arrays.stream(req.getParameterValues("")).mapToInt(Integer::parseInt).toArray();
					result = dao.deleteQnaL(qnaLIdList);
					rd = req.getRequestDispatcher("qna.adm");
					
				}
				
				if(result==1){					
					rd.forward(req, resp);
				}else{
					
				}
			}
		} catch (java.lang.NullPointerException e) {
			resp.sendRedirect("login.bit");
		}
	}
}