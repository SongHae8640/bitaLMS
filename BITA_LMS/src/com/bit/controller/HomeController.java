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
		
		HomeDao daoHome = new HomeDao();
		UserDao daoUser = new UserDao();
		
		if(path.equals("/main.home")){
			rd = req.getRequestDispatcher("home/main_H.jsp");
		}
		
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		
		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("HomeController :: path = " + path);
		
		if(path.equals("/login.home")){
			rd = req.getRequestDispatcher("home/login_H.jsp");
		}else if(path.equals("/join.home")){
			rd = req.getRequestDispatcher("home/join_H.jsp");
		}else if(path.equals("/apply.home")){
			rd = req.getRequestDispatcher("home/apply_H.jsp");
		}
		
		rd.forward(req, resp);
	}
}
