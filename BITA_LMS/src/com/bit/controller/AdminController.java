package com.bit.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;



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
	String json = "";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = null;

		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("AdminController(doGet) :: path = " + path);

		// 세션 저장
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
		//System.out.println("AdminController(doGet) :: userBean="+userBean.toString());
	

		try {
			//로그인이 안된 접속자 일때( session에 userBean이 없는경우
			if(userBean == null){
				resp.sendRedirect("login.bit");
			}
			
			// admin만 접근가능
			if (userBean.getBelong().equals("admin")) {
				AdminDao dao = new AdminDao();
				session.setAttribute("arrangeLectureList", dao.getArrangeLectureList());
				if (path.equals("/main.adm")) {
					// 메인페이지
					rd = req.getRequestDispatcher("admin/main_A.jsp");
				}else if (path.equals("/manage_lec.adm")) {
					// 강좌관리 목록 페이지
					req.setAttribute("LectureList", dao.getLectureList());
					rd = req.getRequestDispatcher("admin/manage_lec.jsp");
					rd.forward(req, resp);
				} else if (path.equals("/manage_lec_detail.adm")) {
					// 강좌관리 상세 페이지
					int lectureId = Integer.parseInt(req.getParameter("idx"));
					req.setAttribute("lectureBean", dao.getLecture(lectureId));
					rd = req.getRequestDispatcher("admin/manage_lec_detail.jsp");
					rd.forward(req, resp);
				} else if (path.equals("/manage_stu.adm")) {
					// 수강생관리 목록 페이지(목록별)
					//콤보박스 정보
					req.setAttribute("arrangeLectureList", dao.getArrangeLectureList());
					//학생목록 리스트정보
					//userDto이용
					req.setAttribute("userBean", dao.getManageStu());
					rd = req.getRequestDispatcher("admin/manage_stu.jsp");
				} else if (path.equals("/manage_stu_month.adm")) {
					// 수강생관리 목록 페이지(월별)
					//콤보박스 정보
					req.setAttribute("arrangeLectureList", dao.getArrangeLectureList());
					dao = new AdminDao();
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
					rd.forward(req, resp);
				}  else if (path.equals("/manage_lec_update.adm")) {
					//강좌관리 수정 페이지	
					rd = req.getRequestDispatcher("admin/manage_lec_update.jsp");
					rd.forward(req, resp);
				}else if (path.equals("/manage_lec_insert.adm")) {
					//강좌관리 입력 페이지
					rd = req.getRequestDispatcher("admin/manage_lec_insert.jsp");
					rd.forward(req, resp);
				} else if (path.equals("/manage_tea_insert.adm")) {
					// 강사관리 강사 추가 페이지
					rd = req.getRequestDispatcher("admin/manage_tea_insert.jsp");
				} else if (path.equals("/manage_tea_update.adm")) {
					// 강사관리 강사 수정 페이지
					rd = req.getRequestDispatcher("admin/manage_tea_update.jsp");
					
				} else if (path.equals("/qna.adm")) {
					// 큐엔에이 목록 페이지
					req.setAttribute("qnaLList", dao.getQnaLList());
					rd = req.getRequestDispatcher("admin/qna_A.jsp");
				} else if (path.equals("/qna_detail.adm")) {
					// 큐엔에이 상세 페이지
					int qnlLId = Integer.parseInt(req.getParameter("idx"));
					req.setAttribute("qnaLBean", dao.getQnaL(qnlLId));
					rd = req.getRequestDispatcher("admin/qna_A_detail.jsp");
				}
				
				
				//ajax 방식 ( rd를 사요하지 않음)
				else if(path.equals("/callCalendar.adm")) {
					//json으로 보낼때 한글 깨짐 방지
					resp.setContentType("text/html;charset=UTF-8");
					
					JSONArray calendarMonthListJson = dao.getCalendarMonthListJson();
					PrintWriter out = resp.getWriter();
					out.write(calendarMonthListJson.toJSONString());
					out.close();
				}else {
					System.out.println("존재하지않는페이지");
				}	
			} else {
				// teacher나 student페이지로 접근하려고 하면 걍 보내버림
				resp.sendRedirect("login.bit");
			}
			if(rd !=null) {
				rd.forward(req, resp);
			}
		} catch (java.lang.NullPointerException e) {
			System.out.println(e);
			resp.sendRedirect("login.bit");
		}catch (java.lang.IllegalStateException e) {
			System.out.println(e);
			resp.sendRedirect("login.bit");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = null;

		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("AdminController(doPost) :: path = " + path);
		
		//세션 저장
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
		
		int result = -1,idx = -1;
		
		resp.setContentType("text/html;charset=UTF-8");

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
//					lectureBean.setLectureId(Integer.parseInt(req.getParameter(""))); //구분자로서의 역할
					lectureBean.setName(req.getParameter(""));
					lectureBean.setLv(Integer.parseInt(req.getParameter("")));
					lectureBean.setTeaName(req.getParameter(""));
					lectureBean.setStartDate(req.getParameter(""));
					lectureBean.setMaxStd(Integer.parseInt(req.getParameter("")));
					
					//결과값에 따라 성공/실패 구분
					result = dao.updateLecture(lectureBean);
					
//					idx = lectureBean.getLectureId();
					rd = req.getRequestDispatcher("manage_lec_detail.adm?idx="+idx);
					
				}else if (path.equals("/manage_lec_insert.adm")) {
					//강좌관리 입력 페이지
					LectureDto lectureBean = new LectureDto();
					lectureBean.setContent(req.getParameter(""));
					lectureBean.setEndDate(req.getParameter(""));
					lectureBean.setFileName(req.getParameter(""));
					lectureBean.setContent(req.getParameter(""));
//					lectureBean.setLectureId(Integer.parseInt(req.getParameter(""))); //구분자로서의 역할
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
					// 수강생관리 목록 페이지 삭제
					String[] userId = req.getParameterValues("");
					result = dao.deleteUser(userId);
					
					rd = req.getRequestDispatcher("manage_stu.adm");
				}else if (path.equals("/manage_tea_insert.adm")) {
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

				} else if (path.equals("/register_update.adm")) {
					// 학생등록 
					String id = req.getParameter("id");
					String lecName = req.getParameter("lecName");
					System.out.println(id+":"+lecName);
					if(id!=null&&lecName!=null){
						result = dao.updateRegister(id,lecName);
					}
					resp.sendRedirect("register.adm");
				}
				
				
				
				
				//비동기 통신
				else if (path.equals("/manage_stu_month.adm")) {
					req.setAttribute("arrangeLectureList", dao.getArrangeLectureList());
					// 수강생관리 목록 페이지(월별) 월 이동
					String yyyymm = req.getParameter("yearMonth");
					System.out.println(yyyymm);
					ArrayList<AttendanceDto> manageStuMonth = dao.getManageStuMonth(yyyymm);
					PrintWriter out= resp.getWriter(); 
					out.write(dao.getManageStuMonthJson(manageStuMonth)+"");
					out.close();
				} else if (path.equals("/manage_stu_month_update.adm")) {
					// 수강생관리 목록 페이지(월별) 수정
//					String yearMonth
					String yyyymm = req.getParameter("nowDay");
					String[] userId = req.getParameterValues("arrId");
					String[] status = req.getParameterValues("arrStatus");					
					System.out.println(yyyymm);
					result = dao.updateManageStuMonth(yyyymm,userId,status);
					if(result>0){
						PrintWriter out= resp.getWriter(); 
						out.write("{\"msg\":\"성공적으로 수정되었습니다\"}");
						out.close();
					}
				}// calendar insert 페이지
				else if (path.equals("/calendar_insert.adm")) {
					CalendarDto calendarBean = new CalendarDto();
					calendarBean.setLectureId(Integer.parseInt(req.getParameter("lectureId")));	//강사기준!! 행정에서는 따로 파라미터 가져와서 설정하게 해야함
					calendarBean.setStartDate(req.getParameter("startDate")+" 00:00:00");
					calendarBean.setEndDate(req.getParameter("endDate")+" 23:59:00");
					calendarBean.setTitle(req.getParameter("title"));
					calendarBean.setContent(req.getParameter("content"));
					System.out.println(calendarBean.toString());
					result = dao.insertCalendar(calendarBean);
					System.out.println("result = "+result);
				}else if(path.equals("/calendar_updateDrag.adm")){
					CalendarDto calendarBean = new CalendarDto();
					calendarBean.setCalendarId(Integer.parseInt(req.getParameter("calendarId")));
					calendarBean.setTitle(req.getParameter("title"));
					calendarBean.setLectureId(Integer.parseInt(req.getParameter("lectureId")));
					calendarBean.setLectureName(req.getParameter("lectureName"));	///update에서는 빼도 되는 부분. 정보 이동 확을을 위해 추가함
					calendarBean.setStartDate(req.getParameter("startDate").replaceAll("T", " "));
					calendarBean.setEndDate(req.getParameter("endDate").replaceAll("T", " "));
					calendarBean.setContent(req.getParameter("content"));
					System.out.println(calendarBean.toString());
					result = dao.updateCalendar(calendarBean);
					System.out.println("result = "+result);
				}else if(path.equals("/calendar_updateEdit.adm")){
					CalendarDto calendarBean = new CalendarDto();
					calendarBean.setCalendarId(Integer.parseInt(req.getParameter("calendarId")));
					calendarBean.setTitle(req.getParameter("title"));
					calendarBean.setLectureId(Integer.parseInt(req.getParameter("lectureId")));
					calendarBean.setStartDate(req.getParameter("startDate")+" 00:00:00");
					calendarBean.setEndDate(req.getParameter("endDate")+" 23:59:00");
					calendarBean.setContent(req.getParameter("content"));
					System.out.println(calendarBean.toString());
					result = dao.updateCalendar(calendarBean);
					System.out.println("result = "+result);
				}else if(path.equals("/calendar_delete.adm")){
					result = dao.deleteCalendar(Integer.parseInt(req.getParameter("calendarId")));
					System.out.println("result = "+result);
				} else if (path.equals("/register_update.adm")) {
					// 학생등록 
					String id = req.getParameter("id");
					String lecName = req.getParameter("lecName");
					System.out.println(id+":"+lecName);
					if(id!=null&&lecName!=null){
						result = dao.updateRegister(id,lecName);
					}
					resp.sendRedirect("register.adm");
				}

				//비동기 통신
				else if (path.equals("/manage_stu_month.adm")) {
					req.setAttribute("arrangeLectureList", dao.getArrangeLectureList());
					// 수강생관리 목록 페이지(월별) 월 이동
					String yyyymm = req.getParameter("yearMonth");
					System.out.println(yyyymm);
					ArrayList<AttendanceDto> manageStuMonth = dao.getManageStuMonth(yyyymm);
					PrintWriter out= resp.getWriter(); 
					out.write(dao.getManageStuMonthJson(manageStuMonth)+"");
					out.close();
				} else if (path.equals("/manage_stu_month_update.adm")) {
					// 수강생관리 목록 페이지(월별) 수정
//					String yearMonth
					String yyyymm = req.getParameter("nowDay");
					String[] userId = req.getParameterValues("arrId");
					String[] status = req.getParameterValues("arrStatus");					
					System.out.println(yyyymm);
					result = dao.updateManageStuMonth(yyyymm,userId,status);
					if(result>0){
						PrintWriter out= resp.getWriter(); 
						out.write("{\"msg\":\"성공적으로 수정되었습니다\"}");
						out.close();
					}
				}// calendar insert 페이지
				else if (path.equals("/calendar_insert.adm")) {
					CalendarDto calendarBean = new CalendarDto();
					calendarBean.setLectureId(Integer.parseInt(req.getParameter("lectureId")));	//강사기준!! 행정에서는 따로 파라미터 가져와서 설정하게 해야함
					calendarBean.setStartDate(req.getParameter("startDate")+" 00:00:00");
					calendarBean.setEndDate(req.getParameter("endDate")+" 23:59:00");
					calendarBean.setTitle(req.getParameter("title"));
					calendarBean.setContent(req.getParameter("content"));
					System.out.println(calendarBean.toString());
					result = dao.insertCalendar(calendarBean);
					System.out.println("result = "+result);
				}else if(path.equals("/calendar_update.adm")){
					CalendarDto calendarBean = new CalendarDto();
					calendarBean.setCalendarId(Integer.parseInt(req.getParameter("calendarId")));
					calendarBean.setTitle(req.getParameter("title"));
					calendarBean.setLectureId(Integer.parseInt(req.getParameter("lectureId")));
					calendarBean.setLectureName(req.getParameter("lectureName"));	///update에서는 빼도 되는 부분. 정보 이동 확을을 위해 추가함
					calendarBean.setStartDate(req.getParameter("startDate").replaceAll("T", " "));
					calendarBean.setEndDate(req.getParameter("endDate").replaceAll("T", " "));
					calendarBean.setContent(req.getParameter("content"));
					System.out.println(calendarBean.toString());
					result = dao.updateCalendar(calendarBean);
					System.out.println("result = "+result);
				}else if(path.equals("/calendar_delete.adm")){
					result = dao.deleteCalendar(Integer.parseInt(req.getParameter("calendarId")));
					System.out.println("result = "+result);
				}else if(path.equals("/qna_update.adm")){
					QnaLDto bean = new QnaLDto();
					bean.setQnaLId(Integer.parseInt(req.getParameter("qnaLId")));
					bean.setAnswerContent(req.getParameter("questionAnswer"));
					System.out.println("bean = "+bean.toString());
					result = dao.updateQnaL(bean);
					System.out.println("result = "+result);
					
					PrintWriter out = resp.getWriter();
					out.write("OK");
					out.close();
				}else if (path.equals("/qna_delete.adm")) {
					// 큐엔에이 삭제 페이지
					result = dao.deleteQnaL(Integer.parseInt(req.getParameter("qnaLId")));
					PrintWriter out = resp.getWriter();
					out.write("OK");
					out.close();					
				}
				else if (path.equals("/attendacne_allInsert.adm")) {
					System.out.println("추가!!");
					// 큐엔에이 삭제 페이지
					dao.insertAttendanceAll();
					PrintWriter out = resp.getWriter();
					out.write("OK");
					out.close();					
				}else {
					System.out.println("주소 없음 ");
				}
				

				
				
				
				if(rd!=null){					
					rd.forward(req, resp);
				}else{
					System.out.println("ajax 통신");
				}
			}
		} catch (java.lang.NullPointerException e) {
			System.out.println(e);
			resp.sendRedirect("login.bit");
		}
	}


}