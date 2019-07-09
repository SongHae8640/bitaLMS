package com.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.model.AdminDao;
import com.bit.model.ApplyDto;
import com.bit.model.HomeDao;
import com.bit.model.UserDao;
import com.bit.model.UserDto;

@WebServlet("*.home")
public class HomeController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		
		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("HomeController :: path = " + path);
		
		// 세션 저장
		//HttpSession session = req.getSession();
		//UserDto userBean = (UserDto) session.getAttribute("userBean");
		
		if(path.equals("/main.home")){
			rd = req.getRequestDispatcher("home/main_H.jsp");
		}else if(path.equals("/join.home")){
			rd = req.getRequestDispatcher("home/join_H.jsp");
			
		}else if(path.equals("/apply.home")){
			rd = req.getRequestDispatcher("home/apply_H.jsp");
		}
		
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		
		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("HomeController :: path = " + path);
		
		int result = -1;
		if(path.equals("/login.home")){
			String id = req.getParameter("id");
			String pw = req.getParameter("pw");
			
			UserDao dao = new UserDao();
			UserDto userBean = dao.login(id, pw);
			try{
				String belong = userBean.getBelong();
				if(belong.equals("teacher") || belong.equals("admin") || belong.equals("student")|| belong.equals("before")){
					HttpSession session = req.getSession();
					session.setAttribute("userBean", userBean);
					resp.sendRedirect("home/login_H.jsp");
				}
			}catch(java.lang.NullPointerException e){
					req.setAttribute("msg", "<script type=\"text/javascript\">alert('id&pw를 다시 확인하세요');</script>");
					doGet(req, resp);
			}
			
		}else if(path.equals("/join.home")){
			//getParam으로 회원가입에 필요한 내용 체크 및 저장
			//전부 체크 성공 시 저장, 하나라도 안 맞으면 다시 돌려보내기
			
			//useDto생성
			UserDto userBean = new UserDto();
//			userBean.setUserId(userId); 값 초기화 (다른 값들도)
			UserDao dao = new UserDao();
			result = dao.insertUser(userBean);
			
			//정상적으로 회원 가입 한 것만
			if(result ==1) {
				HttpSession session = req.getSession();
				session.setAttribute("userBean", userBean);
				resp.sendRedirect("home/login_H.jsp");
			}else {
				//result 값에 따라 오류 메세지 
				req.setAttribute("msg", "<script type=\"text/javascript\">alert('result값에따라 문구 바꾸기');</script>");
				doGet(req, resp);
			}
			
		}else if(path.equals("apply.home")) {
			//getParam으로 수강에 필요한 내용 저장
			
			//useDto생성
			ApplyDto applyBean = new ApplyDto();
//			applyBean.setLectureId(lectureId); 값 초기화 (다른 값들도)
			HomeDao dao = new HomeDao();
			result = dao.insertApply(applyBean);
			
			//정상적으로 수강신청 한 것만
			if(result ==1) {
				req.setAttribute("msg", "<script type=\"text/javascript\">alert('수강신청이 성공적으로 완료되었습니다.');</script>");
				doGet(req, resp);
			}else {
				//result 값에 따라 오류 메세지 
				req.setAttribute("msg", "<script type=\"text/javascript\">alert('다시 확인하세요');</script>");
				doGet(req, resp);
			}
		}
	}
}