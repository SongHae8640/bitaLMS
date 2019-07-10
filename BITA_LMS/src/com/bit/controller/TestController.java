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
import com.bit.model.StudentDao;
import com.bit.model.UserDao;
import com.bit.model.UserDto;

@WebServlet("*.test")
public class TestController extends HttpServlet {
	StudentDao dao = new StudentDao();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = null;

		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("testController :: path = " + path);
		
		UserDao dao = new UserDao();
		UserDto userBean = dao.login("stu1", "1234");
		
		StudentDao sDao = new StudentDao();
		
		if(path.equals("/callAttendance.test")){
			resp.setContentType("text/html;charset=UTF-8"); 
			
			System.out.println("post");
			
			String id = req.getParameter("id");
			String json = "";
			
			//Student 출석상황에 필요한 정보 가져오기
			
			//입실/지각/퇴실 정보 등 status, 입퇴실시간(where오늘,시분만 가져오기),강좌명,강좌기간
			AttendanceDto AttendanceBean = sDao.getAttendance(userBean.getUserId());
			//입실 횟수 / 지각 횟수/ 조퇴횟수 / 외출횟수 / 결석 횟수 groupby status
			int[] statusNum = sDao.getAttendanceStatusList(userBean.getUserId());
			
			//출결현황 (총출석수, 총수업수), userbean에있는 id 이용
			int attendanceDays = sDao.getAttendanceDays(userBean.getUserId());
			//userbean에 들어있는 lectureId
			int totalDays= sDao.getTotalDays(userBean.getLectureId());
			
			//출석 지각 조퇴 외출 결석
			json = "{\"status\" : \""+AttendanceBean.getStatus()+"\" , \"name\" : \""+userBean.getName()+"\" ,"
					+ " \"checkinTime\" : \""+AttendanceBean.getCheckinTime()+"\", \"checkoutTime\" : \""+AttendanceBean.getCheckoutTime()+"\","
					+ " \"lecName\" : \""+userBean.getLectureName()+"\", \"startDate\" : \""+userBean.getStartDate()+"\", \"endDate\" : \""+userBean.getEndDate()+"\""
					+ " ,\"attendanceDays\" : \""+attendanceDays+"\", \"totalDays\" : \""+totalDays+"\""
					+ " ,\"출석\" : \""+statusNum[0]+"\", \"지각\" : \""+statusNum[1]+"\", \"조퇴\" : \""+statusNum[2]+"\", \"외출\" : \""+statusNum[3]+"\", \"결석\" : \""+statusNum[4]+"\"}";
			//입실, 퇴실 버튼을 눌렀을 때는 출석입력하고 출석상황 갖고 오기
			System.out.println(json);
			PrintWriter out= resp.getWriter(); 
			out.write(json);
			out.close();
		}else if(path.equals("/callAttendanceBtn.test")){
			resp.setContentType("text/html;charset=UTF-8"); 
			
			System.out.println("btnpost");
			
			String btn = req.getParameter("btn");
			System.out.println(btn);
			String json = "";
			
			int result = sDao.updateAttendance(userBean.getUserId(), btn);
			if(result<0){
				System.out.println("오류");
			}
			AttendanceDto AttendanceBean = sDao.getAttendance(userBean.getUserId());
			json = "{\"status\" : \""+AttendanceBean.getStatus()+"\", "
					+ " \"checkinTime\" : \""+AttendanceBean.getCheckinTime()+"\", \"checkoutTime\" : \""+AttendanceBean.getCheckoutTime()+"\"}";
			
		}
	}
}
