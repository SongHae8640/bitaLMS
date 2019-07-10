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
			
			//Student �⼮��Ȳ�� �ʿ��� ���� ��������
			
			//�Խ�/����/��� ���� �� status, ����ǽð�(where����,�úи� ��������),���¸�,���±Ⱓ
			AttendanceDto AttendanceBean = sDao.getAttendance(userBean.getUserId());
			//�Խ� Ƚ�� / ���� Ƚ��/ ����Ƚ�� / ����Ƚ�� / �Ἦ Ƚ�� groupby status
			int[] statusNum = sDao.getAttendanceStatusList(userBean.getUserId());
			
			//�����Ȳ (���⼮��, �Ѽ�����), userbean���ִ� id �̿�
			int attendanceDays = sDao.getAttendanceDays(userBean.getUserId());
			//userbean�� ����ִ� lectureId
			int totalDays= sDao.getTotalDays(userBean.getLectureId());
			
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
		}else if(path.equals("/callAttendanceBtn.test")){
			resp.setContentType("text/html;charset=UTF-8"); 
			
			System.out.println("btnpost");
			
			String btn = req.getParameter("btn");
			System.out.println(btn);
			String json = "";
			
			int result = sDao.updateAttendance(userBean.getUserId(), btn);
			if(result<0){
				System.out.println("����");
			}
			AttendanceDto AttendanceBean = sDao.getAttendance(userBean.getUserId());
			json = "{\"status\" : \""+AttendanceBean.getStatus()+"\", "
					+ " \"checkinTime\" : \""+AttendanceBean.getCheckinTime()+"\", \"checkoutTime\" : \""+AttendanceBean.getCheckoutTime()+"\"}";
			
		}
	}
}
