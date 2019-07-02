package com.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.bit")
public class teacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println(path);
		
		try{
			if(req.getAttribute("belong").equals("teacher")){
				if(path.equals("/main.bit")){
					RequestDispatcher rd = req.getRequestDispatcher("teacherMain.jsp");
					rd.forward(req, resp);
				}else if(path.equals("/stumanage.bit")){
					RequestDispatcher rd = req.getRequestDispatcher("studentManage.jsp");
					rd.forward(req, resp);
				}
			}
		}catch(java.lang.NullPointerException e){
			resp.sendRedirect("login.bit");
		}
		
	}
	
	
	
}
